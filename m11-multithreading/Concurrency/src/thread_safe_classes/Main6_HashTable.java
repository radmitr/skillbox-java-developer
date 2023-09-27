package thread_safe_classes;

import java.util.ArrayList;
import java.util.Hashtable;

// 11.11 Потокобезопасные классы
public class Main6_HashTable {

    // HashTable потокобезопасный
    private static Hashtable<String, Integer> map = new Hashtable<>();

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(() -> {
                for (int j = 0; j < 100_000; j++) {
                    map.put(Thread.currentThread().getName() + ", номер: " + j, j);
                }
                System.out.printf("%s: %d\n", Thread.currentThread().getName(), map.size());
            }));
        }

        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println(map.size());
    }
}
