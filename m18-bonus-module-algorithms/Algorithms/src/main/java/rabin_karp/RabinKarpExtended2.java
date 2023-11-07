package rabin_karp;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

// 18.9 Алгоритм поиска подстроки Рабина-Карпа
// Task 18.1.7
public class RabinKarpExtended2 {

    public static final int R = 256; // количество символов в алфавите
    public static final int Q = 101; // некоторое простое число для вычисления хеша

    private String text;
    private TreeMap<Integer, Integer> position2number = new TreeMap<>();;

    public RabinKarpExtended2(String text) {
        if (text == null || text.equals("")) {
            throw new IllegalArgumentException("Передан пустой текстовый фрагмент или null");
        }
        this.text = text;
    }

    public List<Integer> search(String pattern) {
        if (pattern == null || pattern.equals("")) {
            throw new IllegalArgumentException("Передан пустой поисковый запрос или null");
        }
        List<Integer> indexes;
        int patternLength = pattern.length(); // количество символов в паттерне
        int patternHash = 0;                  // вычисленный хеш для паттерна

        // вычисляем хеш паттерна
        for (int i = 0; i < patternLength; i++) {
            patternHash = (R * patternHash + pattern.charAt(i)) % Q;
        }
        createIndex(patternLength);
        final int finalQueryHash = patternHash;
        indexes = position2number.entrySet().stream()
                .filter(entry -> {
                    if (entry.getValue() == finalQueryHash) {
                        int j;
                        // сравниваем символы в строке и паттерне
                        for (j = 0; j < patternLength; j++) {
                            if (text.charAt(entry.getKey() + j) != pattern.charAt(j)) {
                                break;
                            }
                        }
                        return j == patternLength;
                    }
                    return false;
                })
                .map(Map.Entry::getKey).collect(Collectors.toList());
        return indexes;
    }

    private void createIndex(int patternLength) {
        int rm = 1;
        int pos;
        int textHash = 0;

        for (pos = 0; pos < patternLength - 1; pos++) {
            rm = (rm * R) % Q;
        }
        for (pos = 0; pos < patternLength; pos++) {
            textHash = (R * textHash + text.charAt(pos)) % Q;
        }
        for (pos = 0; pos <= text.length() - patternLength; pos++) {
            position2number.put(pos, textHash);
            if (pos < text.length() - patternLength) {
                textHash = (R * (textHash - text.charAt(pos) * rm) + text.charAt(pos + patternLength)) % Q;
                if (textHash < 0) {
                    textHash = (textHash + Q);
                }
            }
        }
    }

    // ==============================================================================

    public static void main(String[] args) {
        RabinKarpExtended2 rabinKarpExtended2 = new RabinKarpExtended2("box Skillbox Skillboxboxboybobobox");
        List<Integer> indexes = rabinKarpExtended2.search("box");
        System.out.println(indexes);
    }
}
