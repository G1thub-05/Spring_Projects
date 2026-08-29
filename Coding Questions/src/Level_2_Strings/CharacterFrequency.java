package Level_2_Strings;
import java.util.Scanner;

public class CharacterFrequency {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter a String: ");
		String str = sc.nextLine().toLowerCase();

		for (int i = 0; i < str.length(); i++) {

			boolean alreadyChecked = false;

			// Check whether this character was already processed
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

			// Count frequency
			for (int j = 0; j < str.length(); j++) {
				if (str.charAt(i) == str.charAt(j)) {
					count++;
				}
			}

			System.out.println(str.charAt(i) + " = " + count);
		}

		sc.close();
	}
}