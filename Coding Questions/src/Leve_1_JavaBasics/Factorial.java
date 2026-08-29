package Leve_1_JavaBasics;

import java.util.Scanner;

public class Factorial {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter a number: ");
		int n = sc.nextInt();

		if (n < 0) {
			System.out.println("Factorial is not defined for negative numbers.");
		} else {
			long factorial = 1;

			// Example: 5! = 1 × 2 × 3 × 4 × 5 = 120
			for (int i = 1; i <= n; i++) {
				factorial = factorial * i;
			}
			System.out.println("Factorial of " + n + " = " + factorial);
		}

		sc.close();
	}
}