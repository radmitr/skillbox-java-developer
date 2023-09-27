package wait_and_notify.good;

public class Parking {

    private int parkingSize = 500;
    private int carCount = 0;

    // wait(), notify()
    public synchronized void in() {
        if (carCount == parkingSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        carCount++;
        System.out.println("Автомобиль завезён");
        System.out.println("Свободных мест: " + (parkingSize - carCount));
        notify();
    }

    // wait(), notify()
    public synchronized void out() {
        if (carCount == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        carCount--;
        System.out.println("Автомобиль вывезён");
        System.out.println("Свободных мест: " + (parkingSize - carCount));
        notify();
    }
}
