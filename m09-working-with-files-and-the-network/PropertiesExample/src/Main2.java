import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main2 {

    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("resources/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(properties.getProperty("cacheSize"));

    }
}
