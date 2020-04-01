import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import static java.lang.System.out;
import static java.rmi.Naming.*;

public class PhoneBookClient {
    public static void main(String[] args) {
        try {
            PhoneBookServer stub = (PhoneBookServer) lookup("rmi://localhost:3333/Contacts");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                out.print("Informe o primeiro nome: ");
                String firstname = scanner.next();
                out.print("Informe o segundo nome: ");
                String lastname = scanner.next();
                out.print("Informe o telefone: ");
                String phone = scanner.next();

                PhoneBookEntry newContact = new PhoneBookEntry(firstname, lastname, phone);
                stub.addEntry(newContact);

                out.println("Deseja cadastrar outro contato? [S/N] ");
                String keepRegistering = scanner.next();

                if (keepRegistering.equals("N") || keepRegistering.equals("n"))
                    break;
            }

            out.println("Contatos cadastrados: ");
            out.println(stub.getPhoneBook().toString());
        } catch (RemoteException | NotBoundException | MalformedURLException ex) {
            System.err.println(ex);
        }
    }
}
