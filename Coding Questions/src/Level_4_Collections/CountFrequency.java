package Level_4_Collections;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CountFrequency {

	public static void main(String[] args) {

		List<Integer> numbers = Arrays.asList(1, 2, 3, 2, 4, 3, 2, 5);

		Map<Integer, Long> frequency =
				numbers.stream()
						.collect(Collectors.groupingBy(
								Function.identity(),
								Collectors.counting()
						));

		System.out.println(frequency);
	}
}