package forkJoin_pool.fibonacci;

import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        int numberFibonacci = 9;

        Integer fib = new ForkJoinPool().invoke(new Fibonacci(numberFibonacci));
        System.out.println(fib);

        Integer fib2 = new Fibonacci(numberFibonacci).compute();
        System.out.println(fib2);
    }
}
