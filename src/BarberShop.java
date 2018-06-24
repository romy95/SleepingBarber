import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * Created by romy on 24.06.2018.
 */
public class BarberShop {
    int numberOfChairs;
    Queue<Customer> waitingCustomers;

    public BarberShop(int chairNumber) {
        numberOfChairs = chairNumber;
        waitingCustomers = new LinkedList<>();
    }

    public void cutHair() {
        Customer customer;
        synchronized (waitingCustomers) {
            while (waitingCustomers.size() == 0) {
                System.out.println("Barber is waiting for a customer.");
                try {
                    waitingCustomers.wait();
                } catch (InterruptedException iex) {
                    iex.printStackTrace();
                }
            }
            System.out.println("Barber found a waiting customer.");
            customer = waitingCustomers.poll();
        }
        long duration = 0;
        try {
            System.out.println("Barber cuts the hair of " + customer + ".");
            duration = (long) (Math.random() * 10);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException iex) {
            iex.printStackTrace();
        }
        System.out.println("Barber completes hair cut of " + customer + " in " + duration + " seconds.");
    }

    public void add(Customer customer) {
        System.out.println(customer + " enters the shop.");
        synchronized (waitingCustomers) {
            if (waitingCustomers.size() == numberOfChairs) {
                System.out.println("No free chair for " + customer);
                System.out.println(customer + " is leaving.");
                return;
            }

            System.out.println(customer + " got the chair.");
            waitingCustomers.offer(customer);

            if (waitingCustomers.size() == 1) {
                waitingCustomers.notify();
            }
        }
    }
}
