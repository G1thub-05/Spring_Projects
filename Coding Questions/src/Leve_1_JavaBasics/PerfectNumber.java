package Leve_1_JavaBasics;

import java.util.Scanner;

public class PerfectNumber {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the Number : ");
		int num = sc.nextInt();
		int sum = 0;
		int temp = num;
		System.out.print("The Divisor for " + num + " is : ");
		for(int i = 1; i < num; i++) {
			if(temp % i == 0) {
				sum = sum + i;
				System.out.print(i + " ");
			}
		}
		if(sum == num) {
			System.out.println("\nThe sum of divisor is : " + sum + " == Given Number " + num);
			System.out.print("\nThe Number is a perfect number");
		} else {
			System.out.println("\nThe sum of divisor is : " + sum + " != Given Number " + num);
			System.out.print("The Number is not a perfect number");
		}
		sc.close();
	}
}
