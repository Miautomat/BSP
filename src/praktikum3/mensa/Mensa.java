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
		
		
		
		
		

	}

}
