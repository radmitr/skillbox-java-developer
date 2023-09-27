package completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 3. Объединение с thenCombine()
 *
 * Если мы хотим по завершению двух задач выполнить третью, то это можно
 * сделать с помощью thenCombine().
 *
 * https://vertex-academy.com/tutorials/ru/java-8-completablefuture-part-2/
 */
public class Ex2_2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> anotherFuture = CompletableFuture.supplyAsync(() -> 2);
        CompletableFuture<Integer> result = future.thenCombine(anotherFuture, (a, b) -> a * b);

        System.out.println(result.get()); // output: 20
    }
}
