package value_object;

public class Agence extends Adresse{
    private final int id;
    private final String name;
    private final String phone;
    private final String longitude;
    private final String lattitude;


    public Agence(String rue, String ville, String codepostal, int id, String name, String phone, String longitude,
                  String lattitude) {
        super(rue, ville, codepostal);
        this.longitude = longitude;
        this.lattitude = lattitude;
        this.name = name;
        this.phone = phone;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getLattitude() {
        return lattitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Agence{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", longitude='" + longitude + '\'' +
                ", lattitude='" + lattitude + '\'' +
                '}';
    }
}
