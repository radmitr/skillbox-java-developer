package executors;

import java.util.concurrent.*;

// 11.15 Executors, Executor и ExecutorService
// 11.16 Метод Shutdown
public class Main2 {

    // runnable and callable tasks
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<Double> future = executorService.submit(() -> {
            double sum = 0;
            for (int i = 0; i < 100_000; i++) {
                sum += Math.random();
            }
            return sum;
        });

        executorService.shutdown();

        System.out.println(future.get());
    }
}
