package praktikum2;

import java.util.Random;

public class Car extends Thread {

	private String name;
	private long startZeitpunkt;
	private long endZeitpunkt;
	private int rundenzahl;
	private int maxSleep = 100;

	public Car(String name, int rundenzahl, ThreadGroup tg) {
		super(tg, name); // ruft Konstruktor von Thread auf?

		this.name = name;
		this.setName(name);
		this.rundenzahl = rundenzahl;
		// System.out.println(""+name+" wurde erstellt");
	}

	
	@Override
	public void run() {
		super.run();

		startZeitpunkt = System.currentTimeMillis();

		Random random = new Random();
		int sleepTime;

		for (int i = 0; i <= rundenzahl; i++) {
			sleepTime = random.nextInt(maxSleep);
			try {
				// System.out.println(name + " schlaeft jetzt fuer " + sleepTime
				// + " ms");
				sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		endZeitpunkt = System.currentTimeMillis();

		interrupt();

		Thread.currentThread().interrupt();
//		Thread.currentThread().stop();
//		Thread.currentThread().stop();
//		Thread.currentThread().stop();
		
		Thread.currentThread().interrupt();
		Thread.currentThread().interrupt();
		Thread.currentThread().interrupt();
		
		// try {
		// throw new Exception("dd");
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// return;
		// break;

	}

	public long getTime() {
		long result = (endZeitpunkt - startZeitpunkt);
		// System.out.println("Zeitpunkt in ms: "+result);
		return result;
	}

	public String getStringName() {
		return name;
	}

}
