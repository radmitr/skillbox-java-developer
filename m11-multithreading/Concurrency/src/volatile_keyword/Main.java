package volatile_keyword;

import java.util.Scanner;

// 11.7 Ключевое слово Volatile
public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main begin");

        Task task = new Task();
        Thread thread = new Thread(task);
        thread.start();

        Thread.sleep(50);
        System.out.print("Press \"Enter\": ");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        task.stop();
        thread.join();

        System.out.println("volatile_keyword.Main  increments: " + task.getCounter());
        System.out.println("Main end");
    }
}
