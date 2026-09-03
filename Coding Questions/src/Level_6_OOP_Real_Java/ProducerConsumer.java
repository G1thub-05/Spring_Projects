package Level_6_OOP_Real_Java;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumer {

	public static void main(String[] args) {

		BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(5);

		Thread producer = new Thread(() -> {

			for (int i = 1; i <= 10; i++) {

				try {
					queue.put(i);
					System.out.println("Produced: " + i);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		});

		Thread consumer = new Thread(() -> {

			for (int i = 1; i <= 10; i++) {

				try {
					int value = queue.take();
					System.out.println("Consumed: " + value);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		});

		producer.start();
		consumer.start();
	}
}
