package Level_5_Java_8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlattenNestedLists {

	public static void main(String[] args) {

		List<List<Integer>> nestedList = Arrays.asList(
				Arrays.asList(1, 2, 3),
				Arrays.asList(4, 5),
				Arrays.asList(6, 7, 8)
		);

		List<Integer> flatList = nestedList.stream()
				.flatMap(list -> list.stream())
				.collect(Collectors.toList());

		System.out.println("Flattened List: " + flatList);
	}
}