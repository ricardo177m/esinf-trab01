package isep.esinf.utils;

import java.util.Arrays;

public class BubbleSort {
  public static int[] sort(int[] original) {
    int[] array = Arrays.copyOf(original, original.length);

    for (int i = 0; i < array.length - 1; i++) {
      for (int j = i + 1; j < array.length; j++) {
        if (array[i] > array[j]) {
          array[i] += array[j];
          array[j] = array[i] - array[j];
          array[i] -= array[j];
        }
      }
    }

    return array;
  }
}
