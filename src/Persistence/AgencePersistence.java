package Persistence;

import value_object.Agence;

import java.sql.*;
import java.util.ArrayList;

public class AgencePersistence {

    private Statement con;
    private Connection connexion;


    public AgencePersistence(Statement con, Connection connexion) throws SQLException, ClassNotFoundException {
        this.con = con;
        this.connexion = connexion;
    }

    /**
     * Methode qui crée une agence
     * @param rs le retour de la base de donnée
     * @return l'agence créée
     * @throws SQLException
     */
    private Agence createAgence(ResultSet rs) throws SQLException {
        return new Agence(
                rs.getString("rue"),
                rs.getString("ville"),
                rs.getString("codepostal"),
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("phone"),
                rs.getString("longitude"),
                rs.getString("lattitude")
        );
    }

    /**
     * Retourne la liste de toutes les agences en base
     * @return la liste de toutes les agences en base
     * @throws SQLException
     */
    public ArrayList<Agence> getAgences() throws SQLException {

        ArrayList<Agence> listeAgence = new ArrayList<Agence>();
        ResultSet rs = con.executeQuery("Select * from agence");
        while(rs.next())
            listeAgence.add(createAgence(rs));
        return listeAgence;
    }

    /**
     * Methode de retour d'une agence
     * @param id l'id de l'agence voulu
     * @return l'agence
     * @throws SQLException
     */
    Agence getAgenceWithId(int id) throws SQLException {
        ResultSet rs = con.executeQuery("Select * from agence where id ="+id);
        if(rs.next() == false)
            return null;
        return createAgence(rs);
    }

    /**
     * Insertion d'une agence dans la base de donnéés
     * @param agence l'agence à mettre
     * @return l'id de l'agence créer
     * @throws SQLException
     */
    public int insertAgence(Agence agence) throws SQLException {
        PreparedStatement ps = connexion.prepareStatement("Insert into agence (name,phone,longitude,lattitude,rue,ville,codepostal) values (?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
        ps.setString(1,agence.getName());
        ps.setString(2,agence.getPhone());
        ps.setString(3,agence.getLongitude());
        ps.setString(4,agence.getLattitude());
        ps.setString(5,agence.getRue());
        ps.setString(6,agence.getVille());
        ps.setString(7,agence.getCodepostal());
        int retid = ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    /**
     * Changer les information d'une agence en base
     * @param id l'agence à changer
     * @param agence les infroamtions à mettre
     * @return le nombre de ligne modifiés
     * @throws SQLException
     */
    public int updateAgence(int id, Agence agence) throws SQLException {
        PreparedStatement ps = connexion.prepareStatement("update agence set name = ? , phone = ?, longitude = ?, lattitude = ?, rue = ?, ville = ?, codepostal = ? where id = ?");
        ps.setString(1,agence.getName());
        ps.setString(2,agence.getPhone());
        ps.setString(3,agence.getLongitude());
        ps.setString(4,agence.getLattitude());
        ps.setString(5,agence.getRue());
        ps.setString(6,agence.getVille());
        ps.setString(7,agence.getCodepostal());
        ps.setInt(8,id);
        return ps.executeUpdate();
    }

    /**
     * Supprimer une agence en base
     * @param id l'id de l'agence à supprmié
     * @return un boolean sur lma reussite de la fonction
     * @throws SQLException
     */
    public boolean deleteAgence(int id) throws SQLException {
        PreparedStatement ps = connexion.prepareStatement("delete from agence where id = ?");
        ps.setInt(1,id);
        return ps.execute();
    }
}

