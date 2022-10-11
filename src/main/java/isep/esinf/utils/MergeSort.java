package isep.esinf.utils;

import java.util.Arrays;

public class MergeSort {
  public static int[] sort(int[] original) {
    int[] array = Arrays.copyOf(original, original.length);

    // if the array contains only one integer, it is already sorted
    if (array.length <= 1)
      return original;

    // divide the array into two halves
    int mid = array.length / 2;
    // System.out.print(mid + " ");

    // left: 0
    // mid: mid
    // right: n

    // keep dividing recursively until left + right = 1
    int[] left = sort(Arrays.copyOfRange(array, 0, mid));
    int[] right = sort(Arrays.copyOfRange(array, mid, array.length));

    // finally, merge both halves
    return merge(left, right);
  }

  private static int[] merge(int[] left, int[] right) {
    // merging the 2 sub arrays into a single array
    int length = left.length + right.length;
    int[] merged = new int[length];
    int i = 0, j = 0;

    // reorder the greater integers into the correct position
    while (i < left.length && j < right.length) {
      if (left[i] < right[j]) {
        merged[i + j] = left[i++];
      } else {
        merged[i + j] = right[j++];
      }
    }

    // finally, fill with the remainding integers
    while (i < left.length) {
      merged[i + j] = left[i++];
    }
    while (j < right.length) {
      merged[i + j] = right[j++];
    }

    return merged;
  }
}
