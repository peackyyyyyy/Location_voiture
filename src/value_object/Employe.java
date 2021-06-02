package value_object;

public class Employe extends Personne{
    private String login;
    private String mdp;
    private int id;

    public Employe(String name, String surname, String email, Adresse adresse, String phone, String login, String mdp){
        super(name, surname, email, adresse, phone);
        this.login = login;
        this.mdp = mdp;
    }

    public Employe(int id, String name, String surname, String email, Adresse adresse, String phone, String login, String mdp){
        super(name, surname, email, adresse, phone);
        this.login = login;
        this.mdp = mdp;
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public String toString() {
        return "Employe{" +
                "login='" + login + '\'' +
                ", mdp='" + mdp + '\'' +
                '}';
    }
}
