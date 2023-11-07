package rabin_karp;

import java.util.*;

// 18.9 Алгоритм поиска подстроки Рабина-Карпа
// Task 18.1.7
public class RabinKarpExtended {
    private String text;
    private TreeMap<Integer, Integer> number2position;
    private ArrayList<Character> alphabet;

    public RabinKarpExtended(String text) {
        if (text == null || text.equals("")) {
            throw new IllegalArgumentException("Передан пустой текстовый фрагмент или null");
        }
        this.text = text;
        createAlphabet(); // создаем алфавит (используемые символы)
    }

    public List<Integer> search(String query) {
        ArrayList<Integer> indexes = new ArrayList<>();
        if (query == null || query.equals("")) {
            throw new IllegalArgumentException("Передан пустой поисковый запрос или null");
        }

        // Проверяем размер алфавита
        if (!isAlphabetSmallEnough()) {
            System.out.println("Достаточно большой размер алфавита");
            return null;
        }

        // Индексируем текст на основании длины строки поиска
        createIndex(query.length());

        // Индексируем строку поиска
        StringBuilder queryIntString = new StringBuilder();
        char[] queryArray = query.toUpperCase().toCharArray();
        for (int i = 0; i < queryArray.length; i++) {
            for (int j = 0; j < alphabet.size(); j++) {
                if (queryArray[i] == alphabet.get(j)) {
                    queryIntString.append(j);
                }
            }
        }
        if (queryIntString.length() < query.length()) {
            System.out.println("Есть символ(ы), которые отсутсвуют в алфавите");
            return null;
        }
        int queryInt = Integer.parseInt(queryIntString.toString());

        // Поиск
        if (!number2position.containsKey(queryInt)) {
            System.out.println("В тексте нет данной подстроки");
            return null;
        }
        int startIndex = number2position.get(queryInt);
        System.out.println("Подстрока начинается с индекса " + startIndex + " (нумерация с '0')");
        for (int i = 0; i < query.length(); i++) {
            indexes.add(startIndex + i);
        }
        return indexes;
    }

    private void createIndex(int queryLength) {
        char[] charArray;
        int[] intArray;

        // Проверяем, что текст для поиска больше по длине чем строка поиска
        charArray = text.toUpperCase().toCharArray();
        if (charArray.length < queryLength) {
            return;
        }
        intArray = new int[charArray.length];
        number2position = new TreeMap<>();

        // Сохдаём в массив цифр из массива букв, согласно нашему алфавиту
        for (int i = 0; i < charArray.length; i++) {
            for (int j = 0; j < alphabet.size(); j++) {
                if (charArray[i] == alphabet.get(j)) {
                    intArray[i] = j;
                }
            }
        }

        // Индексируем кусками длиной query
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= intArray.length - queryLength; i++) {
            for (int j = 0; j < queryLength; j++) {
                builder.append(intArray[i + j]);
            }
            // Собрали билдер длиной queryLength и теперь переводим в число и записываем его вместе с индексом
            int indexKey = Integer.parseInt(builder.toString());
            number2position.put(indexKey, i);
            builder = new StringBuilder();
        }
    }

    private void createAlphabet() {
        TreeSet<Character> tempSet = new TreeSet<>();
        char[] charArray = text.toUpperCase().toCharArray();
        for (char c : charArray) {
            tempSet.add(c);
        }
        alphabet = new ArrayList<>(tempSet);
    }

    private boolean isAlphabetSmallEnough() {
        return alphabet.size() < 10;
    }

    // =================================================================================

    public static void main(String[] args) {
        String text = "CGTTACGTATGCGCTAGCTAGTCGATGATGACGACGACGACGTA";
        String emptyQuery = "";
        String query = "GATG";
        String query2 = "AAAA";
        String query3 = "AAAF";

        RabinKarpExtended rabinKarpExtended = new RabinKarpExtended(text);

        System.out.println("Поиск подстроки по алгоритму Рабина-Карпа\n\n");
        try {
            System.out.println("Поиск пустой подстроки");
            searchTextAndPrint(rabinKarpExtended, emptyQuery);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        try {
            System.out.println("Поиск подстроки " + query);
            searchTextAndPrint(rabinKarpExtended, query);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        try {
            System.out.println("Поиск подстроки " + query2);
            searchTextAndPrint(rabinKarpExtended, query2);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        try {
            System.out.println("Поиск подстроки " + query3);
            searchTextAndPrint(rabinKarpExtended, query3);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void searchTextAndPrint(RabinKarpExtended rabinKarpExtended, String query) {
        List<Integer> indexes = rabinKarpExtended.search(query);
        if (indexes != null) {
            System.out.println(Arrays.toString(indexes.toArray()));
        }
    }
}
