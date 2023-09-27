package thread_safe_classes;

import java.util.ArrayList;

// 11.11 Потокобезопасные классы
public class Main1_ArrayList {

    // ArrayList потокоНЕбезопасный
    private static ArrayList<Double> numbers = new ArrayList<>();

    public static void main(String[] args) {
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(() -> {
                for (int j = 0; j < 500_000; j++) {
                    numbers.add(Math.random());
                }
                System.out.printf("%s: %d\n", Thread.currentThread().getName(), numbers.size());
            }));
        }

        threads.forEach(Thread::start);
    }
}
