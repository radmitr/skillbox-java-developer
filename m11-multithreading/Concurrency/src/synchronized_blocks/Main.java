package synchronized_blocks;

import java.util.ArrayList;

// 11.9 Synchronized-блоки (без "synchronized")
public class Main {

    private static ArrayList<Double> numbers = new ArrayList<>();

    public static void main(String[] args) {
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(Main::someHeavyMethod));
        }
        threads.forEach(Thread::start);
    }

    private static void someHeavyMethod() {
        for (int i = 0; i < 1_000_000; i++) {
            numbers.add(Math.random() / Math.random());
        }
        System.out.printf("%s: %d\n", Thread.currentThread().getName(), numbers.size());
        numbers.clear();
    }
}
