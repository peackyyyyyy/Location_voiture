package value_object;

public class Adresse {
    private final String rue;
    private final String ville;
    private final String codepostal;

    public Adresse(String rue, String ville, String codepostal){
        this.rue = rue;
        this.ville = ville;
        this.codepostal = codepostal;
    }

    public String getCodepostal() {
        return codepostal;
    }

    public String getRue() {
        return rue;
    }

    public String getVille() {
        return ville;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "rue='" + rue + '\'' +
                ", ville='" + ville + '\'' +
                ", codepostal=" + codepostal +
                '}';
    }
}
