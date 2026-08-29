package Leve_1_JavaBasics;

import java.util.Scanner;

public class PrimeNumber {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter a number: ");
		int n = sc.nextInt();

		boolean isPrime = n > 1;

		// i <= Math.sqrt(n) = i * i <= n
		for (int i = 2; i * i <= n && isPrime; i++) {
			if (n % i == 0) {
				isPrime = false;
			}
		}

		System.out.println(n + (isPrime ? " is Prime" : " is Not Prime"));

		sc.close();
	}
}