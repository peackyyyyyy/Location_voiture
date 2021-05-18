package Persistence;

import value_object.Adresse;
import value_object.Client;
import value_object.Fidelite;
import value_object.Voiture;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClientPersistence extends JdbcConnexion{

    VoiturePersistence vp;
    FidelitePersistence fp;
    Statement conn;

    public ClientPersistence(Statement conn, VoiturePersistence vp, FidelitePersistence fp) throws ClassNotFoundException, SQLException {
        this.conn = conn;
        this.vp = vp;
        this.fp = fp;
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

    public Client getClientWithId(int id) throws SQLException {
        Statement con = super.getConn();
        ResultSet rs = con.executeQuery("Select * from client where id="+id);
        rs.next();
        Integer fideliteId = rs.getInt("fidelite_id");
        Integer voitureId = rs.getInt("voiture_id");
        return new Client(
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("email"),
                new Adresse(rs.getString("adresse")),
                rs.getString("phone"),
                vp.getVoitureAvecId(voitureId),
                fp.getFideliteAvecId(fideliteId)
        );


    }
}
