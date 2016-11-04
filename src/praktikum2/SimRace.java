package praktikum2;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Eine Klasse die ein Autorennen startet
 * @author Höling
 *
 */
public class SimRace {

	ThreadGroup wagenGruppe = new ThreadGroup("WagenGruppe");
	int rundenZahl=10;
	
	
	
	
	public SimRace() {
		
		Car wagen1 = new Car("Wagen 1", rundenZahl, wagenGruppe);
		Car wagen2 = new Car("Wagen 2", rundenZahl, wagenGruppe);
		Car wagen3 = new Car("Wagen 3", rundenZahl, wagenGruppe);
		Car wagen4 = new Car("Wagen 4", rundenZahl, wagenGruppe);
		Car wagen5 = new Car("Wagen 5", rundenZahl, wagenGruppe);
		
		
		ArrayList<Car> wagenliste = new ArrayList<Car>();
		wagenliste.add(wagen1);
		wagenliste.add(wagen2);
		wagenliste.add(wagen3);
		wagenliste.add(wagen4);
		wagenliste.add(wagen5);
		
		
		wagen1.start();
		wagen2.start();
		wagen3.start();
		wagen4.start();
		wagen5.start();
		
		
		
		
		Accident unfall = new Accident(wagenliste);
		unfall.start();
		
		
//		while(!unfall.isAlive()){
//			System.out.println("unfall lauft noch");
//		}
		
		
		while((wagen1.isAlive()||wagen2.isAlive()||wagen3.isAlive()||wagen4.isAlive()||wagen5.isAlive())){
//			System.out.println("warten");
			
			if(unfall.isAlive()){
//				System.out.println("unfall lebt noch");
				
			}else{
				
				System.out.println("unfall");
				return;
			}
			
//			try {
//				Thread.currentThread().sleep(10);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		
//		
//		wagen1.join();
//		wagen2.join();
//		wagen3.join();
//		wagen4.join();
//		wagen5.join();
//		
		
//		synchronized (wagenGruppe) {
//		
//			while(wagenGruppe.activeCount()>0){
//				
////				wagenGruppe.interrupt();
//
//				System.out.println("es sind noch "+wagenGruppe.activeCount()+" threads aktiv");
//			
//			}
//		}
		
//		Car[] tg = new Car[5];
//		
//		wagenGruppe.enumerate(tg);
//		
//		System.out.println("wagen 1 millis: "+tg[0].getTime());
	

		wagenliste.sort(new Comparator<Car>() {
			@Override
			public int compare(Car o1, Car o2) {
				// TODO Auto-generated method stub
				
//System.out.println("vergleiche "+o1.getName()+" mit "+o2.getName());
//System.out.println("Die Zeiten sind: "+o1.getTime()+" o2: "+o2.getTime());
				return (int) (o1.getTime()-o2.getTime());
			}
		});
		
		
		
		
		
		System.out.println("---------ENDE DES RENNENS------------");
		int platz=1;
		for(Car wagen : wagenliste){
			
			System.out.println(platz+". Wagen: "+wagen.getName()+" Zeit: "+wagen.getTime());
			
			platz++;
		}
		
		
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		new SimRace();
		
		
	}
	
	
}
