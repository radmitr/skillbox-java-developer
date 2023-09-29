package singleton;

public class Connection3 {

    // create an instance
    private static Connection3 instance = new Connection3();

    private Connection3() {
    }

    public static Connection3 getInstance() {
        return instance;
    }
}
