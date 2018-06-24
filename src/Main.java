import java.util.concurrent.TimeUnit;

/**
 * Created by romy on 24.06.2018.
 */
public class Main {

    public static void main(String a[]) {
        BarberShop shop = new BarberShop(5);
        Barber barber = new Barber(shop);
        new Thread(barber).start();

        int i = 1;
        while (true) {
            Customer customer = new Customer("Customer " + i++, shop);
            new Thread(customer).start();
            try {
                TimeUnit.SECONDS.sleep((long) (Math.random() * 8));
            } catch (InterruptedException iex) {
                iex.printStackTrace();
            }
        }

    }
}
