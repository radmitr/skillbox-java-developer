package interrupt.interrupter;

// 11.19 Приостановка и прерывание потоков
public class Main {

    public static void main(String[] args) {
        Thread thread = new Processor();
        Thread interrupter = new Thread(new Interrupter(thread));

        thread.start();
        interrupter.start();
    }
}
