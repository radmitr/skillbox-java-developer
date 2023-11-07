package merge_sort;

// 18.8 Алгоритм сортировки MergeSort
// Task 18.1.6
public class MergeSort {

    public MergeSort() {
    }

    public static void sort(int[] array) {
        if (array.length < 2) {
            return;
        }
        int n = array.length;
        int middle = n / 2;
        int[] leftArray = new int[middle];
        int[] rightArray = new int[n - middle];

        for (int i = 0; i < middle; i++) {
            leftArray[i] = array[i];
        }
        for (int i = middle; i < n; i++) {
            rightArray[i - middle] = array[i];
        }
        sort(leftArray);
        sort(rightArray);

        merge(array, leftArray, rightArray);
    }

    private static void merge(int[] array, int[] left, int[] right) {
        int leftEnd = left.length - 1;
        int rightEnd = right.length - 1;
        int i = 0, j = 0, k = 0;
        while (i <= leftEnd && j <= rightEnd) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }
        while (i <= leftEnd) {
            array[k++] = left[i++];
        }
        while (j <= rightEnd) {
            array[k++] = right[j++];
        }
    }
}
