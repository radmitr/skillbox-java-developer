package race_condition;

// 11.5 Состояние гонки и критические секции
public class Main {

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100_000; j++) {
                    ValueStorage.incrementValue();
                }
                System.out.printf("%s: %d\n", Thread.currentThread().getName(), ValueStorage.getValue());
            }).start();
        }
    }
}
