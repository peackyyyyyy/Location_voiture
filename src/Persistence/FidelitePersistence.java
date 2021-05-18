package Persistence;

import value_object.Fidelite;
import value_object.Voiture;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class FidelitePersistence extends JdbcConnexion {

    Statement conn;
    public FidelitePersistence(Statement conn) throws ClassNotFoundException, SQLException {
        this.conn = conn;
    }

    public Fidelite getFideliteAvecId(Integer id) throws SQLException {
        if(id == 0)
            return null;

        ResultSet rs = conn.executeQuery("Select * from fidelite where id="+id);
        rs.next();
        System.out.println(rs.getDate("date").getTime());
        return new Fidelite(
                id,
                new Date(rs.getDate("date").getTime()),
                rs.getInt("duree"),
                rs.getString("description"),
                rs.getInt("price"),
                rs.getFloat("reduction")
        );

    }
}
