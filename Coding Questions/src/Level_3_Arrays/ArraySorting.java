package Level_3_Arrays;

import java.util.Arrays;

public class ArraySorting {

	public static void main(String[] args) {

		int[] arr = {31, 24, 2, 100, 1, 5};

		System.out.println("Original array: " + Arrays.toString(arr));

		int n = arr.length;

		// Ascending order
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {

				if (arr[i] > arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}

		System.out.println("Sorted array in ASC: " + Arrays.toString(arr));

		// Descending order
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {

				if (arr[i] < arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}

		System.out.println("Sorted array in DSC: " + Arrays.toString(arr));
	}
}