import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadUsingFileInputStream {

    public static void main(String[] args) {

        StringBuilder builder = new StringBuilder();

        try {
            FileInputStream fis = new FileInputStream("data/info.txt");
            for(;;) {
                int code = fis.read();
                if (code < 0) {
                    break;
                }
                char ch = (char) code;
                builder.append(ch);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(builder);
    }
}
