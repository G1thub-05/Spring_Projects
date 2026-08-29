package Leve_1_JavaBasics;

import java.util.Scanner;

public class EvenOddCheck2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter The Number : ");
		int num = sc.nextInt();

		String [] arr = {"The Number is Even", "The Number is Odd"};
		System.out.println((num & 1) == 0 ? "even" : "Odd");
		System.out.println(arr[num%2]);
	}
}
