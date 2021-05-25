package value_object;

public class Personne {

    private final String name;
    private final String surname;
    private String email;
    private Adresse adresse;
    private String phone;


    public Personne(String name, String surname, String email, Adresse adresse, String phone){
            this.name = name;
            this.surname = surname;
            this.email = email;
            this.adresse = adresse;
            this.phone = phone;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setPhone(String phone) {
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

    @Override
    public String toString() {
        return "Personne{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", adresse=" + adresse +
                ", phone='" + phone + '\'' +
                '}';
    }
}
