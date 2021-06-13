package Persistence;

import value_object.Adresse;
import value_object.Employe;
import value_object.Voiture;

import java.sql.*;
import java.util.ArrayList;

public class EmployePersistence extends JdbcConnexion {
    private Statement conn;
    private Connection connexion;
    public EmployePersistence(Statement conn, Connection connexion) throws ClassNotFoundException, SQLException {
        this.conn = conn;
        this.connexion = connexion;
    }

    /**
     * Créer un employé en base
     * @param rs le résultat de la base de donnée
     * @return l'employé créée
     * @throws SQLException
     */
    private Employe createEmploye(ResultSet rs) throws SQLException {
        return new Employe(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("email"),
                new Adresse(rs.getString("adresse")),
                rs.getString("phone"),
                rs.getString("login"),
                rs.getString("mdp"));
    }

    /**
     * Avoir un employé avec son id
     * @param id l'id de l'employé créé
     * @return l'employé créer
     * @throws SQLException
     */
    public Employe getEmployeAvecId(int id) throws SQLException {
        if(id == 0)
            return null;
        ResultSet rs = conn.executeQuery("Select * from employe where id="+id);
        if(rs.next() == false)
            return null;
        return  createEmploye(rs);
    }

    /**
     * lister tous les employées en base
     * @return la liste des employés
     * @throws SQLException
     */
    public ArrayList<Employe> getEmployes() throws SQLException {
        ArrayList<Employe> liste = new ArrayList<Employe>();
        ResultSet rs = conn.executeQuery("Select * from employe");
        while(rs.next())
            liste.add(createEmploye(rs));
        return liste;
    }

    /**
     * Insérer un employé en base
     * @param emp l'employé à mettre
     * @return l'id de l'employé créer
     * @throws SQLException
     */
    public boolean insertEmploye(Employe emp) throws SQLException {
        PreparedStatement ps = connexion.prepareStatement("insert into employe (name,surname,email,adresse,phone,login,mdp) values (?,?,?,?,?,?,?)");
        ps.setString(1,emp.getName());
        ps.setString(2,emp.getSurname());
        ps.setString(3,emp.getEmail());
        Adresse ad = emp.getAdresse();
        ps.setString(4,ad.getRue()+";"+ad.getVille()+";"+ad.getCodepostal());
        ps.setString(5,emp.getPhone());
        ps.setString(6,emp.getLogin());
        ps.setString(7,emp.getMdp());
        return ps.execute();
    }

    /**
     * Changer les informations d'un employé
     * @param id l'id de l'employé à changer
     * @param emp les informations à mettre
     * @return le nombre de ligne affecté
     * @throws SQLException
     */
    public int updateEmploye(int id, Employe emp) throws SQLException {
        PreparedStatement ps = connexion.prepareStatement("update employe set name = ? , surname = ? ,email = ? ,adresse = ? ,phone = ? ,login = ? ,mdp = ? where id = ?");
        ps.setString(1,emp.getName());
        ps.setString(2,emp.getSurname());
        ps.setString(3,emp.getEmail());
        Adresse ad = emp.getAdresse();
        ps.setString(4,ad.getRue()+";"+ad.getVille()+";"+ad.getCodepostal());
        ps.setString(5,emp.getPhone());
        ps.setString(6,emp.getLogin());
        ps.setString(7,emp.getMdp());
        ps.setInt(8,id);
        return ps.executeUpdate();
    }

    /**
     * Supprimer un employé en base
     * @param id l'id de l'employé a supprimer
     * @return si l'opération s'est bien passé
     * @throws SQLException
     */
    public boolean deleteEmploye(int id) throws SQLException {
        PreparedStatement ps = connexion.prepareStatement("delete from employe where id = ?");
        ps.setInt(1,id);
        return ps.execute();

    }
}
