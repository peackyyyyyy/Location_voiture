package business;

import value_object.Adresse;
import value_object.Client;
import value_object.Personne;
import value_object.Voiture;

import java.util.ArrayList;

public class ClientManager {
    private final ArrayList<Client> clients;

    public ClientManager(ArrayList<Client> clients){
        this.clients = clients;
    }

    public void add_client(String name, String surname, String email, Adresse adresse, String phone, int id){
        //#todo add client to BDD and get id
        Personne personne = new Personne(name, surname, email, adresse, phone);
        Client client = new Client(personne, id);
        if (!this.clients.contains(client)) {
            this.clients.add(client);
        }
    }
    public void delete_client_by_id(int id){
        this.clients.removeIf(client -> client.getId() == id);
    }

    public void update_client_adresse_by_id(int id, Adresse adresse) {
        for (Client client : this.clients) {
            if (client.getId() == id) {
                client.setAdresse(adresse);
            }
        }
    }

    public void update_client_email_by_id(int id, String email) {
        for (Client client : this.clients) {
            if (client.getId() == id) {
                client.setEmail(email);
            }
        }
    }

    public void update_client_phone_by_id(int id, String phone) {
        for (Client client : this.clients) {
            if (client.getId() == id) {
                client.setPhone(phone);
            }
        }
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    @Override
    public String toString() {
        return "ClientManager{" +
                "clients=" + clients +
                '}';
    }
}
