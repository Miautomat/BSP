package praktikum3.mensa;

import java.util.ArrayList;

public class Mensa {
	
	static ArrayList<Mensakasse> listeKassen = new ArrayList<Mensakasse>();
	static ArrayList<Student> listeStudenten = new ArrayList<Student>();
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Mensakasse kasse1 = new Mensakasse(1);
		listeKassen.add(kasse1);
		listeKassen.add(new Mensakasse(2));
		
			
		
		for(int i=1; i<=100;i++){
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
			elem.interrupt();
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
			Thread.currentThread().sleep(5000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
