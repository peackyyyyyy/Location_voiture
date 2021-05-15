package value_object;

public class Fidelite {
    protected String date;
    protected int duree;
    protected String description;
    protected int price;
    protected float reduction;

    Fidelite(String date, int duree, String description, int price, float reduction){
        this.date = date;
        this.duree = duree;
        this.description = description;
        this.price = price;
        this.reduction = reduction;
    }

    public float getReduction() {
        return reduction;
    }

    public int getDuree() {
        return duree;
    }

    public int getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
