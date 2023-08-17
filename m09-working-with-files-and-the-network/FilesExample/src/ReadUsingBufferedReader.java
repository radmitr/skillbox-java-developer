import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadUsingBufferedReader {

    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader("data/info.txt"));
            for(;;) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                builder.append(line + "\n");
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(builder);
    }
}
