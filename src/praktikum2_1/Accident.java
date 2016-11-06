package praktikum2_1;

import java.util.Random;

/**
 * Class that represents Accidents in the Racing
 * 
 * @author Patrik Höling, Mieke Narjes
 *
 */
public class Accident extends Thread {
	
	private int msekToDeath;
	
	public Accident() {
		msekToDeath= new Random().nextInt(2000);
		
		while(msekToDeath<0){
			msekToDeath= new Random().nextInt();
		}
	}
	
	@Override
	public void run() {
		super.run();
		
		while(!interrupted()){
			try {
				sleep(msekToDeath);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			interrupt();
			
			Thread.currentThread().stop();
			Thread.currentThread().stop();
			Thread.currentThread().stop();
		}	
	}
}
