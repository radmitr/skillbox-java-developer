package completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 3. Получение результата
 *
 * Для того, чтобы получить результат с CompletableFuture необходимо
 * вызвать метод get().
 *
 * Но стоит помнить, что вызов этого метода блокирующий, поток будет
 * ждать пока CompletableFuture не вернет результат и о асинхронности можно забыть.
 *
 * https://vertex-academy.com/tutorials/ru/java-8-completablefuture/
 */
public class Ex1_3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500); // имитируем долгое выполнение
            } catch (InterruptedException e) {}
            return "Hi";
        });
        System.out.println(future.get()); // output: Hi
    }
}
