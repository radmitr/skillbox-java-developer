package completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * У CompletableFuture есть ещё интересные функции. Например, надо построить
 * цепочку из асинхронных вызовов. Т.е. после завершения первой асинхронной
 * функции запустить вторую, после второй третью и т.д. В JavaScript для этого
 * применяются promise. В Java можно использовать CompletableFuture.
 *
 * Сначала выполняется первая задача (sleep(3)) и только после её завершения
 * запустится вторая задача. Причём второй задаче в качестве входного параметра
 * можно передать результаты первой задачи.
 *
 * https://otus.ru/nest/post/193/
 */
public class CompletableFutureEx3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        thenApply();
    }

    private static void thenApply() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> future = CompletableFuture.supplyAsync(()-> {
            System.out.println("job started");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("job done");
            return "feature done!";
        }).thenApply(result -> {
            System.out.println("apply result: " + result);
            return result + " applied";
        });

        System.out.println("waiting...");
        String result = future.get();
        System.out.println("finished, result: " + result);
    }
}
