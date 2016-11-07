package praktikum2_1;

import java.util.Random;

/**
 * Class to represent cars in the race
 * 
 * @author Patrick Höling, Mieke Narjes
 *
 */
public class Car extends Thread {

	private String name;
	private long startTime;
	private long endTime;
	private int rounds;
	private int currRound = 0;
	private int maxSleep = 100;

	public Car(String name, int rounds, ThreadGroup tg) {
		super(tg, name);
		this.name = name;
		this.setName(name);
		this.rounds = rounds; // zu fahrende Runden
	}

	@Override
	public void run() {
		super.run();

		startTime = System.currentTimeMillis();

		Random random = new Random();
		int sleepTime;

		while (!isInterrupted()) {

			sleepTime = random.nextInt(maxSleep);
			try {
				currRound++;
				sleep(sleepTime);
			} catch (InterruptedException e) {
				//e.printStackTrace();
				interrupt();
			}
			if (currRound >= rounds) {
				interrupt();
			}
		}

		endTime = System.currentTimeMillis();
	}

	public int getCurrRound() {
		return this.currRound;
	}

	public long getTime() {
		long result = (endTime - startTime);
		return result;
	}

	public String getStringName() {
		return name;
	}
}
