package Persistence;

import value_object.Devis;

import java.lang.reflect.Type;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class DevisPersistence extends JdbcConnexion{
    private Statement conn;
    private Connection connexion;
    private VoiturePersistence vp;
    private ClientPersistence cp;

    public DevisPersistence(Connection connexion, Statement conn, VoiturePersistence vp, ClientPersistence cp) throws ClassNotFoundException, SQLException {
        this.conn = conn;
        this.connexion = connexion;
        this.vp = vp;
        this.cp = cp;
    }

    /**
     * Avoir un devis par rapport à son id
     * @param id l'id du devis désiré
     * @return le devis désiré
     * @throws SQLException
     * @throws ParseException
     */
    public Devis getDevisWithId(int id) throws SQLException, ParseException {
        ResultSet rs = conn.executeQuery("Select * from devis where id = "+id);
        if(rs.next() == false)
            return null;
        return createDevis(rs);
    }

    /**
     * Lister les devis en base
     * @return la liste de tous les devis
     * @throws SQLException
     * @throws ParseException
     */
    public ArrayList<Devis> getDevis() throws SQLException, ParseException {
        ArrayList<Devis> liste = new ArrayList<Devis>();
        ResultSet rs = conn.executeQuery("Select * from devis");
        while(rs.next())
            liste.add(createDevis(rs));
        return liste;
    }

    /**
     * Créer un devis en base
     * @param rs
     * @return le devis créer
     * @throws SQLException
     * @throws ParseException
     */
    private Devis createDevis(ResultSet rs) throws SQLException, ParseException {
        String fin = rs.getString("fin");
        Date finDate;
        if(fin == null || fin.equals("")){
            finDate = null;
        }
        else {
            finDate = Utilities.strToDate(rs.getString("fin"));
        }
        return new Devis(
                vp.getVoitureAvecId(rs.getInt("voiture_id")),
                cp.getClientWithId(rs.getInt("client_id")),
                Utilities.strToDate(rs.getString("debut")),
                finDate,
                rs.getInt("id")
        );
    }

    /**
     * Onsérer un devis en base
     * @param devis
     * @return l'id du devis créer
     * @throws SQLException
     */
    public int insertDevis(Devis devis) throws SQLException {
        PreparedStatement ps = connexion.prepareStatement("insert into devis (debut,fin,voiture_id,client_id) values (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, Utilities.dateToString(devis.getDebut()));
        if(devis.getFin() != null) {
            ps.setString(2, Utilities.dateToString(devis.getFin()));
        }else{
            ps.setNull(2, Types.CHAR);
        }
        ps.setInt(3, devis.getVoiture().getId());
        ps.setInt(4, devis.getClient().getId());
        int retid = ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    /**
     * Changer les informations d'un devis en base
     * @param id l'id du devis à changer
     * @param devis les informations à mettre
     * @return le nombre de ligne changé en base
     * @throws SQLException
     */
    public boolean updateDevis(int id,Devis devis) throws SQLException {
        PreparedStatement ps = connexion.prepareStatement("update devis set debut = ? ,fin = ? ,voiture_id = ? ,client_id = ? where id = ?");
        ps.setString(1,Utilities.dateToString(devis.getDebut()));
        if(devis.getFin() != null) {
            ps.setString(2, Utilities.dateToString(devis.getFin()));
        }else{
            ps.setNull(2, Types.CHAR);
        }
        ps.setInt(3,devis.getVoiture().getId());
        ps.setInt(4,devis.getClient().getId());
        ps.setInt(5,id);
        return ps.execute();
    }

    /**
     * Supprimer un devis en base
     * @param id l'id du devis à supprimer
     * @return si l'opération s'est bien passsé
     * @throws SQLException
     */
    public boolean deleteDevis(int id) throws SQLException {
        PreparedStatement ps = connexion.prepareStatement("delete from devis where id = ?");
        ps.setInt(1,id);
        return ps.execute();
    }
}
