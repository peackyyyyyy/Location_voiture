package Persistence;

import value_object.Fidelite;
import value_object.Voiture;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class FidelitePersistence extends JdbcConnexion {

    Statement conn;
    Connection connection;
    public FidelitePersistence(Statement conn, Connection connection) throws ClassNotFoundException, SQLException {
        this.conn = conn;
        this.connection = connection;
    }

    public Fidelite createFidelite(ResultSet rs) throws SQLException, ParseException {
        return new Fidelite(
                rs.getInt("id"),
                Utilities.strToDate(rs.getString("date")),
                rs.getInt("duree"),
                rs.getString("description"),
                rs.getInt("price"),
                rs.getFloat("reduction"));
    }

    public Fidelite getFideliteAvecId(Integer id) throws SQLException, ParseException {
        if(id == 0)
            return null;
        ResultSet rs = conn.executeQuery("Select * from fidelite where id="+id);
        rs.next();
        return createFidelite(rs);
    }

    public ArrayList<Fidelite> getFidelites() throws SQLException, ParseException {
        ArrayList<Fidelite> listeFidelite = new ArrayList<Fidelite>();
        ResultSet rs = conn.executeQuery("Select * from fidelite");
        while(rs.next()){
            listeFidelite.add(createFidelite(rs));
        }
        return listeFidelite;
    }

    public boolean insertFidelite(Fidelite fidelite) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("insert into fidelite (date,description,price,reduction,duree) values (?,?,?,?,?)");
        ps.setString(1, Utilities.dateToString(fidelite.getDate()));
        ps.setString(2, fidelite.getDescription());
        ps.setFloat(3,fidelite.getPrice());
        ps.setFloat(4,fidelite.getReduction());
        ps.setInt(5,fidelite.getDuree());
        return ps.execute();
    }

    public int updateFidelite(int id, Fidelite fidelite) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("update fidelite set date = ? ,description = ? , price = ? ,reduction = ? , duree = ?  where id = ?");
        ps.setString(1, Utilities.dateToString(fidelite.getDate()));
        ps.setString(2, fidelite.getDescription());
        ps.setFloat(3,fidelite.getPrice());
        ps.setFloat(4,fidelite.getReduction());
        ps.setInt(5,fidelite.getDuree());
        ps.setInt(6,id);
        return ps.executeUpdate();
    }

    public boolean deleteFidelite(int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("delete from fidelite where id = ?");
        ps.setInt(1,id);
        return ps.execute();
    }

}
