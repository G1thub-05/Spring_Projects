package Level_6_OOP_Real_Java;

class InvalidAgeException extends Exception {

	public InvalidAgeException(String message) {
		super(message);
	}
}

public class CustomException {

	public static void main(String[] args) {

		int age = 15;

		try {

			if (age < 18) {
				throw new InvalidAgeException("Age must be 18 or above");
			}

			System.out.println("Eligible");

		} catch (InvalidAgeException e) {

			System.out.println(e.getMessage());
		}
	}
}