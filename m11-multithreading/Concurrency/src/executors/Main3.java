package executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// 11.17 ThreadPoolExecutor
public class Main3 {

    public static void main(String[] args) {
        // 1
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        executor.setCorePoolSize(10);
        executor.setMaximumPoolSize(15);
        executor.setKeepAliveTime(30, TimeUnit.SECONDS);

        executor.getQueue().size();
        executor.getActiveCount();
        executor.getCompletedTaskCount();

        // 2
        ExecutorService executorService = Executors.newFixedThreadPool(5);
    }
}
