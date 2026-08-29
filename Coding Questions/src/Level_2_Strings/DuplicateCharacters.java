package Level_2_Strings;

import java.util.Scanner;

public class DuplicateCharacters {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter a String: ");
		String str = sc.nextLine().toLowerCase();

		for (int i = 0; i < str.length(); i++) {

			// Skip if this character was already checked
			boolean alreadyChecked = false;

			for (int k = 0; k < i; k++) {
				if (str.charAt(i) == str.charAt(k)) {
					alreadyChecked = true;
					break;
				}
			}

			if (alreadyChecked) {
				continue;
			}

			int count = 0;

			for (int j = 0; j < str.length(); j++) {
				if (str.charAt(i) == str.charAt(j)) {
					count++;
				}
			}

			if (count > 1) {
				System.out.println(str.charAt(i) + " = " + count);
			}
		}

		sc.close();
	}
}