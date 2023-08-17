import java.util.TreeMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //HashMap<String, Integer> good2count = new HashMap<>();
        TreeMap<String, Integer> good2count = new TreeMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String goodName = scanner.nextLine();
            if (goodName.toUpperCase().equals("LIST")) {
                printMap(good2count);
                continue;
            }
            int count = 1;
            if (good2count.containsKey(goodName)) {
                count = good2count.get(goodName) + 1;
            }
            good2count.put(goodName, count);
        }
    }

    private static void printMap (Map<String, Integer> map) {
        //Set<String> keys = map.keySet();
        //for (String key : keys) {
        for (String key : map.keySet()) {
            System.out.println(key + " => " + map.get(key));
        }
    }
}
