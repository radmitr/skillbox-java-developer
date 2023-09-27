package interrupt.interrupter;

public class Processor extends Thread {

    double sum = 0;
    @Override
    public void run() {
        for (;;) {
            if (isInterrupted()) {
                System.out.println(sum);
                break;
            }
            sum += Math.random();
        }
    }
}
