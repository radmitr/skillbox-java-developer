package completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 1. Введение
 *
 * CompletableFuture - новый класс для асинхронной работы, который дает
 * возможность комбинировать шаги обработки, соединяя их в цепочку.
 * Класс содержит около 50 методов для выполнения, объединения а так же
 * обработки исключений.
 *
 * 2. Объединение с thenCompose()
 *
 * Объединять CompletableFuture можно несколькими способами, один из них (thenApply())
 * мы рассмотрели в предыдущей статье, а второй - с помощью thenCompose().
 *
 * Метод thenApply() является аналогом Optional.map(), только вот возвращает
 * новое CompletionStage, которое выполняется с результатом этого этапа.
 * В то время как thenCompose() является аналогом Optional.flatMap() и
 * возвращает новое CompletionStage, которое выполняется на этом этапе в качестве
 * аргумента для функции.
 *
 * https://vertex-academy.com/tutorials/ru/java-8-completablefuture-part-2/
 */
public class Ex2_1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 10)
                .thenCompose(result ->
                        CompletableFuture.supplyAsync(() -> result * 2)
                ).thenCompose(result ->
                        CompletableFuture.supplyAsync(() -> result * 5)
                );
        System.out.println(future.get()); // output: 100
    }
}
