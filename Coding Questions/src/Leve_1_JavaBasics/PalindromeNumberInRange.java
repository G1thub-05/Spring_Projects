package Leve_1_JavaBasics;

import java.util.Scanner;

public class PalindromeNumberInRange {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter start number: ");
		int start = sc.nextInt();

		System.out.print("Enter end number: ");
		int end = sc.nextInt();

		if (start > end || start < 0) {
			System.out.println("Invalid range.");
		} else {

			System.out.print("Palindrome Numbers: ");

			for (int number = start; number <= end; number++) {

				int modified = number;
				int reverse = 0;

				while (modified != 0) {
					int digit = modified % 10;
					reverse = (reverse * 10) + digit;
					modified /= 10;
				}

				if (number == reverse) {
					System.out.print(number + " ");
				}
			}
		}

		sc.close();
	}
}