package callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

// 11.14 Интерфейс Callable
public class Main2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // exception
        Callable<Double> callable = () -> {
            double sum = 0;
            for (int i = 0; i < 1000; i++) {
                sum += Math.random();
            }

            double avgValue = sum / 1000;

            if (avgValue < 0.4999 || avgValue > 0.5001) {
                throw new IllegalArgumentException("large variance, avgValue = " + avgValue);
            }
            return avgValue;
        };

        FutureTask<Double> futureTask = new FutureTask<>(callable);

        new Thread(futureTask).start();

        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
