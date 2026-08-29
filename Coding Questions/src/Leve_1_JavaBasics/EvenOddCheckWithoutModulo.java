package Leve_1_JavaBasics;

import java.util.Scanner;

public class EvenOddCheckWithoutModulo {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter The Number : ");
		int num = sc.nextInt();

		// Method 1
		String [] arr = {"The given number is Even", "The given number is Odd"};
		System.out.println(arr[num%2]);

		// Method 2
		System.out.println((num & 1) == 0 ? arr[0] : arr[1]);

		// Method 3
		int result = num / 2;
		System.out.println("The given number is " + ((result * 2 == num) ? "Even" : "Odd"));
	}
}
