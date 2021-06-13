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
    private ArrayList<Client> listeClient;

    public ClientPersistence(Statement conn, Connection connexion,VoiturePersistence vp, FidelitePersistence fp) throws ClassNotFoundException, SQLException {
        this.conn = conn;
        this.vp = vp;
        this.fp = fp;
        this.connexion = connexion;
        this.listeClient = new ArrayList<>();
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

    public Client getClientWithId(int id) throws SQLException, ParseException {
        ResultSet rs = conn.executeQuery("Select * from client where id="+id);
        if(!rs.next())
            return null;
        return createClient(rs);
    }

    public ArrayList<Client> getClients() throws SQLException, ParseException {
        ArrayList<Client> liste = new ArrayList<>();
        ResultSet rs = conn.executeQuery("Select * from client");
        while(rs.next())
            liste.add(createClient(rs));
        this.listeClient = liste;
        return liste;
    }
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

    public boolean deleteClient(int id) throws SQLException {
        PreparedStatement ps = connexion.prepareStatement("delete from client where id = ?");
        ps.setInt(1,id);
        return ps.execute();
    }

}
