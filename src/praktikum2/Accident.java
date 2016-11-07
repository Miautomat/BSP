package praktikum2;

import java.util.ArrayList;
import java.util.Random;

public class Accident extends Thread {
	
	private int millisToDeath;
	private ArrayList<Car> allCars;
	private boolean stopMe = false;
	
	public Accident(ArrayList<Car> carList) {
		millisToDeath= new Random().nextInt(2000);
		
		while(millisToDeath<0){
			millisToDeath= new Random().nextInt();
		}
		allCars = carList;
	}
	
	@Override
	public void run() {
		super.run();
		
		while(!interrupted()){
			try {
				sleep(millisToDeath);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// wozu diese for Schleife wenn sie nciht genutzt wird???
//			for(Car elem : allCars){
//			elem.interrupt();
//			}
			
			interrupt();
			
//			System.exit(0);
			
//			try {
//				throw new Exception("d");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
//			Thread.currentThread().stop();
//			Thread.currentThread().stop();
//			Thread.currentThread().stop();
			Thread.currentThread().interrupt();
			Thread.currentThread().interrupt();
			Thread.currentThread().interrupt();
			
			stopMyself();
//			try {
//				this.join();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return;
		}	
	}
	
	// stopMe ist immer false hier???
	private void stopMyself(){
		stopMe = false;
	}

}
