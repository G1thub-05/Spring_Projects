package Leve_1_JavaBasics;

import java.util.Scanner;

public class SwapNumbers {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.print("Enter first number: ");
		int a = sc.nextInt();
		System.out.print("Enter second number: ");
		int b = sc.nextInt();
		a = a + b - (b = a);
		System.out.println("After swapping: a = " + a + "b = " + b);
		sc.close();
	}
}