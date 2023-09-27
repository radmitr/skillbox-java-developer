package completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 5. Добавление нескольких callback
 *
 * Для добавления несколько callback метод thenAccept() не подоходит,
 * поскольку Consumer ничего не возвращает. Для этого нужно использовать
 * другой метод - thenApply(), который принимает Function.
 *
 * https://vertex-academy.com/tutorials/ru/java-8-completablefuture/
 */
public class Ex1_5 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hi");

        // 1
//        future.thenApply(result -> {
//            System.out.println(result + " all"); // output: Hi all
//            return result;
//        });
//
//        future.thenApply(result -> {
//            System.out.println(result + ", world!"); // output: Hi, world!
//            return result;
//        });

        // 2
        future.thenApply(result -> {
            System.out.println(result + " all"); // output: Hi all
            return result + " all";
        }).thenApply(result -> {
            System.out.println(result + ", world!"); // output: Hi, world!
            return result;
        });

        future.get();
    }
}
