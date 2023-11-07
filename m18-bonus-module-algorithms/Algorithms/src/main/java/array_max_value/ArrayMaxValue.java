package array_max_value;

// 18.2 Зачем нужны алгоритмы
// Task 18.1.1
public class ArrayMaxValue {

    public static int getMaxValue(int[] values) {
        if (values.length > 0) {
            int maxValue = Integer.MIN_VALUE;
            for (int value : values) {
                if (value > maxValue) {
                    maxValue = value;
                }
            }
            return maxValue;
        }
        return -1;
    }
}
