import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("timeout", "3000");
        properties.setProperty("cacheSize", "10000");
        try {
            properties.store(new FileOutputStream("resources/config.properties"), "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
