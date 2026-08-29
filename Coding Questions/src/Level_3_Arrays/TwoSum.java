package Level_3_Arrays;


import java.util.Scanner;

public class TwoSum {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter array size: ");
		int n = sc.nextInt();

		int[] arr = new int[n];

		System.out.println("Enter array elements:");

		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

		System.out.print("Enter target: ");
		int target = sc.nextInt();

		boolean found = false;

		for (int i = 0; i < arr.length; i++) {

			for (int j = i + 1; j < arr.length; j++) {

				if (arr[i] + arr[j] == target) {
					System.out.println(
							"Numbers: " + arr[i] + " + " + arr[j] + " = " + target
					);

					found = true;
					break;
				}
			}

			if (found) {
				break;
			}
		}

		if (!found) {
			System.out.println("No two numbers found.");
		}

		sc.close();
	}
}