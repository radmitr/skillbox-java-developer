package completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 4. Обработка исключений
 *
 * и exceptionally()
 *
 * где вариант с handle() является более гибким, и функция выполнится в любом
 * случае в независимости от того будет исключение или нет; в то время как exceptionally()
 * выполнится только в случае исключения.
 *
 * https://vertex-academy.com/tutorials/ru/java-8-completablefuture-part-2/
 */
public class Ex2_4 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture.supplyAsync(() -> {
                    throw new RuntimeException("error in async running");
                })
                .exceptionally(err -> (5))
                .thenAccept(System.out::println); // output: 5
    }
}
