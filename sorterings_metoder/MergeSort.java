public class MergeSort {
    public static void sort(int[] array, int start, int end) {

        if (start >= end) return;
        int mid = start + (end - start) / 2;
        sort(array, start, mid);
        sort(array, mid + 1, end);
        int[] temp = new int[end - start + 1];
        int i = start, j = mid + 1, k = 0;
        while (i <= mid && j <= end) {
            if (array[i] < array[j]) {
                temp[k] = array[i];
                i++;
            } else {
                temp[k] = array[j];
                j++;
            }
            k++;
        }
        while (i <= mid) {
            temp[k] = array[i];
            i++;
            k++;
        }
        while (j <= end) {
            temp[k] = array[j];
            j++;
            k++;
        }
        for (i = start; i <= end; i++) {
            array[i] = temp[i - start];
        }
    }
}
