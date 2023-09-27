package callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

// 11.14 Интерфейс Callable
public class Main {

    public static void main(String[] args) {
        // return
        Callable<Double> callable = () -> {
            double sum = 0;
            for (int i = 0; i < 1000; i++) {
                sum += Math.random();
            }
            return sum / 1000;
        };

        FutureTask<Double> futureTask = new FutureTask<>(callable);

//        new Thread(callable).start(); // ошибка!!! Thread принимает только Runnable
        new Thread(futureTask).start();

        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
