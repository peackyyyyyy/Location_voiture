package value_object;
import java.util.Date;

public class Fidelite {
    private Date date;
    private final int duree;
    private final String description;
    private final int price;
    private final float reduction;

    public Fidelite(Date date, int duree, String description, int price, float reduction){
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

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
