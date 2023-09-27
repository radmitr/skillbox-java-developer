package interrupt.interrupter;

public class Interrupter implements Runnable {

    private Thread thread;

    public Interrupter(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
