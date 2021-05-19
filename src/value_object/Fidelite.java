package value_object;
import java.util.Date;

public class Fidelite {
    private final int id;
    private Date debut;
    private Date fin;
    private final String description;
    private final int price;
    private final float reduction;

    public Fidelite(Date debut, Date fin, String description, int price, float reduction, int id){
        this.id = id;
        this.debut = debut;
        this.fin = fin;
        this.description = description;
        this.price = price;
        this.reduction = reduction;
    }

    public int getId() {
        return id;
    }

    public float getReduction() {
        return reduction;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public Date getDebut() {
        return debut;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Fidelite{" +
                "id=" + id +
                ", debut=" + debut +
                ", fin=" + fin +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", reduction=" + reduction +
                '}';
    }
}
