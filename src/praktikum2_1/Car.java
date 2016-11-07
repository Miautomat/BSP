package praktikum2_1;

import java.util.Random;

/**
 * Class to represent cars in the race
 * 
 * @author Patrik Höling, Mieke Narjes
 *
 */
public class Car extends Thread {

	private String name;
	private long startTime;
	private long endTime;
	private int roundNr;
	private int maxSleep = 100;

	public Car(String name, int rounds, ThreadGroup tg) {
		super(tg, name);
		this.name = name;
		this.setName(name);
		this.roundNr = rounds;
	}

	@Override
	public void run() {
		super.run();

		startTime = System.currentTimeMillis();
		
		Random random = new Random();
		int sleepTime;

		for (int i = 0; i <= roundNr; i++) {
			sleepTime = random.nextInt(maxSleep);
			try {
				sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		
		endTime = System.currentTimeMillis();

		interrupt();

		Thread.currentThread().interrupt();
		Thread.currentThread().interrupt();
		Thread.currentThread().interrupt();
		Thread.currentThread().interrupt();
//		Thread.currentThread().stop();
//		Thread.currentThread().stop();
	}

	public long getTime() {
		long result = (endTime - startTime);
		return result;
	}

	public String getStringName() {
		return name;
	}
}
