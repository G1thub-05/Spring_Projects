package Level_5_Java_8;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

public class FindDuplicatesUsingStream {

	public static void main(String[] args) {

		List<Integer> numbers = Arrays.asList(1, 2, 3, 2, 4, 3, 5, 1);

		Set<Integer> seen = new HashSet<>();

		List<Integer> duplicates = numbers.stream()
				.filter(n -> !seen.add(n))
				.distinct()
				.collect(Collectors.toList());

		System.out.println("Duplicate Elements: " + duplicates);
	}
}