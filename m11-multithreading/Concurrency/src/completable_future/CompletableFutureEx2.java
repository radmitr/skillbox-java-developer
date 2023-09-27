package completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * В этом коде используется интересная возможность CompletableFuture из
 * пакета java.util.concurrent. CompletableFuture «умеет» запускать код
 * в параллельном потоке, и при этом использует встроенный thread pool.
 * Его использование упрощает код и даёт прирост в производительности
 * приложения.
 *
 * Ведь на создание нового потока не расходуются системные ресурсы.
 * Более того, встроенный thread pool уже оптимизирован и работает
 * эффективнее большинства пулов, созданных пользователем.
 *
 * Однако надо помнить и об ограничениях. Например, разные модули
 * программы могут конкурировать за потоки из общего пула, что может
 * привести к дополнительным ожиданиям.
 *
 * https://otus.ru/nest/post/192/
 */
public class CompletableFutureEx2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        asyncRun();
    }

    private static void asyncRun() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> future = CompletableFuture.supplyAsync(()-> {
            System.out.println("job started");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("job done");
            return "feature done";
        });

        System.out.println("waiting...");
        String result = future.get();
        System.out.println("finished, result: " + result);
    }
}
