package completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * У CompletableFuture есть встроенный механизм для обработки ошибок.
 *
 * Обратите внимание на блок exceptionally, он запустится, если одна
 * из задач выбросит исключение.
 *
 * https://otus.ru/nest/post/193/
 */
public class CompletableFutureEx4 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        thenApplyException();
    }

    private static void thenApplyException() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> future = CompletableFuture.supplyAsync(()-> {
            System.out.println("job started");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            throw new RuntimeException("runTime exception");
        }).thenApply(result -> {
            System.out.println("apply result: " + result);
            return result + " applied";
        }).exceptionally(exception -> {
            System.out.println("got exception, error: " + exception.getMessage());
            return exception.getMessage();
        });

        System.out.println("waiting...");
        String result = future.get();
        System.out.println("finished, result: " + result);
    }
}
