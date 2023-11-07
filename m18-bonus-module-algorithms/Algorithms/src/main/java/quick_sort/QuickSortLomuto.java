package quick_sort;

// 18.7 Алгоритм сортировки QuickSort
// Task 18.1.5
public class QuickSortLomuto {

    private QuickSortLomuto() {
    }

    public static void sort(int[] array) {
        if (array.length <= 1) {
            return;
        }
        sort(array, 0, array.length - 1);
    }

    private static void sort(int[] a, int lo, int hi) {
        if (lo < hi) {
            int pivot = partition(a, lo, hi);
            sort(a, lo, pivot - 1);
            sort(a, pivot + 1, hi);
        }
    }

    private static int partition(int[] a, int lo, int hi) {
        int pivot = lo + (hi - lo) / 2;
        swap(a, pivot, hi);
        int storeIndex = lo;

        for (int i = lo; i < hi; i++) {
            if (a[i] < a[hi]) {
                swap(a, storeIndex, i);
                storeIndex++;
            }
        }
        swap(a, hi, storeIndex);
        return storeIndex;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
