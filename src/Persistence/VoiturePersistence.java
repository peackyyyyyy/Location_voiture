package Persistence;

import value_object.Categorie.Luxe;
import value_object.ICategorie;
import value_object.Voiture;
import value_object.model.Enumeration;

import java.sql.*;
import java.util.ArrayList;

public class VoiturePersistence extends JdbcConnexion{

    Statement conn;
    Connection connexion;
    CategoriePersistence cp;
    CarburantPersistence carbup;
    StatePersistence stp;

    public VoiturePersistence(Statement conn, Connection connexion, CategoriePersistence cp, CarburantPersistence carbup, StatePersistence stp) throws ClassNotFoundException, SQLException {
        this.conn = conn;
        this.cp = cp;
        this.carbup = carbup;
        this.connexion =  connexion;
        this.stp = stp;
    }

    private Voiture createVoiture(ResultSet rs) throws SQLException {
        return new Voiture(
                rs.getInt("id"),
                rs.getString("marque"),
                rs.getString("model"),
                rs.getInt("kilometers"),
                rs.getInt("endommage")==1?true:false,
                rs.getInt("vitesse")==1?true:false,
                rs.getInt("clim")==1?true:false,
                cp.getCategorieAvecId(rs.getInt("categorie_id")),
                carbup.getCarburantAvecId(rs.getInt("carburant_id")),
                stp.getStateAvecId(rs.getInt("state_id"))
                );
    }
    public Voiture getVoitureAvecId(Integer id) throws SQLException {
        Statement con = super.getConn();
        ArrayList<Voiture> listeVoitures = new ArrayList<Voiture>();
        ResultSet rs = con.executeQuery("Select * from voiture where id="+id);
        if(rs.next() == false)
            return null;
        return createVoiture(rs);

    }
    public ArrayList<Voiture> getVoitures() throws SQLException {
        Statement con = super.getConn();
        ArrayList<Voiture> listeVoitures = new ArrayList<Voiture>();
        ResultSet rs = con.executeQuery("Select * from voiture");
        while(rs.next()){
            listeVoitures.add(createVoiture(rs));
        }
        return listeVoitures;
    }

    public boolean insertVoiture(Voiture vt) throws SQLException {
        PreparedStatement ps = connexion.prepareStatement("insert into voiture (marque,model,kilometers,endommage,vitesse,clim,categorie_id,carburant_id,state_id) values (?,?,?,?,?,?,?,?,?)");
        ps.setString(1, vt.getMarque());
        ps.setString(2,vt.getModel());
        ps.setInt(3,vt.getKilometers());
        ps.setBoolean(4,vt.isEndommage());
        ps.setBoolean(5,vt.isVitesse());
        ps.setBoolean(6,vt.isClim());
        ps.setInt(7,cp.getIdCategorie(vt.getCategorie()));
        ps.setInt(8,carbup.getIdCarbu(vt.getCarburant()));
        ps.setInt(9,stp.getIdState(vt.getState()));
        return ps.execute();
    }

    public int updateVoiture(int id, Voiture vt) throws SQLException {
        PreparedStatement ps = connexion.prepareStatement("update voiture set marque = ? ,model = ? ,kilometers = ? ,endommage = ? ,vitesse = ? ,clim = ? ,categorie_id = ? ,carburant_id = ?, state_id = ? where id = ?");
        ps.setString(1, vt.getMarque());
        ps.setString(2,vt.getModel());
        ps.setInt(3,vt.getKilometers());
        ps.setBoolean(4,vt.isEndommage());
        ps.setBoolean(5,vt.isVitesse());
        ps.setBoolean(6,vt.isClim());
        ps.setInt(7,cp.getIdCategorie(vt.getCategorie()));
        ps.setInt(8,carbup.getIdCarbu(vt.getCarburant()));
        ps.setInt(9,stp.getIdState(vt.getState()));
        ps.setInt(10,id);
        return ps.executeUpdate();
    }

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
