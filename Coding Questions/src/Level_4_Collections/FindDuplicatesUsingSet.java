package Level_4_Collections;

import java.util.HashSet;

public class FindDuplicatesUsingSet {

	public static void main(String[] args) {

		int[] arr = {1, 2, 3, 2, 4, 3, 5, 1};

		HashSet<Integer> set = new HashSet<>();

		for (int num : arr) {
			if (!set.add(num)) {
				System.out.println("Duplicate: " + num);
			}
		}
	}
}