package Level_4_Collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortList {

	public static void main(String[] args) {

		List<Integer> list = new ArrayList<>();

		list.add(5);
		list.add(2);
		list.add(8);
		list.add(1);
		list.add(3);

		// Ascending Order
		Collections.sort(list);
		System.out.println("Sorted List: " + list);

		// Descending Order
		list.sort(Collections.reverseOrder());
		System.out.println("Sorted List: " + list);

	}
}