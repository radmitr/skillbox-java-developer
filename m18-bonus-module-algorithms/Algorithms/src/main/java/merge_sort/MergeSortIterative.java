package merge_sort;

import java.util.Arrays;

// 18.8 Алгоритм сортировки MergeSort
// Task 18.1.6
public class MergeSortIterative {

    public MergeSortIterative() {
    }

    public static void sort(int[] array) {
        int[] aux = Arrays.copyOf(array, array.length);
        int n = array.length;
        for (int size = 1; size < n; size *= 2) {
            for (int j = 0; j < n - size; j += 2 * size) {
                merge(array, aux, j, j + size - 1, Math.min(j + 2 * size - 1, n - 1));
            }
        }
    }

    public static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (aux[i] <= aux[j]) {
                a[k] = aux[i++];
            } else {
                a[k] = aux[j++];
            }
        }
    }
}
