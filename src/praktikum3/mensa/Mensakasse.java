package praktikum3.mensa;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Mensakasse{
	
	private ReentrantLock lock = new ReentrantLock();
	private ReentrantLock lockSemaphore = new ReentrantLock();
	private int kassenNummer;
	private final Semaphore semaphore = new Semaphore(100);
	
	public Mensakasse(int nummer) {
		kassenNummer=nummer;
	}
	
	public void bezahlen(){
		
		lock.lock();
		try {
			//hier wird bezahlt
			System.out.println("An Kasse "+kassenNummer+" wird bezahlt");
		} finally {
			lock.unlock();
		}
		lock.unlock();
	}
	public int getHoldCount(){
		
		return lock.getHoldCount();
	}
	

}
