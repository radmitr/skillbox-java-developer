package synchronized_blocks;

import java.util.ArrayList;

// 11.9 Synchronized-блоки
public class Main2 {

    private static ArrayList<Double> numbers = new ArrayList<>();

    public static void main(String[] args) {
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(Main2::someHeavyMethod));
        }
        threads.forEach(Thread::start);
    }

    private static void someHeavyMethod() {
        for (int i = 0; i < 1_000_000; i++) {
            Double value = Math.random() / Math.random(); // тяжёлую операцию вынесли за synchronized-блок
            // synchronized block
//            synchronized (this) { // если бы это был НЕ статический метод
//            synchronized (Main.class) { // если бы это был НЕ статический метод
            synchronized (numbers) {
                numbers.add(value);
            }
        }
        System.out.printf("%s: %d\n", Thread.currentThread().getName(), numbers.size());
        numbers.clear();
    }
}
