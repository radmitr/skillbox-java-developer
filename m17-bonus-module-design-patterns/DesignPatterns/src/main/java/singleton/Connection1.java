package singleton;

public class Connection1 {

    private static Connection1 instance;

    private Connection1() {
    }

    public static Connection1 getInstance() {
        if (instance == null) {
            instance = new Connection1();
        }
        return instance;
    }
}
