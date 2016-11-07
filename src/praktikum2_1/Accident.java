package praktikum2_1;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class that represents Accidents in the Racing
 * 
 * @author Patrick Höling, Mieke Narjes
 *
 */
public class Accident extends Thread {

	private int millisToDeath;
	private ArrayList<Car> autoListe;
	
	public Accident(ArrayList<Car> carList, int rounds) {
		millisToDeath= new Random().nextInt((rounds + 1)*100);
		autoListe=carList;
	}
	
	@Override
	public void run() {
		super.run();
		
			try {
				sleep(millisToDeath);
			} catch (InterruptedException e) {
				//e.printStackTrace();
				interrupt();
			}
			
			for (Car auto : autoListe){
				auto.interrupt();
			}	
	}
}
