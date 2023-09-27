package forkJoin_pool.mkyong_examples.fibonacci;

import java.util.concurrent.ForkJoinPool;

/**
 * 2. Fork/Join – RecursiveAction
 *
 * A fork join example to find the Fibonacci number by using recursive loop.
 *
 * *Note
 * This method is used for Fork/Join demo only, recursive loop is slow. Try this
 * Java Fibonacci examples to find the Fibonacci number faster.
 *
 * https://mkyong.com/java/java-fork-join-framework-examples/
 */
public class Main {

    public static void main(String[] args) {
        ForkJoinFibonacci task = new ForkJoinFibonacci(50);
        new ForkJoinPool().invoke(task);

        System.out.println(task.getNumber());
    }
}
