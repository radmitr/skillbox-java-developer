import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteUsingFileOutputStream {

    public static void main(String[] args) {
        try {
            FileOutputStream fos = new FileOutputStream("data/file.txt");
            for (int i = 32; i <= 127; i++) {
                fos.write(i);
            }
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
