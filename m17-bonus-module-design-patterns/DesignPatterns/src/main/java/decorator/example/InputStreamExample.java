package decorator.example;

import java.io.*;
import java.util.zip.GZIPInputStream;

public class InputStreamExample {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("somefile.gz");
        BufferedInputStream bis = new BufferedInputStream(fis);
        GZIPInputStream gis = new GZIPInputStream(bis);
        ObjectInputStream ois = new ObjectInputStream(gis);

        Object object = ois.readObject();
        ois.close();
    }
}
