package Leve_1_JavaBasics;

import java.util.Scanner;

public class ArmstrongNumber {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter a number: ");
		int org = sc.nextInt();

		int modified = org;
		int sum = 0;

		while(modified != 0) {
			int digit = modified % 10;
			sum = sum + (digit * digit * digit);
			modified = modified / 10;
		}

		// 153 = 1³ + 5³ + 3³ = 153
		if(org == sum) {
			System.out.println(org + " is an Armstrong Number");
		} else {
			System.out.println(org + " is not an Armstrong Number");
		}

		sc.close();
	}
}