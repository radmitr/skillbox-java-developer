import java.util.*;

// Task 5.5
public class Main {

    private static final int MIN_CAR_CHARS = 111;
    private static final int MAX_CAR_CHARS = 999;
    private static final int MIN_REGION_NUMBER = 1;
    private static final int MAX_REGION_NUMBER = 199;
    private static final char[] CAR_CHARS = {'A','B','C','E','H','K','M','O','P','T','X'};

    private static List<String> arrayList = new ArrayList<>();
    private static List<String> linkedList = new LinkedList<>();
    private static Set<String> treeSet = new TreeSet<>();
    private static Set<String> hashSet = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("Идёт генерация красивых номеров автомобилей...");
        generateCoolCarNumber(arrayList); // sorted

        linkedList.addAll(arrayList);
        hashSet.addAll(arrayList);
        treeSet.addAll(arrayList);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите номер для поиска:");

            String carNumber = "";
            try {
                carNumber = scanner.nextLine().trim();
            } catch (NoSuchElementException e) {
                // after Ctrl+D
                System.exit(0);
            }

            bruteForceSearchInList(arrayList, carNumber);
            bruteForceSearchInList(linkedList, carNumber);

            binarySearchInSortedList(arrayList, carNumber);
            binarySearchInSortedList(linkedList, carNumber);

            searchInSet(hashSet, carNumber);

            searchInSet(treeSet, carNumber);

            System.out.println();
        }
    }

    private static void generateCoolCarNumber(Collection<String> collection) {
        for (int i = 0; i < CAR_CHARS.length; i++) {
            for (int num = MIN_CAR_CHARS; num <= MAX_CAR_CHARS; num += MIN_CAR_CHARS) {
                for (int j = 0; j < CAR_CHARS.length; j++) {
                    for (int k = 0; k < CAR_CHARS.length; k++) {
                        for (int reg = MIN_REGION_NUMBER; reg <= MAX_REGION_NUMBER; reg++) {
                            String number = CAR_CHARS[i] + Integer.toString(num) + CAR_CHARS[j] + CAR_CHARS[k]
                                    + String.format("%02d", reg);
                            collection.add(number);
                        }
                    }
                }
            }
        }
    }

    private static void bruteForceSearchInList(List<String> list, String carNumber) {
        long start = System.nanoTime();
        boolean found = list.contains(carNumber);
        long duration = System.nanoTime() - start;

        String result;
        if (found) {
            result = "найден";
        } else {
            result = "не найден";
        }

        String fullClassName = list.getClass().toString();
        String className = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);

        System.out.printf("Поиск перебором в %s: номер %s, поиск занял %.1f мкс\n", className, result, duration/1000.);
    }

    private static void binarySearchInSortedList(List<String> list, String carNumber) {
        long start = System.nanoTime();
        int found = Collections.binarySearch(list, carNumber);
        long duration = System.nanoTime() - start;

        String result;
        if (found >= 0) {
            result = "найден";
        } else {
            result = "не найден";
        }

        String fullClassName = list.getClass().toString();
        String className = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);

        System.out.printf("Бинарный поиск в %s: номер %s, поиск занял %.1f мкс\n", className, result, duration/1000.);
    }

    private static void searchInSet(Set<String> set, String carNumber) {
        long start = System.nanoTime();
        boolean found = set.contains(carNumber);
        long duration = System.nanoTime() - start;

        String result;
        if (found) {
            result = "найден";
        } else {
            result = "не найден";
        }

        String fullClassName = set.getClass().toString();
        String className = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);

        System.out.printf("Поиск в %s: номер %s, поиск занял %.1f мкс\n", className, result, duration/1000.);
    }
}
