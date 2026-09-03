package Level_6_OOP_Real_Java;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {

	private final int capacity;

	public LRUCache(int capacity) {
		super(capacity, 0.75f, true);
		this.capacity = capacity;
	}

	@Override
	protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		return size() > capacity;
	}

	public static void main(String[] args) {

		LRUCache<Integer, String> cache = new LRUCache<>(3);

		cache.put(1, "A");
		cache.put(2, "B");
		cache.put(3, "C");

		System.out.println(cache);

		cache.get(1);       // 1 becomes recently used

		cache.put(4, "D");  // 2 is removed

		System.out.println(cache);
	}
}
