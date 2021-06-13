package Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import value_object.Voiture;

public class VoiturePersistence extends JdbcConnexion {
    private Statement conn;
    private Connection connexion;
    private CategoriePersistence cp;
    private CarburantPersistence carbup;
    private StatePersistence stp;
    private ArrayList<Voiture> listeVoiture;

    public VoiturePersistence(Statement conn, Connection connexion, CategoriePersistence cp, CarburantPersistence carbup, StatePersistence stp) throws ClassNotFoundException, SQLException {
        this.conn = conn;
        this.cp = cp;
        this.carbup = carbup;
        this.connexion = connexion;
        this.stp = stp;
    }

    private Voiture createVoiture(ResultSet rs) throws SQLException {
        return new Voiture(rs.getInt("id"), rs.getString("marque"), rs.getString("model"), rs.getInt("kilometers"), rs.getInt("endommage") == 1, rs.getInt("vitesse") == 1, rs.getInt("clim") == 1, this.cp.getCategorieAvecId(rs.getInt("categorie_id")), this.carbup.getCarburantAvecId(rs.getInt("carburant_id")), this.stp.getStateAvecId(rs.getInt("state_id")));
    }

    public Voiture getVoitureAvecId(Integer id) throws SQLException {
        Statement con = super.getConn();
        new ArrayList();
        ResultSet rs = con.executeQuery("Select * from voiture where id=" + id);
        return !rs.next() ? null : this.createVoiture(rs);
    }

    public ArrayList<Voiture> getVoitures() throws SQLException {
        Statement con = super.getConn();
        ArrayList<Voiture> liste = new ArrayList();
        ResultSet rs = con.executeQuery("Select * from voiture");

        while(rs.next()) {
            liste.add(this.createVoiture(rs));
        }

        this.listeVoiture = liste;
        return liste;
    }

    public int insertVoiture(Voiture vt) throws SQLException {
        PreparedStatement ps = this.connexion.prepareStatement("insert into voiture (marque,model,kilometers,endommage,vitesse,clim,categorie_id,carburant_id,state_id) values (?,?,?,?,?,?,?,?,?)", 1);
        ps.setString(1, vt.getMarque());
        ps.setString(2, vt.getModel());
        ps.setInt(3, vt.getKilometers());
        ps.setBoolean(4, vt.isEndommage());
        ps.setBoolean(5, vt.isVitesse());
        ps.setBoolean(6, vt.isClim());
        ps.setInt(7, this.cp.getIdCategorie(vt.getCategorie()));
        ps.setInt(8, this.carbup.getIdCarbu(vt.getCarburant()));
        ps.setInt(9, this.stp.getIdState(vt.getState()));
        int retid = ps.executeUpdate();
        vt.setId(retid);
        this.listeVoiture.add(vt);
        return retid;
    }

    public int updateVoiture(int id, Voiture vt) throws SQLException {
        PreparedStatement ps = this.connexion.prepareStatement("update voiture set marque = ? ,model = ? ,kilometers = ? ,endommage = ? ,vitesse = ? ,clim = ? ,categorie_id = ? ,carburant_id = ?, state_id = ? where id = ?");
        ps.setString(1, vt.getMarque());
        ps.setString(2, vt.getModel());
        ps.setInt(3, vt.getKilometers());
        ps.setBoolean(4, vt.isEndommage());
        ps.setBoolean(5, vt.isVitesse());
        ps.setBoolean(6, vt.isClim());
        ps.setInt(7, this.cp.getIdCategorie(vt.getCategorie()));
        ps.setInt(8, this.carbup.getIdCarbu(vt.getCarburant()));
        ps.setInt(9, this.stp.getIdState(vt.getState()));
        ps.setInt(10, id);
        int retid = ps.executeUpdate();
        return retid;
    }

    public boolean deleteVoiture(int id) throws SQLException {
        PreparedStatement ps = this.connexion.prepareStatement("delete from voiture where id = ? ");
        ps.setInt(1, id);
        return ps.execute();
    }

    public CategoriePersistence getCp() {
        return this.cp;
    }

    public void setCp(CategoriePersistence cp) {
        this.cp = cp;
    }

    public CarburantPersistence getCarbup() {
        return this.carbup;
    }

    public void setCarbup(CarburantPersistence carbup) {
        this.carbup = carbup;
    }
}

