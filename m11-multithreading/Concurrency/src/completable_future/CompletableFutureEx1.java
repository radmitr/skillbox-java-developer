package completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Время от времени появляется необходимость выполнить асинхронно (т.е. одновременно
 * с основным действием программы выполняется другая задача) какой-нибудь код.
 * Например, отправить письмо по e-mail или что-нибудь из базы данных прочитать.
 * В Java для подобных операций можно создать поток и выполнить в нём нужное действие.
 *
 * Просто создаётся поток и имитируется задача длительностью 3 сек.: вызов
 * Thread.sleep(TimeUnit.SECONDS.toMillis(sec));
 * с перехваченным исключением. Задача возвращает результат через CompletableFuture.
 *
 * https://otus.ru/nest/post/192/
 */
public class CompletableFutureEx1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        simpleRun();
    }

    private static void simpleRun() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> future = new CompletableFuture<>();

        new Thread(() -> {
            System.out.println("job started");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            future.complete("feature done");
            System.out.println("job done");
        }).start();

        System.out.println("waiting...");
        String result = future.get();
        System.out.println("finished, result: " + result);
    }
}
