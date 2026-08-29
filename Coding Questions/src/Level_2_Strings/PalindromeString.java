package Level_2_Strings;

import java.util.Scanner;

public class PalindromeString {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter a String: ");
		String org = sc.nextLine();

		String reverse = "";

		for (int i = org.length() - 1; i >= 0; i--) {
			reverse = reverse + org.charAt(i);
		}

		if (org.equals(reverse)) {
			System.out.println(org + " is a Palindrome String");
		} else {
			System.out.println(org + " is not a Palindrome String");
		}

		sc.close();
	}
}
