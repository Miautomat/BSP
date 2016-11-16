package praktikum3.mensa;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Student extends Thread {
	
	
	int maxEssensZeit = 100;
	ArrayList<Mensakasse> listeKassen;
	
	
	public Student(int waittime, ArrayList<Mensakasse> listeKasse) {
		super();
		
		maxEssensZeit=waittime;
		listeKassen=listeKasse;
		
		
	}
	
	@Override
	public void run() {
		
		super.run();
		
		while(!isInterrupted()){
			
			Mensakasse aktuelleKasse = getMinimum();
			Semaphore semaphore = aktuelleKasse.getSemaphore();
			
			System.out.println(getName()+" will bezahlen");
			
			try {
				semaphore.acquire();
				aktuelleKasse.bezahlen();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				
				semaphore.release();
			}
			semaphore.release();
			
			
			System.out.println(getName()+" hat bezahlt und geht essen");
			geheEssen();
			
			
		}
		
		
		
	}
	private Mensakasse getMinimum(){
		
		Mensakasse minK=null;
		int minAnzahl=Integer.MAX_VALUE;
		
		for(Mensakasse elem : listeKassen){
			
			if(minK==null){
				minK=elem;
				minAnzahl=elem.getSemaphore().getQueueLength();
			}else{
				int tempAnzahl = elem.getSemaphore().getQueueLength();
				if(minAnzahl>tempAnzahl){
					minK=elem;
					minAnzahl=tempAnzahl;
				}
				
				
			}
			
			
		}
		
		return minK;
		
	}
	
	private void geheEssen(){
		Random schlafenszeit = new Random();
		
		try {
			Thread.currentThread().sleep(schlafenszeit.nextInt(maxEssensZeit));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
