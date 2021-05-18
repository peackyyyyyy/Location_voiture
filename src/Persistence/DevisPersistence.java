package Persistence;

import java.sql.SQLException;
import java.sql.Statement;

public class DevisPersistence extends JdbcConnexion{
    Statement conn;
    public DevisPersistence(Statement conn) throws ClassNotFoundException, SQLException {
        this.conn = conn;
    }
}
