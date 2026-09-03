package Level_4_Collections;

import java.util.Arrays;
import java.util.HashSet;

public class RemoveDuplicatesUsingSet {

	public static void main(String[] args) {

		int[] arr = {1, 2, 3, 2, 4, 3, 5, 1};

		HashSet<Integer> set = new HashSet<>();

		for (int num : arr) {
			set.add(num);
		}

		System.out.println("Original array: " + Arrays.toString(arr));
		System.out.println("After removing duplicates: " + set);
	}
}