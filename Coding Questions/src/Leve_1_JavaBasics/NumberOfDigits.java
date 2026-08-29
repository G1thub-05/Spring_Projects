package Leve_1_JavaBasics;

import java.util.Scanner;

public class NumberOfDigits {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number: ");
		if (sc.hasNextInt()) {
			int number = sc.nextInt();

			int count = 0;
			int n = number;

			if (n == 0) {
				count = 1;
			} else {
				while (n != 0) {
					n = n / 10;
					count++;
				}
			}

			System.out.println(
					"Number of digits in " + number + " is " + count
			);

		} else {
			System.out.println("Input is not a valid integer.");
		}

		sc.close();
	}
}