package Leve_1_JavaBasics;

import java.util.Scanner;

public class Fibonacci {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of terms: ");
		int n = sc.nextInt();

		int first = 0;
		int second = 1;

		System.out.print("Fibonacci Series: ");

		for (int i = 1; i <= n; i++) {
			System.out.print(first + " ");

			int next = first + second;
			first = second;
			second = next;
		}

		sc.close();
	}
}