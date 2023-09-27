package race_condition;

public class ValueStorage {

    private static int value;

    public static void incrementValue() {
        value = value + 1; // не атомарная операция (выполняется за несколько операций)
    }

    public static int getValue() {
        return value;
    }
}
