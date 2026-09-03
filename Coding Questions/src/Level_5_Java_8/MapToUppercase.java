package Level_5_Java_8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapToUppercase {

	public static void main(String[] args) {

		List<String> names = Arrays.asList("rahul", "amit", "neha", "priya");

		List<String> upperCaseNames = names.stream()
				.map(name -> name.toUpperCase())
				.collect(Collectors.toList());

		System.out.println("Uppercase: " + upperCaseNames);
	}
}