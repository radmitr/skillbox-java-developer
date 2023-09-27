package completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * 1. Введение
 *
 * CompletableFuture - новый класс для асинхронной работы, который дает
 * возможность комбинировать шаги обработки, соединяя их в цепочку.
 * Класс содержит около 50 методов для выполнения, объединения а так же обработки
 * исключений. Рассмотрим мы лишь некоторые из них.
 *
 * 2. Простейший CompletableFuture
 *
 * Для создания CompletableFuture можно воспользоваться методами supplyAsync(),
 * где future исполнится в ForkJoinPool.commonPool(), т.к. мы не указывали ему Executor.
 * Если мы хотим указать где будет исполняться future то передаем Executor вторым параметром.
 *
 * https://vertex-academy.com/tutorials/ru/java-8-completablefuture/
 */
public class Ex1_1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1 - supplyAsync()
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(
                () -> "Hi");

        // 2 - supplyAsync() c Executors.newCachedThreadPool()
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(
                () -> "Hi", Executors.newCachedThreadPool());
    }
}
