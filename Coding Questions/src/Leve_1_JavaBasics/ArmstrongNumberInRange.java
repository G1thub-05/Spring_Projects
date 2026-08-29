package Leve_1_JavaBasics;

import java.util.Scanner;

public class ArmstrongNumberInRange {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter start number: ");
		int start = sc.nextInt();

		System.out.print("Enter end number: ");
		int end = sc.nextInt();

		if(start < 0 || end < 0 || start > end) {
			System.out.println("Invalid range.");
		} else {

			System.out.print("Armstrong Numbers: ");

			for(int number = start; number <= end; number++) {

				int count = 0;
				int n = number;

				// Count digits
				do {
					n /= 10;
					count++;
				} while(n != 0);

				int sum = 0;
				n = number;

				// Calculate Armstrong sum
				do {
					int rem = n % 10;
					sum += (int) Math.pow(rem, count);
					n /= 10;
				} while(n != 0);

				if(sum == number) {
					System.out.print(number + " ");
				}
			}
		}

		sc.close();
	}
}