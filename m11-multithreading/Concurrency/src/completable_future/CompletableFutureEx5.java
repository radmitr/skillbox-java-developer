package completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * У CompletableFuture есть ещё интересная возможность: запускать несколько
 * асинхронных задач, подождать, когда они завершатся и обработать полученные результаты.
 *
 * Обратите внимание на структуру futureOne.thenAcceptBothAsync(...). Ждём, когда
 * завершатся обе задачи, и обрабатываем итоговый результат. В отличие от предыдущих
 * примеров, в этом фрагменте кода поток выполнения программы не ждёт, когда
 * завершатся future, а идёт дальше.
 *
 * https://otus.ru/nest/post/193/
 */
public class CompletableFutureEx5 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        acceptBoth();
    }

    private static void acceptBoth() {
        final CompletableFuture<String> futureOne = CompletableFuture.supplyAsync(()-> {
            System.out.println("job one started");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("job one is done");
            return "feature done one";
        });

        final CompletableFuture<String> futureTwo = CompletableFuture.supplyAsync(()-> {
            System.out.println("job two started");
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("job two is done");
            return "feature done two";
        });

        System.out.println("waiting...");
        futureOne.thenAcceptBothAsync(futureTwo,
                (result1, result2) -> System.out.println("join: " + result1 + ", " + result2));
        System.out.println("end");

        // ждём, чтобы увидеть результаты двух потоков, т.к. метод thenAcceptBothAsync() не ждёт
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
