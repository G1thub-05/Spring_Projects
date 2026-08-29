package Level_3_Arrays;

public class ArrayElementCount {

	public static void main(String[] args) {

		int[] arr = {10, 20, 30, 40, 66, 50, 60, 70};

		countUsingLength(arr);
		countUsingForEach(arr);
		countUsingException(arr);
	}

	// Method 1: Using length
	public static void countUsingLength(int[] arr) {

		System.out.println(
				"Count using length: " + arr.length
		);
	}

	// Method 2: Using for-each loop
	public static void countUsingForEach(int[] arr) {

		int count = 0;

		for (int num : arr) {
			count++;
		}

		System.out.println(
				"Number of elements using for-each: " + count
		);
	}

	// Method 3: Using ArrayIndexOutOfBoundsException
	public static void countUsingException(int[] arr) {

		int count = 0;

		try {
			for (int i = 0; ; i++) {
				arr[i] = i;
				count++;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(
					"Number of elements using exception: " + count
			);
		}
	}
}