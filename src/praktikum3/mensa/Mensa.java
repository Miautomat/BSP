package praktikum3.mensa;

import java.util.ArrayList;

public class Mensa {
	
	static ArrayList<Mensakasse> listeKassen = new ArrayList<Mensakasse>();
	static ArrayList<Student> listeStudenten = new ArrayList<Student>();
	static Integer anzahlStudenten=20;
	static Integer anzahlKassen=3;
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		
		for(int i=1; i<=anzahlKassen;i++){
			
			listeKassen.add(new Mensakasse(i));
			
		}
		
			
		
		for(int i=1; i<=anzahlStudenten;i++){
			Student temp = new Student(1000, listeKassen);
			temp.setName("Student"+i);
			
			listeStudenten.add(temp);
			
			
			
		}
		
		for(Student elem : listeStudenten){
			
			elem.start();
			
		}
		
		
		Mensa mensa = new Mensa();
		
		mensa.sleep();
		
		for(Student elem : listeStudenten){
			System.out.println(elem.getName()+" wird beendet");
			
			
			elem.stopThat();
			elem.interrupt();

//			elem.stop();
			
			
			try {
				elem.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		

	}
	
	public void sleep(){
		try {
			Thread.currentThread().sleep(10000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
