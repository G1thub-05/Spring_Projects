package Leve_1_JavaBasics;

import java.util.Scanner;

public class SumOfDigits {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter a number: ");
		int org = sc.nextInt();

		int modified = org;
		int sum = 0;

		while (modified != 0) {
			int digit = modified % 10;
			sum = sum + digit;
			modified = modified / 10;
		}

		System.out.println("Sum of digits of " + org + " = " + sum);

		sc.close();
	}
}