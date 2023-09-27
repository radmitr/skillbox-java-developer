package completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 4. Обработка исключений
 *
 * CompletableFuture также позволяет нам с легкостью обрабатывать исключения
 * с помощью методов handle()
 *
 * https://vertex-academy.com/tutorials/ru/java-8-completablefuture-part-2/
 */
public class Ex2_3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                    throw new RuntimeException("error in async running");
                }).handle((obj, err) -> {
                    System.out.print(err.getMessage());
                    return 10;
                });

        System.out.println(" " + future.get()); // output: java.lang.RuntimeException: error in async running 10
    }
}
