package quick_sort;

// 18.7 Алгоритм сортировки QuickSort
// Task 18.1.5
public class QuickSortLomuto2 {

    private QuickSortLomuto2() {
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
        int j = lo;
        int supportElement = a[lo];
        for (int i = lo + 1; i <= hi; i++) {
            if (a[i] < supportElement) {
                j+=1;
                swap(a, i, j);
            }
        }
        swap(a, lo, j);
        return j;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
