package Leve_1_JavaBasics;

import java.util.Scanner;

public class FactorialusingRecursion {

	public static long factorial(int n) {

		if (n == 0 || n == 1) {
			return 1;
		}

		return n * factorial(n - 1);
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter a number: ");
		int n = sc.nextInt();

		if (n < 0) {
			System.out.println("Factorial is not defined for negative numbers.");
		} else {
			long result = factorial(n);
			System.out.println("Factorial of " + n + " = " + result);
		}
		sc.close();
	}
}