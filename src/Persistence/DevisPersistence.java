package Persistence;

import value_object.Devis;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

public class DevisPersistence extends JdbcConnexion{
    private Statement conn;
    private Connection connexion;
    private VoiturePersistence vp;
    private ClientPersistence cp;
    private ArrayList<Devis> listeDevis;

    public DevisPersistence(Connection connexion, Statement conn, VoiturePersistence vp, ClientPersistence cp) throws ClassNotFoundException, SQLException {
        this.conn = conn;
        this.connexion = connexion;
        this.vp = vp;
        this.cp = cp;
    }

    public Devis getDevisWithId(int id) throws SQLException, ParseException {
        ResultSet rs = conn.executeQuery("Select * from devis where id = "+id);
        if(!rs.next())
            return null;
        return createDevis(rs);
    }
    public ArrayList<Devis> getDevis() throws SQLException, ParseException {
        ArrayList<Devis> liste = new ArrayList<>();
        ResultSet rs = conn.executeQuery("Select * from devis");
        while(rs.next())
            liste.add(createDevis(rs));
        listeDevis = liste;
        return liste;
    }

    private Devis createDevis(ResultSet rs) throws SQLException, ParseException {
        return new Devis(
                vp.getVoitureAvecId(rs.getInt("voiture_id")),
                cp.getClientWithId(rs.getInt("client_id")),
                Utilities.strToDate(rs.getString("debut")),
                Utilities.strToDate(rs.getString("fin")),
                rs.getInt("id")
                );
    }
    public int insertDevis(Devis devis) throws SQLException {
        PreparedStatement ps = connexion.prepareStatement("insert into devis (debut,fin,voiture_id,client_id) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        ps.setString(1,Utilities.dateToString(devis.getDebut()));
        ps.setString(2,Utilities.dateToString(devis.getFin()));
        ps.setInt(3,devis.getVoiture().getId());
        ps.setInt(4,devis.getClient().getId());
        int retid = ps.executeUpdate();
        devis.setId(retid);
        listeDevis.add(devis);
        return retid;
    }
    public boolean updateDevis(int id,Devis devis) throws SQLException {
        PreparedStatement ps = connexion.prepareStatement("update devis set debut = ? ,fin = ? ,voiture_id = ? ,client_id = ? where id = ?");
        ps.setString(1,Utilities.dateToString(devis.getDebut()));
        ps.setString(2,Utilities.dateToString(devis.getFin()));
        ps.setInt(3,devis.getVoiture().getId());
        ps.setInt(4,devis.getClient().getId());
        ps.setInt(5,id);
        return ps.execute();
    }
    public boolean deleteDevis(int id) throws SQLException {
        PreparedStatement ps = connexion.prepareStatement("delete from devis where id = ?");
        ps.setInt(1,id);
        return ps.execute();
    }
}
