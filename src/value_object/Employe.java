package value_object;

public class Employe extends Personne{
    private String login;
    private String mdp;
    private int id;
    private String type;

    public Employe(String name, String surname, String email, Adresse adresse, String phone, String login, String mdp,String type){
        super(name, surname, email, adresse, phone);
        this.login = login;
        this.mdp = mdp;
        this.type=type;
    }

    public Employe(int id, String name, String surname, String email, Adresse adresse, String phone, String login, String mdp,String type){
        super(name, surname, email, adresse, phone);
        this.login = login;
        this.mdp = mdp;
        this.id = id;
        this.type=type;
    }

    public String getLogin() {
        return login;
    }

    public String getMdp() {
        return mdp;
    }

    public String getType() { return type; }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Employe{" +
                "login='" + login + '\'' +
                ", mdp='" + mdp + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
