package completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 2. Исполнение нескольких CompletableFuture
 *
 * Если же нам нужно получить результат работы всех задач, тогда можно
 * воспользоваться методом join() и Stream API.
 *
 * Метод  join() как и get() возвращает результат работы CompletableFuture,
 * но в отличии от него join() бросает RuntimeException в случае ошибки исполнения.
 * Данная особенность позволяет использовать ссылку на метод, и заменить
 * .map(m -> m.join())
 * на
 * .map(CompletableFuture::join)
 *
 * https://vertex-academy.com/tutorials/ru/java-8-completablefuture-chast-3/
 */
public class Ex3_2 {

    public static void main(String[] args) {
        CompletableFuture<String> what = CompletableFuture.supplyAsync(() -> "What");
        CompletableFuture<String> the = CompletableFuture.supplyAsync(() -> "the");
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "future");
        CompletableFuture<String> holds = CompletableFuture.supplyAsync(() -> "holds?");

        // 1 - лямбда: '.map(m -> m.join())'
//        String result = Stream.of(what, the, future, holds)
//                .map(m -> m.join())
//                .collect(Collectors.joining(" "));

        // 2 - ссылка на метод: 'CompletableFuture::join'
        String result = Stream.of(what, the, future, holds)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "));

        System.out.println(result); // output: What the future holds?
    }
}
