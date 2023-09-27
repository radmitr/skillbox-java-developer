package completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * 2. Простейший CompletableFuture
 *
 * и runAsync()
 *
 * Разница в том, что supplyAsync() принимает Supplier, а runAsync -> Runnable.
 * Проще говоря, с помощью supplyAsync() можно вернуть результат, с runAsync() - нельзя.
 *
 * https://vertex-academy.com/tutorials/ru/java-8-completablefuture/
 */
public class Ex1_2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1 - runAsync()
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(
                () -> System.out.println("Hi"));

        // 2 - runAsync() c Executors.newCachedThreadPool()
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(
                () -> System.out.println("Hi"), Executors.newCachedThreadPool());
    }
}
