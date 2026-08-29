package Level_2_Strings;


import java.util.Scanner;

public class ReverseWords {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter a sentence: ");
		String str = sc.nextLine();

		String[] words = str.split(" ");

		for (int i = words.length - 1; i >= 0; i--) {
			System.out.print(words[i] + " ");
		}

		sc.close();
	}
}