package quick_sort;

// 18.7 Алгоритм сортировки QuickSort
// Task 18.1.5
public class QuickSortHoare2 {

    private QuickSortHoare2() {
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
            sort(a, pivot, hi);
        }
    }

    private static int partition(int[] a, int lo, int hi) {
//        int mid = lo + (hi - lo) / 2;
        int mid = (lo + hi) >>> 1;
        int pivot = a[mid];

        while (lo <= hi) {
            while (a[lo] < pivot) {
                ++lo;
            }
            while (pivot < a[hi]) {
                --hi;
            }
            if (lo <= hi) {
                swap(a, lo, hi);
                ++lo;
                --hi;
            }
        }
        return lo;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
