package strategy.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StrategyExample {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(5);
        list.add(1);
        System.out.println(list);

        // strategy of sorting
        Collections.sort(list, (i1, i2) -> i2 - i1);
        System.out.println(list);
    }
}
