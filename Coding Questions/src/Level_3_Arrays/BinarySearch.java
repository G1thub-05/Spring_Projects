package Level_3_Arrays;

import java.util.Arrays;
import java.util.Scanner;

public class BinarySearch {

	public static void main(String[] args) {

		int[] arr = {5, 1, 3, 6, 44, 22, 11, 66, 0, 9};

		System.out.println("Unsorted Array: " + Arrays.toString(arr));
		Arrays.sort(arr);
		System.out.println("Sorted Array: " + Arrays.toString(arr));

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter the element to be searched: ");
		int element = sc.nextInt();
		int index = findElementIndex(arr, element);

		if (index == -1) {
			System.out.println("Element not found");
		} else {
			System.out.println(
					"Element " + element + " found at index " + index
			);
		}

		sc.close();
	}

	public static int findElementIndex(int[] arr, int element) {

		int start = 0;
		int end = arr.length - 1;

		while (start <= end) {

			int mid = start + (end - start) / 2;

			if (arr[mid] == element) {
				return mid;
			} else if (arr[mid] < element) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}

		return -1;
	}
}