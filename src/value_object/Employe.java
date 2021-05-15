package value_object;

public class Employe extends Personne{
    protected String login;
    protected String mdp;

    Employe(String name, String surname, String email, Adresse adresse, String phone){
        super(name, surname, email, adresse, phone);
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
}
