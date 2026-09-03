package Level_4_Collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CharacterFrequencyUsingHashMap {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter a string: ");
		String str = sc.nextLine();

		HashMap<Character, Integer> map = new HashMap<>();

		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);

			map.put(ch, map.getOrDefault(ch, 0) + 1);
		}

		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		sc.close();
	}
}