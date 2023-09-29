package singleton;

public class Connection2 {

    private static Connection2 instance;

    private Connection2() {
    }

    // synchronized method
    public static synchronized Connection2 getInstance() {
        if (instance == null) {
            instance = new Connection2();
        }
        return instance;
    }
}
