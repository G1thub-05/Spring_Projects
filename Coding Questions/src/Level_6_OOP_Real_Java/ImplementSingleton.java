package Level_6_OOP_Real_Java;

class Singleton {

	// Private constructor
	private Singleton() {
	}

	// Static inner class
	private static class Holder {
		private static final Singleton INSTANCE = new Singleton();
	}

	// Global access point
	public static Singleton getInstance() {
		return Holder.INSTANCE;
	}
}

public class ImplementSingleton {

	public static void main(String[] args) {

		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();

		System.out.println(s1 == s2);   // true
	}
}