package wait_and_notify.good;

// 11.10 Взаимодействие потоков — методы Wait и Notify
public class Main {

    public static void main(String[] args) {
        Parking parking = new Parking();

        Thread thread1 = new Thread(new Producer(parking));
        Thread thread2 = new Thread(new Consumer(parking));

        thread1.start();
        thread2.start();
    }
}
