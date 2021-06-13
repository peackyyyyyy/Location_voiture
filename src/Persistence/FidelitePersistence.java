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

    /**
     * Créer une fidélité
     * @param rs le résultat de la base de donnée
     * @return la fidélité créer
     * @throws SQLException
     * @throws ParseException
     */
    public Fidelite createFidelite(ResultSet rs) throws SQLException, ParseException {
        return new Fidelite(
                Utilities.strToDate(rs.getString("date")),
                Utilities.strToDate(rs.getString("fin")),
                rs.getString("description"),
                rs.getInt("price"),
                rs.getFloat("reduction"),
                rs.getInt("id")
        );
    }

    /**
     * Avoir une fidélité par rapport à son id
     * @param id l'id de la fidélité voulu
     * @return la fidelite voulu
     * @throws SQLException
     * @throws ParseException
     */
    public Fidelite getFideliteAvecId(Integer id) throws SQLException, ParseException {
        ResultSet rs = conn.executeQuery("Select * from fidelite where id="+id);
        if(rs.next() == false)
            return null;
        return createFidelite(rs);
    }

    /**
     * Lister toutes les fidélités en base
     * @return la liste des fidelites
     * @throws SQLException
     * @throws ParseException
     */
    public ArrayList<Fidelite> getFidelites() throws SQLException, ParseException {
        ArrayList<Fidelite> listeFidelite = new ArrayList<Fidelite>();
        ResultSet rs = conn.executeQuery("Select * from fidelite");
        while(rs.next()){
            listeFidelite.add(createFidelite(rs));
        }
        return listeFidelite;
    }

    /**
     * Insérer une fidelite en base
     * @param fidelite la fidelite que l'on veut mettre
     * @return l'id de la fidelité generer
     * @throws SQLException
     */
    public int insertFidelite(Fidelite fidelite) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("insert into fidelite (date,description,price,reduction,fin) values (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, Utilities.dateToString(fidelite.getDebut()));
        ps.setString(2, fidelite.getDescription());
        ps.setFloat(3,fidelite.getPrice());
        ps.setFloat(4,fidelite.getReduction());
        ps.setString(5,Utilities.dateToString(fidelite.getFin()));

        int retid = ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    /**
     * Chnager les informations d'une fidelité
     * @param id l'id de la fidelité à changer
     * @param fidelite les informations à mettre
     * @return le nombre de ligne affecté
     * @throws SQLException
     */
    public int updateFidelite(int id, Fidelite fidelite) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("update fidelite set date = ? ,description = ? , price = ? ,reduction = ? , fin = ?  where id = ?");
        ps.setString(1, Utilities.dateToString(fidelite.getDebut()));
        ps.setString(2, fidelite.getDescription());
        ps.setFloat(3,fidelite.getPrice());
        ps.setFloat(4,fidelite.getReduction());
        ps.setString(5,Utilities.dateToString(fidelite.getFin()));
        ps.setInt(6,id);
        return ps.executeUpdate();
    }

    /**
     * Supprimer une fidelité en base
     * @param id l'iude de la fidélité à supprimer
     * @return si l'opération s'est bien passé
     * @throws SQLException
     */
    public boolean deleteFidelite(int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("delete from fidelite where id = ?");
        ps.setInt(1,id);
        return ps.execute();
    }

}
