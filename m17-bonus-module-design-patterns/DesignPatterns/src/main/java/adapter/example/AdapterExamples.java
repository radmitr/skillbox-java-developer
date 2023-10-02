package adapter.example;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class AdapterExamples {

    public static void main(String[] args) throws IOException {
        // 1 - Arrays -> List
        List<Integer> list = Arrays.asList(1, 2, 3);

        // 2 - InputStreamReader
        FileInputStream fis = new FileInputStream("somefile.txt");
        InputStreamReader isr = new InputStreamReader(fis);

        int t;
        while((t = isr.read())!= -1) {
            // convert the integer true to character
            char r = (char)t;
            System.out.println("Character : " + r);

            // check if the stream ready
            boolean b = isr.ready();
            System.out.println("Ready? : " + b);
        }

        isr.close();
        fis.close();

        // 3 - OutputStreamWriter
        OutputStream os = new FileOutputStream("test.txt");
        OutputStreamWriter osw = new OutputStreamWriter(os);
        FileInputStream fis2 = new FileInputStream("test.txt");

        // use of write(int char):
        // writing character values to the "test.txt"
        osw.write(71);
        osw.write(69);
        osw.write(69);
        osw.write(75);
        osw.write(83);

        osw.flush();

        // read what we write
        for (int i = 0; i < 5; i++) {
            // reading the content of "test.txt" file
            System.out.println("write(int char): " + (char) fis2.read());
        }
        osw.close();
    }
}
