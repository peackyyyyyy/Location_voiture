package value_object;

import java.util.Date;

public class Facture {
    private final int price;
    private final float reduction;
    private final int duree;
    private float finalprice;

    public Facture(int price, float reduction, Date debut, Date fin){
        this.duree = fin.compareTo(debut);
        this.price = price;
        this.reduction = reduction;
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
