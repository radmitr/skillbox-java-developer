package deadlock;

// 11.12 Взаимная блокировка — Deadlock
public class Main {

    public static void main(String[] args) {
        final Friend petya = new Friend("Петя");
        final Friend ruslan = new Friend("Руслан");

        new Thread(() -> petya.throwBollTo(ruslan)).start();
        new Thread(() -> ruslan.throwBollTo(petya)).start();
    }
}
