package value_object;

public class Adresse {
    private final String rue;
    private final String ville;
    private final String codepostal;

    public Adresse(String rue, String ville, String codepostal) {
        this.rue = rue;
        this.ville = ville;
        this.codepostal = codepostal;
    }

    public Adresse(String str) {
        //23 rue lol;Paris;75010
        String[] strsplit = str.split(";");
        this.rue = strsplit[0];
        this.ville = strsplit[1];
        this.codepostal = strsplit[2];

    }

    public String getStrToBdd() {
        return this.rue + ";" + this.ville + ";" + codepostal;
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
        return rue + ";"+ ville + ";"+ codepostal;
    }
}
