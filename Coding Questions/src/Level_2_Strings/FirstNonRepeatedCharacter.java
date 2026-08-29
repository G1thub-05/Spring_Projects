package Level_2_Strings;


import java.util.Scanner;

public class FirstNonRepeatedCharacter {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter a String: ");
		String str = sc.nextLine().toLowerCase();

		for (int i = 0; i < str.length(); i++) {

			int count = 0;

			for (int j = 0; j < str.length(); j++) {
				if (str.charAt(i) == str.charAt(j)) {
					count++;
				}
			}

			if (count == 1) {
				System.out.println(
						"First non-repeated character: " + str.charAt(i)
				);
				break;
			}
		}

		sc.close();
	}
}