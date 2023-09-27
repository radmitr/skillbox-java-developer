package forkJoin_pool.mkyong_examples.sum;

/**
 * 1. Fork/Join – RecursiveTask
 *
 * A fork join example to sum all the numbers from a range.
 *
 * https://mkyong.com/java/java-fork-join-framework-examples/
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(ForkJoinAdd.startForkJoinSum(1_000_000));
    }
}
