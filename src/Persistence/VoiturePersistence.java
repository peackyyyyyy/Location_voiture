package Persistence;

import value_object.Categorie.Luxe;
import value_object.ICategorie;
import value_object.Voiture;
import value_object.model.Enumeration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VoiturePersistence extends JdbcConnexion{

    Statement conn;
    CategoriePersistence cp;
    CarburantPersistence carbup;

    public VoiturePersistence(Statement conn, CategoriePersistence cp, CarburantPersistence carbup) throws ClassNotFoundException, SQLException {
        this.conn = conn;
        this.cp = cp;
        this.carbup = carbup;
    }
    public Voiture getVoitureAvecId(Integer id) throws SQLException {
        if(id == 0)
            return null;
        Statement con = super.getConn();
        ArrayList<Voiture> listeVoitures = new ArrayList<Voiture>();
        ResultSet rs = con.executeQuery("Select * from voiture where id="+id);
        Voiture vt;
        rs.next();
        return new Voiture(
                rs.getInt("id"),
                rs.getString("marque"),
                rs.getString("model"),
                rs.getInt("kilometers"),
                cp.getCategorieAvecId(rs.getInt("categorie_id")),
                rs.getInt("vitesse")==1?true:false,
                rs.getInt("clim")==1?true:false,
                carbup.getCarburantAvecId(rs.getInt("carburant_id")),
                rs.getInt("endommage")==1?true:false);

    }
    public ArrayList<Voiture> getVoitures() throws SQLException {
        Statement con = super.getConn();
        ArrayList<Voiture> listeVoitures = new ArrayList<Voiture>();
        ResultSet rs = con.executeQuery("Select * from voiture");
        Voiture vt;
        while(rs.next()){
            vt = new Voiture(
                    rs.getInt("id"),
                    rs.getString("marque"),
                    rs.getString("model"),
                    rs.getInt("kilometers"),
                    cp.getCategorieAvecId(rs.getInt("categorie_id")),
                    rs.getInt("vitesse")==1?true:false,
                    rs.getInt("clim")==1?true:false,
                    carbup.getCarburantAvecId(rs.getInt("carburant_id")),
                    rs.getInt("endommage")==1?true:false);

            listeVoitures.add(vt);
        }
        return listeVoitures;
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
