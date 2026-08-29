package Leve_1_JavaBasics;

import java.util.Scanner;

public class ArmstrongNumber {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter the number: ");

		if(sc.hasNextInt()) {
			int number = sc.nextInt();

			if(number < 0) {
				System.out.println("Armstrong number is defined for non-negative integers.");
			} else {

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

				System.out.println(
						"Sum of digits in " + number + " is " + sum + " and it is " + (sum == number ? "an Armstrong Number" : "not an Armstrong Number"));
			}

		} else {
			System.out.println("Input is not a valid integer.");
		}

		sc.close();
	}
}