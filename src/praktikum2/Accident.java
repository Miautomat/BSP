package praktikum2;

import java.util.ArrayList;
import java.util.Random;

public class Accident extends Thread {
	
	private int millisToDeath;
	ArrayList<Car> alleAutos;
	boolean stopMe = false;
	
	public Accident(ArrayList<Car> autoliste) {

		
		millisToDeath= new Random().nextInt(2000);
		
		
		while(millisToDeath<0){
			millisToDeath= new Random().nextInt();
			
		}
		
		
		alleAutos=autoliste;
		
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
			
			for(Car elem : alleAutos){
//				elem.interrupt();
				
			}
			
			interrupt();
			
//			System.exit(0);
			
//			try {
//				throw new Exception("d");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			
			
			
			
			Thread.currentThread().stop();
			Thread.currentThread().stop();
			Thread.currentThread().stop();
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
	private void stopMyself(){
		stopMe=false;
	}

}
