import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.ArrayList;

import static java.lang.System.*;

public class PhoneBookServerImpl extends UnicastRemoteObject implements PhoneBookServer {
    private ArrayList<PhoneBookEntry> contacts = new ArrayList<PhoneBookEntry>();

    public PhoneBookServerImpl() throws RemoteException {
        super();
    }

    public static void main(String[] args) {
        try {
            PhoneBookServerImpl server = new PhoneBookServerImpl();

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
        out.printf("Executando addEntry()... Adicionando um novo contato na lista: %s%n",
                entry.toString());

        this.contacts.add(entry);
    }
}
