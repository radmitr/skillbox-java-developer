import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class WriteUsingFiles {

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            strings.add(String.valueOf(i));
        }

        try {
            Files.write(Paths.get("data/file3.txt"), strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
