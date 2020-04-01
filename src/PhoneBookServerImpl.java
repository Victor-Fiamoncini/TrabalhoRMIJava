import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.ArrayList;

import static java.lang.System.*;

public class PhoneBookServerImpl extends UnicastRemoteObject implements PhoneBookServer {
    private ArrayList<PhoneBookEntry> contacts = new ArrayList<PhoneBookEntry>();
    private MongoClient connection;
    private MongoDatabase database;


    public PhoneBookServerImpl(MongoClient connection, MongoDatabase database) throws RemoteException {
        super();
        setConnection(connection);
        setDatabase(database);
    }

    public static void main(String[] args) {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase mongoDB = mongoClient.getDatabase("mongoContacts");

            PhoneBookServerImpl server = new PhoneBookServerImpl(mongoClient, mongoDB);

            Registry registry = LocateRegistry.createRegistry(3333);
            registry.bind("Contacts", server);

            out.println("Servidor inicializado");
        } catch (RemoteException | AlreadyBoundException ex) {
            err.println(ex);
        }
    }

    public ArrayList<PhoneBookEntry> getPhoneBook() throws RemoteException {
        out.println("Executando getPhoneBook()... Obtendo todos os contatos cadastrados");

        return this.contacts;
    }

    public void addEntry(PhoneBookEntry entry) throws RemoteException {
        out.printf("Executando addEntry()... Adicionando um novo contato na lista e na base de dados: %s%n",
                entry.toString());

        this.contacts.add(entry);

        Document document = new Document();
        document.put("firstname", entry.getFisrtname());
        document.put("lastname", entry.getLastname());
        document.put("phone", entry.getPhone());

        this.database.getCollection("contacts").insertOne(document);
    }

    public MongoClient getConnection() { return this.connection; }

    public void setConnection(MongoClient connection) {
        this.connection = connection;
    }

    public MongoDatabase getDatabase() { return this.database;  }

    public void setDatabase(MongoDatabase database) {
        this.database = database;
    }

    @Override
    public String toString() {
        return "PhoneBookServerImpl{" +
                "contacts=" + contacts +
                ", connection=" + connection +
                ", database=" + database +
                '}';
    }
}
