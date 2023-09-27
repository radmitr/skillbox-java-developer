package wait_and_notify.bad;

public class Parking {

    private int parkingSize = 500;
    private int carCount = 0;

    public synchronized void in() {
        carCount++;
        System.out.println("Автомобиль завезён");
        System.out.println("Свободных мест: " + (parkingSize - carCount));
    }

    public synchronized void out() {
        carCount--;
        System.out.println("Автомобиль вывезён");
        System.out.println("Свободных мест: " + (parkingSize - carCount));
    }
}
