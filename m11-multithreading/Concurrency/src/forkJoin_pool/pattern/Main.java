package forkJoin_pool.pattern;

import java.util.concurrent.ForkJoinPool;

// 11.20 ForkJoinPool и RecursiveTask
public class Main {

    public static void main(String[] args) {
        Node root = null;

        Long sum = new ForkJoinPool().invoke(new NodeValueSumCalculator(root));
        System.out.println(sum);
    }
}
