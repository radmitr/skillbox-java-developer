package volatile_keyword;

public class Task implements Runnable {

    private long counter;

    // 1
//    private boolean isRunning; // переменная закеширована, поэтому остановки потока Task не происходит
    // 2 - volatile
    private volatile boolean isRunning; // переменная не кешируется

    public Task() {
        isRunning = true;
    }

    @Override
    public void run() {
        System.out.println("Task begin");
        while (isRunning) {
            counter++;
        }
        System.out.println("volatile_keyword.Task  increments: " + counter);
        System.out.println("Task end");
    }

    public void stop() {
        isRunning = false;
    }

    public long getCounter() {
        return counter;
    }
}
