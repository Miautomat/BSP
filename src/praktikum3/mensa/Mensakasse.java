package praktikum3.mensa;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Mensakasse{
	
	private ReentrantLock lock = new ReentrantLock();
	private ReentrantLock lockSemaphore = new ReentrantLock();
	private int kassenNummer;
	private final Semaphore semaphore = new Semaphore(1);
	private int warteschlangennummer=0;
	
	public Mensakasse(int nummer) {
		kassenNummer=nummer;
	}
	
	public void bezahlen(){
		
		lock.lock();
		try {
			//hier wird bezahlt
//			System.out.println("Es warten andere Studenten damit sie Bezahlen können: "+semaphore.getQueueLength());
			
			System.out.println("An Kasse "+kassenNummer+" wird bezahlt");
			
			Thread.currentThread().sleep(500);
			
		} catch (InterruptedException e) {

//			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
		if(lock.isHeldByCurrentThread()){
			lock.unlock();
		}
		
		
	}
	public Semaphore getSemaphore(){
		
		return semaphore;
	}
	public String getName(){
		
		return ""+kassenNummer;
		
	}
	

}
