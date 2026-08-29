package Leve_1_JavaBasics;

import java.util.Scanner;

public class PrimeNumberInRange {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter a number: ");
		int n = sc.nextInt();

		for(int i = 2; i <= n; i++) {
			boolean isPrime = true;

			for(int j = 2; j * j <= i; j++) {
				if(i % j == 0) {
					isPrime = false;
					break;
				}
			}

			if(isPrime) {
				System.out.print(i + " ");
			}
		}

		sc.close();
	}
}