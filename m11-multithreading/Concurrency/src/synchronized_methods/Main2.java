package synchronized_methods;

import java.util.ArrayList;

// 11.8 Synchronized-методы
public class Main2 {

    private static ArrayList<Double> numbers = new ArrayList<>();

    public static void main(String[] args) {
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(Main2::someHeavyMethod));
        }
//        threads.forEach(t -> t.start());
        threads.forEach(Thread::start);
    }

    // synchronized method
    private static synchronized void someHeavyMethod() {
        for (int i = 0; i < 1_000_000; i++) {
            numbers.add(Math.random() / Math.random());
        }
        System.out.printf("%s: array size = %d\n", Thread.currentThread().getName(), numbers.size());
        numbers.clear();
    }
}
