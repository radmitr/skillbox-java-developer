package deadlock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Friend implements Comparable<Friend> {

    private final String name;

    public Friend(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // synchronized method
    public synchronized void throwBollTo(Friend catcher) {
        System.out.printf("%s [%s] %s: %s кинул мне мяч!\n",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(LocalDateTime.now()),
                Thread.currentThread().getName(),
                catcher.getName(),
                this.name);
        catcher.throwBollTo(this);
    }

    @Override
    public int compareTo(Friend o) {
        return this.getName().compareTo(o.getName());
    }
}
