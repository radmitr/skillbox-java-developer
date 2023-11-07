package iteration_vs_recursion;

// 18.3 Рекурсивные алгоритмы
public class RecursionStackOverflow {

    public static void checkStack(int i) {
        System.out.println(i);
        checkStack(i + 1);
    }

    public static void main(String[] args) {
        checkStack(1);
    }
}
