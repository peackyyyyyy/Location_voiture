package business;

import Persistence.ClientPersistence;
import value_object.Adresse;
import value_object.Client;
import value_object.Personne;
import value_object.Voiture;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Optional;

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

    public int add_client(Client client) throws SQLException {

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

    public int updateClient(int id, Client client) throws SQLException {
        int ret = clientPersistence.updateClient(id,client);
        delete_client_by_id(id);
        clients.add(client);
        return ret;
    }

    public void delete(int id) throws SQLException {
        clientPersistence.deleteClient(id);
        delete_client_by_id(id);
    }

    public ArrayList<Client> find_clients(Optional<Integer> id, Optional<String> name, Optional<String> surname){
        ArrayList<Client> result = this.clients;
        if (id.isPresent()){
            result = try_list_id(id.get(), result);
        }
        if (name.isPresent()){
            result = try_list_name(name.get(), result);
        }
        if (surname.isPresent()){
            result = try_list_surname(surname.get(), result);
        }
        return result;
    }
    private ArrayList<Client> try_list_id(int id, ArrayList<Client> result){
        ArrayList<Client> newresult = new ArrayList<>();
        for (Client client: result){
            if (id==client.getId()){
                newresult.add(client);
            }
        }
        return newresult;
    }

    private ArrayList<Client> try_list_name(String name, ArrayList<Client> result){
        ArrayList<Client> newresult = new ArrayList<>();
        for (Client client: result){
            if (name.equals(client.getName())){
                newresult.add(client);
            }
        }
        return newresult;
    }

    private ArrayList<Client> try_list_surname(String surname, ArrayList<Client> result){
        ArrayList<Client> newresult = new ArrayList<>();
        for (Client client: result){
            if (surname.equals(client.getSurname())){
                newresult.add(client);
            }
        }
        return newresult;
    }

    public Client get_client_by_id(int id){
        for (Client client: this.clients){
            if (client.getId() == id){
                return client;
            }
        }
        return null;
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