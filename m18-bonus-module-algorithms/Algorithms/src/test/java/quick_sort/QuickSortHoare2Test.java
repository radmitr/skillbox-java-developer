package quick_sort;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Task 18.1.5
class QuickSortHoare2Test {

    private static final Random random;
    private static final long seed;

    static {
        seed = System.currentTimeMillis();
        random = new Random(seed);
    }

    @Test
    void shouldAcceptWhenEmptyArray() {
        int[] array = {};
        int[] expected = {};

        QuickSortHoare2.sort(array);

        assertThat(array, equalTo(expected));
    }

    @Test
    void shouldAcceptWhenSingleValuedArray() {
        int[] array = {2};
        int[] expected = {2};

        QuickSortHoare2.sort(array);

        assertThat(array, equalTo(expected));
    }

    @Test
    void shouldSortWhenArrayWithEvenNumberOfAllPositiveValues() {
        int[] array = {60, 7, 55, 9, 999, 3};
        int[] expected = {3, 7, 9, 55, 60, 999};

        QuickSortHoare2.sort(array);

        assertThat(array, equalTo(expected));
    }

    @Test
    void shouldSortWhenArrayWithOddNumberOfAllPositiveValues() {
        int[] array = {60, 7, 55, 9, 999, 3, 111};
        int[] expected = {3, 7, 9, 55, 60, 111, 999};

        QuickSortHoare2.sort(array);

        assertThat(array, equalTo(expected));
    }

    @Test
    void shouldSortWhenArrayWithAllNegativeValues() {
        int[] array = {-60, -7, -55, -9, -999, -3};
        int[] expected = {-999, -60, -55, -9, -7, -3};

        QuickSortHoare2.sort(array);

        assertThat(array, equalTo(expected));
    }

    @Test
    void shouldSortWhenArrayWithRealNumberValues() {
        int[] array = {60, -7, 55, 9, -999, -3};
        int[] expected = {-999, -7, -3, 9, 55, 60};

        QuickSortHoare2.sort(array);

        assertThat(array, equalTo(expected));
    }

    @Test
    void shouldSortWhenArrayWithDuplicateValue() {
        int[] array = {60, 7, 55, 55, 999, 3};
        int[] expected = {3, 7, 55, 55, 60, 999};

        QuickSortHoare2.sort(array);

        assertThat(array, equalTo(expected));
    }

    @Test
    void shouldReturnTheSameArrayWhenOrderedArray() {
        int[] array = {3, 7, 9, 55, 60, 999};
        int[] expected = {3, 7, 9, 55, 60, 999};

        QuickSortHoare2.sort(array);

        assertThat(array, equalTo(expected));
    }

    @Test
    void shouldSortWhenReverseOrderedArray() {
        int[] array = {999, 60, 55, 9, 7, 3};
        int[] expected = {3, 7, 9, 55, 60, 999};

        QuickSortHoare2.sort(array);

        assertThat(array, equalTo(expected));
    }

    @Test
    void shouldSortWhenRandomArray() {
        int randomSize = generateInt(10_000);
        int[] array = generateArray(randomSize);

        QuickSortHoare2.sort(array);

        assertTrue(isSorted(array));
    }

    public static int generateInt(int n) {
        return random.nextInt(n);
    }

    private static int[] generateArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }

    public static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
