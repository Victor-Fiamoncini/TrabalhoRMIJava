import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.ArrayList;

public class PhoneBookServerImpl extends UnicastRemoteObject implements PhoneBookServer {
    private ArrayList<PhoneBookEntry> contacts = new ArrayList<PhoneBookEntry>();

    public PhoneBookServerImpl() throws RemoteException {
        super();
    }

    public static void main(String[] args) {
        try {
            PhoneBookServerImpl server = new PhoneBookServerImpl();

            Registry registry = LocateRegistry.createRegistry(5099);
            registry.bind("Contacts", server);

            System.out.println("Servidor pronto");
        } catch (RemoteException | AlreadyBoundException ex) {
            System.err.println(ex);
        }
    }

    public ArrayList<PhoneBookEntry> getPhoneBook() throws RemoteException {
        System.out.println("Executando getPhoneBook()... obtendo todos os contatos cadastrados");

        return this.contacts;
    }

    public void addEntry(PhoneBookEntry entry) throws RemoteException {
        System.out.printf("Executando o metodo addEntry()... Adicionando a lista de contato: %s%n",
                entry.toString());

        this.contacts.add(entry);
    }
}
