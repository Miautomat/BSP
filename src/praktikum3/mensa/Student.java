package praktikum3.mensa;

import java.util.ArrayList;

public class Student extends Thread {
	
	
	long maxEssensZeit = 100;
	ArrayList<Mensakasse> listeKassen;
	
	public Student(long waittime, ArrayList<Mensakasse> listeKasse) {
		super();
		
		maxEssensZeit=waittime;
		listeKassen=listeKasse;
		
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		while(!isInterrupted()){
			
			
		}
		
		
		
	}
	private Mensakasse getMinimum(){
		
		Mensakasse minK=null;
		int minAnzahl;
		
		for(Mensakasse elem : listeKassen){
			
			if(minK==null){
				
			}
			
			
		}
		
		return null;
		
	}

}
