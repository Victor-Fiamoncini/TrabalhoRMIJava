import java.rmi.*;
import java.util.ArrayList;

public interface PhoneBookServer extends Remote {
    public ArrayList<PhoneBookEntry> getPhoneBook() throws RemoteException;
    public void addEntry(PhoneBookEntry entry) throws RemoteException;
}
