public class RadixSort {
    public static void sort(int[] array, int maxValue) {
        int exponent = 1;
        int[] temp = new int[array.length];
        while (maxValue / exponent > 0) {
            int[] bucket = new int[10];
            for (int i = 0; i < array.length; i++) {
                int digit = (array[i] / exponent) % 10;
                bucket[digit]++;
            }
            for (int i = 1; i < 10; i++) {
                bucket[i] += bucket[i - 1];
            }
            for (int i = array.length - 1; i >= 0; i--) {
                int digit = (array[i] / exponent) % 10;
                temp[bucket[digit] - 1] = array[i];
                bucket[digit]--;
            }
            for (int i = 0; i < array.length; i++) {
                array[i] = temp[i];
            }
            exponent *= 10;
        }
    }
}
