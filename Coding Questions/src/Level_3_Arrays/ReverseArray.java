package Level_3_Arrays;

import java.util.Arrays;
import java.util.Scanner;

public class ReverseArray {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter array size: ");
		int n = sc.nextInt();
		int[] arr = new int[n];
		System.out.println("Enter array elements:");

		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

		// Reverse array
		int start = 0;
		int end = arr.length - 1;

		while (start < end) {
			int temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;

			start++;
			end--;
		}

		// Print using for-each
		System.out.println("Reversed array:");

		for (int num : arr) {
			System.out.print(num + " ");
		}

		sc.close();
	}

	public static void reverseArray() {
		int[] arr1 = {11, 66, 9, 5, 44};
		int[] arr2 = new int[arr1.length];
		int ln = 0;
		for (int i = arr1.length -1; i >= 0; i--) {
			arr2[ln] = arr1[i];
			ln++;
		}
		System.out.println(Arrays.toString(arr1));
		System.out.println(Arrays.toString(arr2));
	}
}