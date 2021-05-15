package value_object;

public class Adresse {
    protected String rue;
    protected String ville;
    protected int codepostal;

    Adresse(String rue, String ville, int codepostal){
        this.rue = rue;
        this.ville = ville;
        this.codepostal = codepostal;
    }

    public int getCodepostal() {
        return codepostal;
    }

    public String getRue() {
        return rue;
    }

    public String getVille() {
        return ville;
    }
}
