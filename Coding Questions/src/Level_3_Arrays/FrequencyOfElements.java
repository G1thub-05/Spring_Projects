package Level_3_Arrays;

import java.util.Scanner;

public class FrequencyOfElements {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter array size: ");
		int n = sc.nextInt();

		int[] arr = new int[n];

		System.out.println("Enter array elements:");

		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

		for (int i = 0; i < arr.length; i++) {

			boolean alreadyChecked = false;

			// Check if element was already processed
			for (int k = 0; k < i; k++) {
				if (arr[i] == arr[k]) {
					alreadyChecked = true;
					break;
				}
			}

			if (alreadyChecked) {
				continue;
			}

			int count = 0;

			// Count frequency
			for (int j = 0; j < arr.length; j++) {
				if (arr[i] == arr[j]) {
					count++;
				}
			}

			System.out.println(arr[i] + " = " + count);
		}

		sc.close();
	}
}