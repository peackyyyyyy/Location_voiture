package value_object;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;

public class Facture {
    private final int price;
    private float reduction;
    private final int duree;
    private float finalprice;

    public Facture(int price, Date debut, Date fin){
        this.duree = (int) ChronoUnit.DAYS.between(debut.toInstant(), fin.toInstant());
        this.price = price;
        this.reduction = 1;
        this.finalprice = price*duree*reduction;
    }
    public Facture(int price, Date debut, Date fin, float reduction){
        this.duree = (int) ChronoUnit.DAYS.between(debut.toInstant(), fin.toInstant());
        this.price = price;
        this.finalprice = price*duree*reduction;
    }

    public int getPrice() {
        return price;
    }

    public int getDuree() {
        return duree;
    }

    public float getFinalprice() {
        return finalprice;
    }

    public void setFinalprice(int finalprice) {
        this.finalprice = finalprice;
    }

    public float getReduction() {
        return reduction;
    }

    public void setReduction(float reduction) {
        this.reduction = reduction;
    }

    @Override
    public String toString() {
        return "Facture{" +
                "price=" + price +
                ", reduction=" + reduction +
                ", duree=" + duree +
                ", finalprice=" + finalprice +
                '}';
    }
}
