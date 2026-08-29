package Leve_1_JavaBasics;

import java.util.Scanner;

public class ReverseNumber {

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

		System.out.println("Original number: " + org);
		System.out.println("Reverse number: " + reverse);

		sc.close();
	}
}