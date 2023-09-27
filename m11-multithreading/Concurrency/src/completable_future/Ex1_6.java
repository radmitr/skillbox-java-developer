package completable_future;

import java.util.concurrent.CompletableFuture;

/**
 * 5. Добавление нескольких callback
 *
 * Нужно помнить, что функция в thenApply() исполняется в том же потоке,
 * где вызывается. Если же использовать thenApplyAsync(), тогда функция
 * будет исполнена как отдельная задача в ForkJoinPool.commonPool .
 *
 * https://vertex-academy.com/tutorials/ru/java-8-completablefuture/
 */
public class Ex1_6 {

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hi");

        System.out.printf("[%s]\n", Thread.currentThread().getName()); // output: [t]

        future.thenApplyAsync(result -> {
            System.out.printf("[%s] %s all\n", Thread.currentThread().getName(), result); // output: [t] Hi all
            return result;
        });

        Thread.sleep(100);
    }
}
