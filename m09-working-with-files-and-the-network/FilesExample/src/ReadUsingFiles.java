import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReadUsingFiles {

    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();

        try {
            // 1 - readAllLines()
            List<String> lines = Files.readAllLines(Paths.get("data/info.txt"));
            lines.forEach(line -> builder.append(line + "\n"));

            // 2 - readAllBytes()
//            byte[] bytes = Files.readAllBytes(Paths.get("data/info.txt"));
//            for (byte b : bytes) {
//                builder.append((char) b);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(builder);
    }
}
