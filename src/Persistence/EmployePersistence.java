package Persistence;

import value_object.Adresse;
import value_object.Employe;
import value_object.Voiture;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployePersistence extends JdbcConnexion {
    Statement conn;
    public EmployePersistence(Statement conn) throws ClassNotFoundException, SQLException {
        this.conn = conn;
    }

    public Employe getEmployeAvecId(int id) throws SQLException {
        if(id == 0)
            return null;
        Statement con = super.getConn();
        ResultSet rs = con.executeQuery("Select * from employe where id="+id);
        rs.next();
        return new Employe(
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("email"),
                new Adresse(rs.getString("adresse")),
                rs.getString("phone"),
                rs.getString("login"),
                rs.getString("mdp")
        );

    }
}
