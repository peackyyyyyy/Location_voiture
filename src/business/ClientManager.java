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

    /**
     * Le Client manager contient toutes les méthodes liées aux clients (ajouter, modifier, rechercher ....)
     * @param clients  liste de tous les clients
     * @param clientPersistence  la classe client persistence est responsable de l'interaction avec la BDD pour la table client
     */

    public ClientManager(ArrayList<Client> clients,ClientPersistence clientPersistence) {
        this.clients = clients;
        this.clientPersistence = clientPersistence;
    }

    /**
     * Ajouter un client en RAM et en BDD
     * @param name
     * @param surname
     * @param email
     * @param adresse
     * @param phone
     * @return id du client
     * @throws SQLException
     */

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

    /**
     * Ajouter un client avec un client en argument
     * @param client
     * @return id client
     * @throws SQLException
     */

    public int add_client(Client client) throws SQLException {

        if (!this.clients.contains(client)) {
            this.clients.add(client);
            int leid = clientPersistence.insertClient(client);
            client.setId(leid);
            return leid;
        }
        return -1;
    }

    /**
     * Supprimer un client par son id en RAM
     * @param id
     */

    public void delete_client_by_id(int id) {
        this.clients.removeIf(client -> client.getId() == id);
    }

    /**
     * Modifier un client, grace a l'id du client et un object client correspondent au nouveau client
     * @param id
     * @param client
     * @return nombre de lignes modifiées
     * @throws SQLException
     */

    public int updateClient(int id, Client client) throws SQLException {
        int ret = clientPersistence.updateClient(id,client);
        delete_client_by_id(id);
        clients.add(client);
        return ret;
    }

    /**
     * Supprimer client par id
     * @param id
     * @throws SQLException
     */

    public void delete(int id) throws SQLException {
        clientPersistence.deleteClient(id);
        delete_client_by_id(id);
    }

    /**
     * Recherche de client en RAM en fonction de paramettre entrées. Les paramettres sont optionnel, la méthodes réalise des filtres successif de parametre, dans le cas ou il y en a un.
     * Ce qui permet d'avoir des recherches flexibles
     * @param id
     * @param name
     * @param surname
     * @return list de clients
     */

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

    /**
     * Trie la liste de clients en fonction de l'ID
     * @param id
     * @param result
     * @return
     */
    private ArrayList<Client> try_list_id(int id, ArrayList<Client> result){
        ArrayList<Client> newresult = new ArrayList<>();
        for (Client client: result){
            if (id==client.getId()){
                newresult.add(client);
            }
        }
        return newresult;
    }

    /**
     * Trie la list de client en fonction du nom
     * @param name
     * @param result
     * @return
     */

    private ArrayList<Client> try_list_name(String name, ArrayList<Client> result){
        ArrayList<Client> newresult = new ArrayList<>();
        for (Client client: result){
            if (name.equals(client.getName())){
                newresult.add(client);
            }
        }
        return newresult;
    }

    /**
     * Trie la list de clients en fonction du prenom
     * @param surname
     * @param result
     * @return
     */

    private ArrayList<Client> try_list_surname(String surname, ArrayList<Client> result){
        ArrayList<Client> newresult = new ArrayList<>();
        for (Client client: result){
            if (surname.equals(client.getSurname())){
                newresult.add(client);
            }
        }
        return newresult;
    }

    /**
     * Retourn un client par ID
     * @param id
     * @return client or null
     */

    public Client get_client_by_id(int id){
        for (Client client: this.clients){
            if (client.getId() == id){
                return client;
            }
        }
        return null;
    }

    /**
     * Supprime client en base
     * @param id
     * @return
     * @throws SQLException
     */

    public boolean deleteClientBdd(int id) throws SQLException {
        delete_client_by_id(id);
        return clientPersistence.deleteClient(id);
    }


    /**
     * Mise a jour de l'adresse d'un client par l'ID
     * @param id
     * @param adresse
     */


    public void update_client_adresse_by_id(int id, Adresse adresse) {
        for (Client client : this.clients) {
            if (client.getId() == id) {
                client.setAdresse(adresse);
            }
        }
    }

    /**
     * Mis a jour de l'email d'un client par l'ID
     * @param id
     * @param email
     */

    public void update_client_email_by_id(int id, String email) {
        for (Client client : this.clients) {
            if (client.getId() == id) {
                client.setEmail(email);
            }
        }
    }

    /**
     * Mis a jour du téléphone d'un client par l'ID
     * @param id
     * @param phone
     */

    public void update_client_phone_by_id(int id, String phone) {
        for (Client client : this.clients) {
            if (client.getId() == id) {
                client.setPhone(phone);
            }
        }
    }

    /**
     * Recuperer tous les clients en BDD
     * @return
     * @throws SQLException
     * @throws ParseException
     */

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