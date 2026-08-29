package Level_3_Arrays;


import java.util.Scanner;

public class MoveZeros {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter array size: ");
		int n = sc.nextInt();

		int[] arr = new int[n];

		System.out.println("Enter array elements:");

		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

		int index = 0;

		// Move non-zero elements to the front
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != 0) {
				arr[index] = arr[i];
				index++;
			}
		}

		// Fill remaining positions with zeros
		while (index < arr.length) {
			arr[index] = 0;
			index++;
		}

		System.out.println("Array after moving zeros to the end:");

		for (int num : arr) {
			System.out.print(num + " ");
		}

		sc.close();
	}
}
