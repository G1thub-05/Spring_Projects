package Level_4_Collections;

import java.util.Arrays;

public class FindMaxMin {

	public static void main(String[] args) {

		int[] arr = {10, 5, 25, 8, 15};

		int max = Arrays.stream(arr).max().getAsInt();
		int min = Arrays.stream(arr).min().getAsInt();

		System.out.println("Maximum: " + max);
		System.out.println("Minimum: " + min);
	}
}