package wait_and_notify.bad;

public class Producer implements Runnable {

    private Parking parking;

    public Producer(Parking parking) {
        this.parking = parking;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            parking.in();
        }
    }
}
