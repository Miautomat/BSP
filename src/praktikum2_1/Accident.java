package praktikum2_1;

import java.util.Random;

/**
 * Class that represents Accidents in the Racing
 * 
 * @author Patrik Höling, Mieke Narjes
 *
 */
public class Accident extends Thread {

	private int millisToDeath;
	
	public Accident(int rounds) {
		millisToDeath= new Random().nextInt((rounds + 1)*100);
		
		while(millisToDeath < 0){
			millisToDeath = new Random().nextInt();
		}
	}
	
	@Override
	public void run() {
		super.run();
		
		while(!interrupted()){
			try {
				sleep(millisToDeath);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			interrupt();
			Thread.currentThread().interrupt();
			Thread.currentThread().interrupt();
			Thread.currentThread().interrupt();
			
//			
//			Thread.currentThread().stop();
//			Thread.currentThread().stop();
//			Thread.currentThread().stop();
		}	
	}
}
