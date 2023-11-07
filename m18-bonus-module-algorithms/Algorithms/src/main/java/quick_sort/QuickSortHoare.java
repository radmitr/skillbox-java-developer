package quick_sort;

// 18.7 Алгоритм сортировки QuickSort
// Task 18.1.5
public class QuickSortHoare {

    private QuickSortHoare() {
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
        int i = lo + 1;
        int j = hi;
        int pivot = a[lo];

        while (true) {
            while (i < hi && a[i] < pivot) {
                i += 1;
            }
            while (pivot < a[j]) {
                j -= 1;
            }
            if (i >= j) {
                break;
            }
            swap(a, i++, j--);
        }
        swap(a, lo, j); // put partitioning item pivot at a[j]
        return j;       // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
