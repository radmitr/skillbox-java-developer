package iterator.example;

import java.util.ArrayList;
import java.util.Iterator;

public class IteratorExample {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Iterator<String> it = list.iterator();

        while (it.hasNext()) {
            String line = it.next();
        }
    }
}
