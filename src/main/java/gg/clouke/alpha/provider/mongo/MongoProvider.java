package gg.clouke.alpha.provider.mongo;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

/**
 * @author Lucanius
 * @since May 31, 2022.
 * Alpha - All Rights Reserved.
 */
public interface MongoProvider {

    MongoProvider enable(boolean enable);

    MongoProvider host(String host);

    MongoProvider port(int port);

    MongoProvider database(String database);

    MongoProvider auth(boolean auth);

    MongoProvider user(String user);

    MongoProvider pass(String pass);

    MongoProvider authDb(String authDb);

    MongoProvider connect();

    MongoProvider dispose();

    MongoCollection<Document> collect(String collection);

}
