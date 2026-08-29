package Level_3_Arrays;

import java.util.Arrays;
import java.util.Scanner;

public class SortArray {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter array size: ");
		int n = sc.nextInt();

		int[] arr = new int[n];

		System.out.println("Enter array elements:");

		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

		Arrays.sort(arr);

		System.out.println("Sorted array:");

		for (int num : arr) {
			System.out.print(num + " ");
		}

		sc.close();
	}
}