package value_object;

public class Personne {

    protected String name;
    protected String surname;
    protected String email;
    protected Adresse adresse;
    protected String phone;


    Personne(String name, String surname, String email, Adresse adresse, String phone){
            this.name = name;
            this.surname = surname;
            this.email = email;
            this.adresse = adresse;
            this.phone = phone;
    }


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
