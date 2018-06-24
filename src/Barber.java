/**
 * Created by romy on 24.06.2018.
 */
public class Barber implements Runnable {
    BarberShop shop;

    public Barber(BarberShop shop) {
        this.shop = shop;
    }

    public void run() {
        System.out.println("Barber started..");
        while (true) {
            shop.cutHair();
        }
    }
}
