package Level_2_Strings;

import java.util.Scanner;

public class RemoveDuplicateCharacters {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter a String: ");
		String str = sc.nextLine();

		String result = "";

		for (int i = 0; i < str.length(); i++) {

			boolean isDuplicate = false;

			for (int j = 0; j < result.length(); j++) {
				if (str.charAt(i) == result.charAt(j)) {
					isDuplicate = true;
					break;
				}
			}

			if (!isDuplicate) {
				result = result + str.charAt(i);
			}
		}

		System.out.println("After removing duplicates: " + result);

		sc.close();
	}
}