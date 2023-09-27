package completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 4. Добавление callback
 *
 * Более приемлемым способом обработать результат работы CompletableFuture
 * есть callback. Если после выполенения задачи мы хотим вывести ее на екран,
 * это будет выглядеть так.
 *
 * get() вызывается для того, чтобы подождать исполнения future. Его можно
 * заменить, к примеру, на Thread.sleep.
 *
 * https://vertex-academy.com/tutorials/ru/java-8-completablefuture/
 */
public class Ex1_4 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hi");

        future.thenAccept(result -> System.out.println(result));

        future.get();
//        Thread.sleep(1000);
    }
}
