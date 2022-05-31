package gg.clouke.alpha.provider.log.impl;

import com.google.gson.JsonParser;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import gg.clouke.alpha.Alpha;
import gg.clouke.alpha.check.BaseCheck;
import gg.clouke.alpha.provider.log.Log;
import gg.clouke.alpha.provider.log.LogProvider;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Lucanius
 * @since May 31, 2022.
 * Alpha - All Rights Reserved.
 */
public class StandardLogProvider implements LogProvider {

    private final Alpha plugin;
    private final Map<UUID, Set<Log>> logs;
    private final boolean canSave;
    private final UpdateOptions options;
    private final JsonParser parser;

    public StandardLogProvider() {
        this.plugin = Alpha.getInstance();
        this.logs = new ConcurrentHashMap<>();
        this.canSave = plugin.getMongoProvider() != null;
        this.options = new UpdateOptions().upsert(true);
        this.parser = new JsonParser();
    }

    @Override
    public void log(UUID uuid, String player, BaseCheck base, int vl, String data) {
        if (!logs.containsKey(uuid)) {
            logs.put(uuid, new HashSet<>());
        }

        logs.get(uuid).add(new Log(uuid, player, base.name(), base.type(), vl, data));
    }

    @Override
    public Set<Log> fetch(UUID uuid) {
        if (canSave) {
            try (MongoCursor<Document> cursor = plugin.getMongoProvider().collect("logs").find(Filters.eq("uuid", uuid.toString())).iterator()) {
                while (cursor.hasNext()) {
                    if (!logs.containsKey(uuid)) {
                        logs.put(uuid, new HashSet<>());
                    }

                    cursor.next().values().forEach(doc -> logs.get(uuid).add(new Log((Document) doc)));
                }
            }
        }

        return logs.getOrDefault(uuid, new HashSet<>());
    }

    @Override
    public void save() {
        if (!canSave) {
            return;
        }

        logs.keySet().forEach(uuid -> {
            Set<Log> logs = fetch(uuid);
            if (logs.isEmpty()) {
                return;
            }

            Document document = new Document("uuid", uuid.toString());
            logs.forEach(log -> document.append(String.valueOf(log.getTimeStamp()), log.getDocument()));

            plugin.getMongoProvider().collect("logs").replaceOne(Filters.eq("uuid", uuid.toString()), document, options);
        });
    }

    @Override
    public String toPaste(String string) {
        try {
            URL url = new URL("https://pastie.io/documents");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.109 Safari/537.36");
            connection.setRequestProperty("Content-type", "text/plain");
            connection.setInstanceFollowRedirects(false);

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(string);
            wr.close();

            StringBuilder builder = new StringBuilder();
            try (InputStreamReader stream = new InputStreamReader(connection.getInputStream()); BufferedReader reader = new BufferedReader(stream)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
            }

            return parser.parse(builder.toString()).getAsJsonObject().get("key").getAsString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Map<UUID, Set<Log>> getCached() {
        return logs;
    }
}
