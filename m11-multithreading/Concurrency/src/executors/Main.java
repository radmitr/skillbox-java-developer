package executors;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

// 11.15 Executors, Executor и ExecutorService
public class Main {

    // runnable task
    public static void main(String[] args) {
        Executor executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            for(;;) {
                System.out.println(Math.random());
            }
        });
    }
}
