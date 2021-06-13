package business;

import value_object.Adresse;
import value_object.Client;
import value_object.Personne;

import java.util.ArrayList;
import java.util.Optional;

public class ClientManager {
    private final ArrayList<Client> clients;

    public ClientManager(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public void add_client(String name, String surname, String email, Adresse adresse, String phone, int id) {
        //#todo add client to BDD and get id
        Personne personne = new Personne(name, surname, email, adresse, phone);
        Client client = new Client(personne, id);
        if (!this.clients.contains(client)) {
            this.clients.add(client);
        }
    }
    public Client get_client_by_id(int id){
        for (Client client: this.clients){
            if (client.getId() == id){
                return client;
            }
        }
        return null;
    }

    public void delete_client_by_id(int id) {
        this.clients.removeIf(client -> client.getId() == id);
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
