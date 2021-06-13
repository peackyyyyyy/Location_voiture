package Persistence;

import value_object.Voiture;

import java.sql.*;
import java.util.ArrayList;

public class VoiturePersistence extends JdbcConnexion{

    Statement conn;
    Connection connexion;
    CategoriePersistence cp;
    CarburantPersistence carbup;
    AgencePersistence ap;
    StatePersistence stp;
    ArrayList<Voiture> liseVoiture;

    public VoiturePersistence(Statement conn, Connection connexion, CategoriePersistence cp, CarburantPersistence carbup, StatePersistence stp,AgencePersistence ap) throws ClassNotFoundException, SQLException {
        this.conn = conn;
        this.cp = cp;
        this.carbup = carbup;
        this.connexion =  connexion;
        this.stp = stp;
        this.ap = ap;
    }

    /**
     * Créer une voiture
     * @param rs le résultat de labase de donnée
     * @return
     * @throws SQLException
     */
    private Voiture createVoiture(ResultSet rs) throws SQLException {
        return new Voiture(
                rs.getInt("id"),
                rs.getString("marque"),
                rs.getString("model"),
                rs.getInt("kilometers"),
                rs.getInt("endommage")==1?true:false,
                rs.getInt("vitesse")==1?true:false,
                rs.getInt("clim")==1?true:false,
                ap.getAgenceWithId(rs.getInt("agence_id")),
                cp.getCategorieAvecId(rs.getInt("categorie_id")),
                carbup.getCarburantAvecId(rs.getInt("carburant_id")),
                stp.getStateAvecId(rs.getInt("state_id")),
                ap.getAgenceWithId(rs.getInt("agence_id_a_etre"))
        );
    }

    /**
     * Retourne la voitire par rapport à son id
     * @param id l'id de la voiture voulu
     * @return la voiture vculu
     * @throws SQLException
     */
    public Voiture getVoitureAvecId(Integer id) throws SQLException {
        Statement con = super.getConn();
        ResultSet rs = con.executeQuery("Select * from voiture where id="+id);
        if(rs.next() == false)
            return null;
        return createVoiture(rs);

    }

    /**
     * Liste toutes les voiture en base
     * @return une liste de voiture
     * @throws SQLException
     */
    public ArrayList<Voiture> getVoitures() throws SQLException {
        Statement con = super.getConn();
        ArrayList<Voiture> listeVoitures = new ArrayList<Voiture>();
        ResultSet rs = con.executeQuery("Select * from voiture");
        while(rs.next()){
            listeVoitures.add(createVoiture(rs));
        }
        return listeVoitures;
    }

    /**
     * Inserer une voiture en base
     * @param vt la voiture que l'on veut mettre
     * @return l'id de la voiture créer
     * @throws SQLException
     */
    public int insertVoiture(Voiture vt) throws SQLException {
        PreparedStatement ps = connexion.prepareStatement("insert into voiture (marque,model,kilometers,endommage,vitesse,clim,categorie_id,carburant_id,state_id,agence_id,agence_id_a_etre) values (?,?,?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, vt.getMarque());
        ps.setString(2,vt.getModel());
        ps.setInt(3,vt.getKilometers());
        ps.setBoolean(4,vt.isEndommage());
        ps.setBoolean(5,vt.isVitesse());
        ps.setBoolean(6,vt.isClim());
        ps.setInt(7,cp.getIdCategorie(vt.getCategorie()));
        ps.setInt(8,carbup.getIdCarbu(vt.getCarburant()));
        ps.setInt(9,vt.getAgence().getId());
        ps.setInt(10,vt.getAgence().getId());
        ps.setInt(11,stp.getIdState(vt.getState()));
        ps.setInt(11,vt.getAgence_a_etre().getId());
        int retid = ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    /**
     * Changer les informations d'une voiture
     * @param id l'id de la voiture que l'on veut changer
     * @param vt les informations à mettre
     * @return le nombre de ligne à mettre
     * @throws SQLException
     */
    public int updateVoiture(int id, Voiture vt) throws SQLException {
        PreparedStatement ps = connexion.prepareStatement("update voiture set marque = ? ,model = ? ,kilometers = ? ,endommage = ? ,vitesse = ? ,clim = ? ,categorie_id = ? ,carburant_id = ?, state_id = ? , agence_id = ?, agence_id_a_etre = ? where id = ?");
        ps.setString(1, vt.getMarque());
        ps.setString(2,vt.getModel());
        ps.setInt(3,vt.getKilometers());
        ps.setBoolean(4,vt.isEndommage());
        ps.setBoolean(5,vt.isVitesse());
        ps.setBoolean(6,vt.isClim());
        ps.setInt(7,cp.getIdCategorie(vt.getCategorie()));
        ps.setInt(8,carbup.getIdCarbu(vt.getCarburant()));
        ps.setInt(9,stp.getIdState(vt.getState()));
        ps.setInt(10,vt.getAgence().getId());
        ps.setInt(11,vt.getAgence_a_etre().getId());
        ps.setInt(12,id);
        return ps.executeUpdate();
    }

    /**
     * Supprimé une voiture par rapport id
     * @param id l'id de la voiture à mettre
     * @return si l'opération à mettre
     * @throws SQLException
     */
    public boolean deleteVoiture(int id) throws SQLException {
        PreparedStatement ps = connexion.prepareStatement("delete from voiture where id = ? ");
        ps.setInt(1,id);
        return ps.execute();
    }

    public CategoriePersistence getCp() {
        return cp;
    }

    public void setCp(CategoriePersistence cp) {
        this.cp = cp;
    }

    public CarburantPersistence getCarbup() {
        return carbup;
    }

    public void setCarbup(CarburantPersistence carbup) {
        this.carbup = carbup;
    }
}
