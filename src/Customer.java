/**
 * Created by romy on 24.06.2018.
 */
public class Customer implements Runnable {
    String name;
    BarberShop shop;

    public Customer(String name, BarberShop shop) {
        this.name = name;
        this.shop = shop;
    }

    public void run() {
        goForHairCut();
    }

    private synchronized void goForHairCut() {
        shop.add(this);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
