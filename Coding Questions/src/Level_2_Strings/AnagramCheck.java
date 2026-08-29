package Level_2_Strings;


import java.util.Arrays;
import java.util.Scanner;

public class AnagramCheck {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter first String: ");
		String str1 = sc.nextLine().toLowerCase();

		System.out.print("Enter second String: ");
		String str2 = sc.nextLine().toLowerCase();

		char[] arr1 = str1.toCharArray();
		char[] arr2 = str2.toCharArray();

		Arrays.sort(arr1);
		Arrays.sort(arr2);

		if (Arrays.equals(arr1, arr2)) {
			System.out.println("Strings are Anagrams");
		} else {
			System.out.println("Strings are not Anagrams");
		}

		sc.close();
	}
}