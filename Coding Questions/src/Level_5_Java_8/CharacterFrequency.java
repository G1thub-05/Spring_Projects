package Level_5_Java_8;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CharacterFrequency {

	public static void main(String[] args) {

		String str = "hello";

		Map<Character, Long> frequency =
				str.chars()
						.mapToObj(c -> (char) c)
						.collect(Collectors.groupingBy(
								Function.identity(),
								LinkedHashMap::new,
								Collectors.counting()
						));

		System.out.println(frequency);
	}
}