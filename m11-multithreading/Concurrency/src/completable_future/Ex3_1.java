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
 * 2. Исполнение нескольких CompletableFuture
 *
 * Если мы захотим запустить несколько CompletableFuture параллельно,
 * то сделать это можно с помощью allOf().
 *
 * Но стоит помнить, что метод allOf() лишь запускает переданные задачи.
 * Результат работы нужно получать у каждого CompletableFuture отдельно.
 *
 * https://vertex-academy.com/tutorials/ru/java-8-completablefuture-chast-3/
 */
public class Ex3_1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> what = CompletableFuture.supplyAsync(() -> "What");
        CompletableFuture<String> the = CompletableFuture.supplyAsync(() -> "the");
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "future");
        CompletableFuture<String> holds = CompletableFuture.supplyAsync(() -> "holds");

        CompletableFuture<Void> all = CompletableFuture.allOf(what, the, future, holds);

        all.get();

        System.out.println(what.isDone()); // true
        System.out.println(the.isDone()); // true
        System.out.println(future.isDone()); // true
        System.out.println(holds.isDone()); // true
    }
}
