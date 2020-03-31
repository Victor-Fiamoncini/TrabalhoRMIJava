import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class PhoneBookClient {
    public static void main(String[] args) {
        try {
            PhoneBookServer stub = (PhoneBookServer) Naming.lookup("rmi://localhost:5099/Contacts");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Informe o primeiro nome: ");
                String firstname = scanner.next();
                System.out.print("Informe o segundo nome: ");
                String lastname = scanner.next();
                System.out.print("Informe o telefone: ");
                String phone = scanner.next();

                PhoneBookEntry newContact = new PhoneBookEntry(firstname, lastname, phone);
                stub.addEntry(newContact);

                System.out.println("Deseja cadastrar outro contato? [S/N] ");
                String keepRegistering = scanner.next();

                if (keepRegistering.equals("N") || keepRegistering.equals("n"))
                    break;
            }

            System.out.println("Contatos cadastrados: ");
            System.out.println(stub.getPhoneBook().toString());
        } catch (RemoteException | NotBoundException | MalformedURLException ex) {
            System.err.println(ex);
        }
    }
}
