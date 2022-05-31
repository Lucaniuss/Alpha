package gg.clouke.alpha.provider.mongo.impl;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import gg.clouke.alpha.provider.mongo.MongoProvider;
import lombok.Getter;
import org.bson.Document;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lucanius
 * @since May 31, 2022.
 * Alpha - All Rights Reserved.
 */
@Getter
public class StandardMongoProvider implements MongoProvider {

    private boolean enabled;
    private String host;
    private int port;
    private String database;
    private boolean auth;
    private String user;
    private String pass;
    private String authDb;

    private MongoClient client;
    private MongoDatabase db;
    private Map<String, MongoCollection<Document>> collections;

    @Override
    public MongoProvider enable(boolean enable) {
        this.enabled = enable;
        return this;
    }

    @Override
    public MongoProvider host(String host) {
        this.host = host;
        return this;
    }

    @Override
    public MongoProvider port(int port) {
        this.port = port;
        return this;
    }

    @Override
    public MongoProvider database(String database) {
        this.database = database;
        return this;
    }

    @Override
    public MongoProvider auth(boolean auth) {
        this.auth = auth;
        return this;
    }

    @Override
    public MongoProvider user(String user) {
        this.user = user;
        return this;
    }

    @Override
    public MongoProvider pass(String pass) {
        this.pass = pass;
        return this;
    }

    @Override
    public MongoProvider authDb(String authDb) {
        this.authDb = authDb;
        return this;
    }

    @Override
    public MongoProvider connect() {
        if (!enabled) {
            return null;
        }

        try {
            client = auth
                    ? new MongoClient(new ServerAddress(host, port), Collections.singletonList(MongoCredential.createCredential(user, authDb, pass.toCharArray())))
                    : new MongoClient(host, port);
        } catch (Exception e) {
            e.printStackTrace();
            return this;
        }

        db = client.getDatabase(database);
        collections = new HashMap<>();

        return this;
    }

    @Override
    public MongoProvider dispose() {
        if (client != null) {
            client.close();
        }

        return this;
    }

    @Override
    public MongoCollection<Document> collect(String collection) {
        return collections.computeIfAbsent(collection, c -> db.getCollection(c));
    }
}
