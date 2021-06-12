package test;
import business.ClientManager;
import value_object.Adresse;
import value_object.Client;
import java.util.ArrayList;
import java.util.Optional;

public class TestClientManager {
    public static void main(String[] args) {
        Adresse adresse = new Adresse("rue", "ville", "06600");
        ArrayList<Client> clientsArrayList = new ArrayList<>();
        ClientManager clientManager = new ClientManager(clientsArrayList);
        clientManager.add_client("name", "surname", "mail", adresse, "0658526192", 1);
        clientManager.add_client("name2", "surname2", "mail2", adresse, "0658526192", 2);
        clientManager.add_client("name", "surname3", "mail2", adresse, "0658526192", 3);
        clientManager.update_client_email_by_id(2, "new email");
        /*for (Client client : clientsArrayList) {
            System.out.println(client);
        }*/
        ArrayList<Client> result = clientManager.find_clients(Optional.empty(), Optional.of("name"), Optional.of("surname"));
        for (Client client: result){
            System.out.println(client);
        }


    }
}
