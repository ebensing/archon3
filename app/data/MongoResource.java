package data;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.net.UnknownHostException;
import java.util.Properties;

/**
 * User: EJ
 * Date: 11/5/13
 * Time: 11:13 PM
 */
public enum MongoResource {
    INSTANCE;
    private MongoClient mongoClient;
    private Properties properties;
    private String host = "localhost";
    private int port = 27017;

    private MongoResource() {
        try {
            if (mongoClient == null) {
                mongoClient = getClient();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MongoClient getClient() {
        try {
            return new MongoClient(host, port);
        } catch (UnknownHostException uh) {
            uh.printStackTrace();
        }
        return null;
    }

    public Datastore getDatastore(String dbName) {
        Datastore ds;
        ds = new Morphia().createDatastore(mongoClient,dbName);

        return ds;
    }
}
