package Level_3_Arrays;


import java.util.Scanner;

public class LargestSecondLargest {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter array size: ");
		int n = sc.nextInt();

		int[] arr = new int[n];

		System.out.println("Enter array elements:");

		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

		int largest = Integer.MIN_VALUE;
		int secondLargest = Integer.MIN_VALUE;

		for (int i = 0; i < n; i++) {

			if (arr[i] > largest) {
				secondLargest = largest;
				largest = arr[i];

			} else if (arr[i] > secondLargest && arr[i] != largest) {
				secondLargest = arr[i];
			}
		}

		System.out.println("Largest: " + largest);

		if (secondLargest == Integer.MIN_VALUE) {
			System.out.println("Second largest does not exist");
		} else {
			System.out.println("Second largest: " + secondLargest);
		}

		sc.close();
	}
}