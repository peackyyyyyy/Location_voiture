//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import value_object.Voiture;

public class VoiturePersistence extends JdbcConnexion {
    Statement conn;
    CategoriePersistence cp;
    CarburantPersistence carbup;

    public VoiturePersistence(Statement conn, CategoriePersistence cp, CarburantPersistence carbup) throws ClassNotFoundException, SQLException {
        this.conn = conn;
        this.cp = cp;
        this.carbup = carbup;
    }

    public Voiture getVoitureAvecId(Integer id) throws SQLException {
        if (id == 0) {
            return null;
        } else {
            Statement con = super.getConn();
            new ArrayList();
            ResultSet rs = con.executeQuery("Select * from voiture where id=" + id);
            rs.next();
            return new Voiture(rs.getInt("id"), rs.getString("marque"), rs.getString("model"), rs.getInt("kilometers"), this.cp.getCategorieAvecId(rs.getInt("categorie_id")), rs.getInt("vitesse") == 1, rs.getInt("clim") == 1, this.carbup.getCarburantAvecId(rs.getInt("carburant_id")), rs.getInt("endommage") == 1);
        }
    }

    public ArrayList<Voiture> getVoitures() throws SQLException {
        Statement con = super.getConn();
        ArrayList<Voiture> listeVoitures = new ArrayList();
        ResultSet rs = con.executeQuery("Select * from voiture");

        while(rs.next()) {
            Voiture vt = new Voiture(rs.getInt("id"), rs.getString("marque"), rs.getString("model"), rs.getInt("kilometers"), this.cp.getCategorieAvecId(rs.getInt("categorie_id")), rs.getInt("vitesse") == 1, rs.getInt("clim") == 1, this.carbup.getCarburantAvecId(rs.getInt("carburant_id")), rs.getInt("endommage") == 1);
            listeVoitures.add(vt);
        }

        return listeVoitures;
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

