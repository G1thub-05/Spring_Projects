package Level_3_Arrays;

import java.util.HashSet;
import java.util.Scanner;

public class RemoveDuplicates {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter array size: ");
		int n = sc.nextInt();

		int[] arr = new int[n];

		System.out.println("Enter array elements:");

		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

		HashSet<Integer> set = new HashSet<>();

		for (int num : arr) {
			set.add(num);
		}

		System.out.println("After removing duplicates:");

		for (int num : set) {
			System.out.print(num + " ");
		}

		sc.close();
	}
}
