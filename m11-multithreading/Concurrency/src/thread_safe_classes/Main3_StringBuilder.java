package thread_safe_classes;

import java.util.ArrayList;

// 11.11 Потокобезопасные классы
public class Main3_StringBuilder {

    // StringBuilder потокоНЕбезопасный
    private static StringBuilder builder = new StringBuilder();

    public static void main(String[] args) {
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(() -> {
                for (int j = 0; j < 500_000; j++) {
                    builder.append("d");
                }
                System.out.printf("%s: %d\n", Thread.currentThread().getName(), builder.length());
            }));
        }

        threads.forEach(Thread::start);
    }
}
