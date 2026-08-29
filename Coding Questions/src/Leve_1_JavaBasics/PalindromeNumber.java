package Leve_1_JavaBasics;

import java.util.Scanner;

public class PalindromeNumber {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter a number: ");
		int org = sc.nextInt();
		int modified = org;
		int reverse = 0;

		while (modified != 0) {
			int digit = modified % 10;
			reverse = (reverse * 10) + digit;
			modified = modified / 10;
		}

		if (org == reverse) {
			System.out.println(org + " is a Palindrome Number");
		} else {
			System.out.println(org + " is not a Palindrome Number");
		}

		sc.close();
	}
}