package deadlock_fixed;

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

    public void throwBollTo(Friend catcher) {
        System.out.printf("%s [%s] %s: %s кинул мне мяч!\n",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(LocalDateTime.now()),
                Thread.currentThread().getName(),
                catcher.getName(),
                this.name);
        // synchronized block
        synchronized (compareTo(catcher) > 0 ? catcher : this) {
            catcher.throwBollTo(this);
        }
    }

    @Override
    public int compareTo(Friend o) {
        return this.getName().compareTo(o.getName());
    }
}
