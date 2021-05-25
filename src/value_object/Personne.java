package value_object;

public class Personne {

    private final String name;
    private final String surname;
    private final String email;
    private final Adresse adresse;
    private final String phone;


    public Personne(String name, String surname, String email, Adresse adresse, String phone){
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
