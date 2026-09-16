package Level_4_Collections;

import java.util.Arrays;

public class SecondHighest {

	public static void main(String[] args) {

		int[] arr = {10, 5, 25, 8, 15, 25};

		int secondHighest = Arrays.stream(arr)
				.distinct()
				.boxed()
				.sorted((a, b) -> Integer.compare(b, a))
				.skip(1)
				.findFirst()
				.get();

		System.out.println("Second Highest: " + secondHighest);
	}
}