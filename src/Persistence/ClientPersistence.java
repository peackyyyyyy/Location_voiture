package Persistence;

import value_object.Adresse;
import value_object.Client;
import value_object.Fidelite;
import value_object.Voiture;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

public class ClientPersistence extends JdbcConnexion{

    private VoiturePersistence vp;
    private FidelitePersistence fp;
    private Statement conn;
    private Connection connexion;

    public ClientPersistence(Statement conn, Connection connexion,VoiturePersistence vp, FidelitePersistence fp) throws ClassNotFoundException, SQLException {
        this.conn = conn;
        this.vp = vp;
        this.fp = fp;
        this.connexion = connexion;
    }

    public VoiturePersistence getVp() {
        return vp;
    }

    public void setVp(VoiturePersistence vp) {
        this.vp = vp;
    }

    public FidelitePersistence getFp() {
        return fp;
    }

    public void setFp(FidelitePersistence fp) {
        this.fp = fp;
    }

    /**
     * Methode pour créer un client
     * @param rs le resultat de la base de donnée
     * @return un client
     * @throws SQLException
     * @throws ParseException
     */
    public Client createClient(ResultSet rs ) throws SQLException, ParseException {
        return new Client(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("email"),
                new Adresse(rs.getString("adresse")),
                rs.getString("phone"),
                vp.getVoitureAvecId(rs.getInt("voiture_id")),
                fp.getFideliteAvecId(rs.getInt("fidelite_id"))
        );
    }

    /**
     * Avoir un client par rapport à son id
     * @param id l'id du client voulu
     * @return le client
     * @throws SQLException
     * @throws ParseException
     */
    public Client getClientWithId(int id) throws SQLException, ParseException {
        ResultSet rs = conn.executeQuery("Select * from client where id="+id);
        if(rs.next() == false)
            return null;
        return createClient(rs);
    }

    /**
     * Methode pour lister les clients en base
     * @return
     * @throws SQLException
     * @throws ParseException
     */
    public ArrayList<Client> getClients() throws SQLException, ParseException {
        ArrayList<Client> liste = new ArrayList<Client>();
        ResultSet rs = conn.executeQuery("Select * from client");
        while(rs.next())
            liste.add(createClient(rs));
        return liste;
    }

    /**
     * Method pour inserer un client en base
     * @param client le client à mettre
     * @return l'id du client généré
     * @throws SQLException
     */
    public int insertClient(Client client) throws SQLException {
        PreparedStatement ps = connexion.prepareStatement("insert into client (name,surname,email,adresse,phone,voiture_id,fidelite_id) values (?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
        ps.setString(1,client.getName());
        ps.setString(2,client.getSurname());
        ps.setString(3,client.getEmail());
        Adresse ad = client.getAdresse();
        ps.setString(4,ad.getRue()+";"+ad.getVille()+";"+ad.getCodepostal());
        ps.setString(5,client.getPhone());
        if (client.getLocation() != null) {
            ps.setInt(6, client.getLocation().getId());
        } else {
            ps.setNull(6, Types.INTEGER);
        }
        if (client.getFidelite() != null) {
            ps.setInt(7, client.getFidelite().getId());
        } else {
            ps.setNull(7, Types.INTEGER);
        }
        int retid = ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    /**
     * Changer les informations d'un client
     * @param id l'id du client que l'on veut changer
     * @param client les informations du client à mettre
     * @return le nombre de lignes changé en base
     * @throws SQLException
     */
    public int updateClient(int id, Client client) throws SQLException {
        PreparedStatement ps = connexion.prepareStatement("update client set  name = ? ,surname = ? ,email = ? ,adresse = ? ,phone = ? ,voiture_id = ? ,fidelite_id = ? where id=?");
        ps.setString(1,client.getName());
        ps.setString(2,client.getSurname());
        ps.setString(3,client.getEmail());
        Adresse ad = client.getAdresse();
        ps.setString(4,ad.getRue()+";"+ad.getVille()+";"+ad.getCodepostal());
        ps.setString(5,client.getPhone());
        if (client.getLocation() != null) {
            ps.setInt(6, client.getLocation().getId());
        } else {
            ps.setNull(6, Types.INTEGER);
        }
        if (client.getFidelite() != null) {
            ps.setInt(7, client.getFidelite().getId());
        } else {
            ps.setNull(7, Types.INTEGER);
        }
        ps.setInt(8,id);
        return ps.executeUpdate();
    }

    /**
     * Suppression d'un client en base
     * @param id l'id du client à supmprimé
     * @return si l'opération s'est bien passé
     * @throws SQLException
     */
    public boolean deleteClient(int id) throws SQLException {
        PreparedStatement ps = connexion.prepareStatement("delete from client where id = ?");
        ps.setInt(1,id);
        return ps.execute();
    }
}
