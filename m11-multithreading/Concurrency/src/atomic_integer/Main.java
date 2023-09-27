package atomic_integer;

// 11.6 Атомарные переменные
public class Main {

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10_000_000; j++) {
                    ValueStorage.incrementValue();
                }
                System.out.printf("%s: %d\n", Thread.currentThread().getName(), ValueStorage.getValue());
            }).start();
        }
    }
}
