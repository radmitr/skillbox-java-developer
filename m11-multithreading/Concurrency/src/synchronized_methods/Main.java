package synchronized_methods;

import java.util.ArrayList;

// 11.8 Synchronized-методы (без "synchronized")
public class Main {

    private static ArrayList<Double> numbers = new ArrayList<>();

    public static void main(String[] args) {
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(Main::someHeavyMethod));
        }
//        threads.forEach(t -> t.start());
        threads.forEach(Thread::start);
    }

    private static void someHeavyMethod() {
        for (int i = 0; i < 1_000_000; i++) {
            numbers.add(Math.random() / Math.random());
        }
        System.out.printf("%s: array size = %d\n", Thread.currentThread().getName(), numbers.size());
        numbers.clear();
    }
}
