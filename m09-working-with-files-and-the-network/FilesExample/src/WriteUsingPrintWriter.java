import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class WriteUsingPrintWriter {

    public static void main(String[] args) {
        try {
            PrintWriter writer = new PrintWriter("data/file2.txt");
            for (int i = 0; i < 1000; i++) {
                writer.write(i + "\n");
            }
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
