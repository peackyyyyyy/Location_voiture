package business;

import Persistence.ClientPersistence;
import value_object.Adresse;
import value_object.Client;
import value_object.Personne;
import value_object.Voiture;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class ClientManager {
    private ArrayList<Client> clients;
    private ClientPersistence clientPersistence;

    public ClientManager(ArrayList<Client> clients,ClientPersistence clientPersistence) {
        this.clients = clients;
        this.clientPersistence = clientPersistence;
    }

    public int add_client(String name, String surname, String email, Adresse adresse, String phone) throws SQLException {
        Personne personne = new Personne(name, surname, email, adresse, phone);
        Client client = new Client(personne);

        if (!this.clients.contains(client)) {
            this.clients.add(client);
            int leid = clientPersistence.insertClient(client);
            client.setId(leid);
            return leid;
        }
        return -1;
    }

    public void delete_client_by_id(int id) {
        this.clients.removeIf(client -> client.getId() == id);
    }

    public void updateClient(int id, Client client) throws SQLException {
        clientPersistence.updateClient(id,client);
        delete_client_by_id(id);
        clients.add(client);
    }

    public boolean deleteClientBdd(int id) throws SQLException {
        delete_client_by_id(id);
        return clientPersistence.deleteClient(id);
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

    public ArrayList<Client> getClients() throws SQLException, ParseException {
        clients = clientPersistence.getClients();
        return clients;
    }

    @Override
    public String toString() {
        return "ClientManager{" +
                "clients=" + clients +
                '}';
    }
}
