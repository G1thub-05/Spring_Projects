package Level_3_Arrays;

import java.util.Scanner;

public class SmallestSecondSmallest {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter array size: ");
		int n = sc.nextInt();

		int[] arr = new int[n];

		System.out.println("Enter array elements:");

		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

		int smallest = Integer.MAX_VALUE;
		int secondSmallest = Integer.MAX_VALUE;

		for (int i = 0; i < n; i++) {

			if (arr[i] < smallest) {
				secondSmallest = smallest;
				smallest = arr[i];

			} else if (arr[i] < secondSmallest && arr[i] != smallest) {
				secondSmallest = arr[i];
			}
		}

		System.out.println("Smallest: " + smallest);

		if (secondSmallest == Integer.MAX_VALUE) {
			System.out.println("Second smallest does not exist");
		} else {
			System.out.println("Second smallest: " + secondSmallest);
		}

		sc.close();
	}
}