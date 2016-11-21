package praktikum3.mensa;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Student extends Thread {

	int maxEssensZeit = 100;
	ArrayList<Mensakasse> listeKassen;
	boolean stoppen = false;

	public Student(int waittime, ArrayList<Mensakasse> listeKasse) {
		super();

		maxEssensZeit = waittime;
		listeKassen = listeKasse;

	}

	@Override
	public void run() {

//		super.run();

		while (!isInterrupted() && !stoppen) {

			
				

				Mensakasse aktuelleKasse = getMinimum();
				Semaphore semaphore = aktuelleKasse.getSemaphore();

				System.out.println("\t"+getName() + " stellt sich an Kasse "+aktuelleKasse.getName()+" an");

				try {
					semaphore.acquire();
					System.out.println("\t"+"Student "+this.getName()+" bezahlt an Kasse "+aktuelleKasse.getName());
					
					aktuelleKasse.bezahlen();
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					interrupt();
//					e.printStackTrace();
					
				} finally {
					

//					stoppen=true;
//					interrupt();
					semaphore.release();
				}
				
				
//				semaphore.release();

//				System.out.println("\t"+getName() + " hat bezahlt und geht essen");
				geheEssen();
//				System.out.println("\t"+getName()+" hat gegessen");

		
				

		}
		System.out.println(getName()+" geht nach Hause");

	}

	private Mensakasse getMinimum() {

		Mensakasse minK = null;
		int minAnzahl = Integer.MAX_VALUE;

		for (Mensakasse elem : listeKassen) {

			if (minK == null) {
				minK = elem;
				minAnzahl = elem.getQueueLength();
			}
			int tempAnzahl = elem.getQueueLength();
			if (minAnzahl > tempAnzahl) {
				minK = elem;
				minAnzahl = tempAnzahl;

			}

		}

		return minK;

	}

	private void geheEssen() {
		Random schlafenszeit = new Random();

		try {
			
			Thread.currentThread().sleep(schlafenszeit.nextInt(maxEssensZeit));
		} 
		catch (InterruptedException e) {
//			interrupt();
//			stoppen=true;
		}

	}

	public void stopThat() {
		
		stoppen = true;

	}

}
