package Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Calsse permettant de faire le lien avec le java et le SQL
 */
public class JdbcConnexion implements IConnexion{

    protected Statement conn;
    protected Connection connexion;

    /**
     * Constructeur de la connexion avec la base de donnée
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public JdbcConnexion() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection con = DriverManager.
                    getConnection("jdbc:mariadb://achetez.ml:3306/rentacar"
                            , "hugo", "labibine");
            this.connexion = con;
            Statement stmt = con.createStatement();
            conn = stmt;
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
    public Statement getConn() {
        return conn;
    }

    public void setConn(Statement conn) {
        this.conn = conn;
    }

    public Connection getConnexion() {
        return connexion;
    }

    public void setConnexion(Connection connexion) {
        this.connexion = connexion;
    }
}