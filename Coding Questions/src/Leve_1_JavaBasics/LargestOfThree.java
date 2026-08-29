package Leve_1_JavaBasics;

import java.util.Scanner;

public class LargestOfThree {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter first number: ");
		int a = sc.nextInt();

		System.out.print("Enter second number: ");
		int b = sc.nextInt();

		System.out.print("Enter third number: ");
		int c = sc.nextInt();

		if (a >= b && a >= c) {
			System.out.println(a + " is the largest");
		} else if (b >= a && b >= c) {
			System.out.println(b + " is the largest");
		} else {
			System.out.println(c + " is the largest");
		}

		sc.close();
	}
}