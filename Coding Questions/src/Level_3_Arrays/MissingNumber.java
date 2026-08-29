
package Level_3_Arrays;

import java.util.Scanner;

public class MissingNumber {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter numbers separated by spaces: ");
		String input = sc.nextLine();

		String[] numbers = input.trim().split("\\s+");

		int max = 0;

		// Find maximum number
		for (String number : numbers) {
			int num = Integer.parseInt(number);

			if (num > max) {
				max = num;
			}
		}

		boolean[] seen = new boolean[max + 1];

		// Mark entered numbers
		for (String number : numbers) {
			int num = Integer.parseInt(number);

			if (num > 0) {
				seen[num] = true;
			}
		}

		System.out.print("Missing number(s): ");

		boolean found = false;

		// Check only from 1 to maximum number
		for (int i = 1; i <= max; i++) {
			if (!seen[i]) {
				System.out.print(i + " ");
				found = true;
			}
		}

		if (!found) {
			System.out.print("None");
		}

		sc.close();
	}
}