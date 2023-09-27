package executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// 11.18 ScheduledExecutorService
public class Main4 {

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);

        service.schedule(
                () -> System.out.println("YES"), 1000, TimeUnit.MILLISECONDS);

        service.scheduleAtFixedRate(
                () -> System.out.println("NO"), 1000, 2000, TimeUnit.MILLISECONDS);

        service.scheduleWithFixedDelay(
                () -> System.out.println("GOOD"), 1000, 2000, TimeUnit.MILLISECONDS);

        Thread.sleep(30_000);
        service.shutdown();
    }
}
