package praktikum2_1;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Class which starts the Racing
 * 
 * @author Patrik Höling, Mieke Narjes
 *
 */
public class SimRace {
	ArrayList<Car> carList;
	public final int roundNr;

	/**
	 * Constructor initializes all necessary variables and starts the race
	 */
	public SimRace(int quantity, int rounds) {
		roundNr = rounds;
		carList = new ArrayList<>();
		this.createCars(quantity, rounds);
		this.race();
	}

	/**
	 * starts the race itself and runs the evaluation
	 */
	public void race() {
		Accident accident = new Accident(roundNr);

		// starting cars one by one
		for (Car c : carList) {
			c.start();
		}

		// starting accident
		accident.start();

		// Checking for Accident
		while (areAlive(carList)) {
			if (!accident.isAlive()) {
				System.out.println("unfall");
				return;
			}
		}
		
		System.out.println(endAnalysis(carList));
	}

	/**
	 * creates all cars wanted in the race
	 * 
	 * @param quantity
	 * @param round
	 */
	private void createCars(int quantity, int round) {
		int rounds = round;
		ThreadGroup carGroup = new ThreadGroup("CarGroup");

		for (int i = 1; i <= quantity; i++) {
			String carNr = "Wagen" + i;
			carList.add(new Car(carNr, rounds, carGroup));
		}
	}
	
	/**
	 * sorts the cars by their racing time
	 */
	private void sortCars() {
		carList.sort(new Comparator<Car>() {
			@Override
			public int compare(Car o1, Car o2) {
				return (int) (o1.getTime() - o2.getTime());
			}
		});
	}

	/**
	 * returns true if all car-Threads are still alive
	 * 
	 * @param list
	 * @return
	 */
	private static boolean areAlive(ArrayList<Car> list) {
		boolean status = false;
		for (Car c : list) {
			if (c.isAlive()) {
				status = true;
			}
		}
		return status;
	}

	/**
	 * creates a String with the ranking of the race
	 * 
	 * @param list
	 * @return race result
	 */
	private String endAnalysis(ArrayList<Car> list) {
		int platz = 1;
		
		this.sortCars();

		StringBuilder sb = new StringBuilder();
		sb.append("---------ENDE DES RENNENS------------\n");

		for (Car c : list) {
			sb.append(platz).append(". Wagen: ").append(c.getName()).append(" Zeit: ").append(c.getTime()).append(" ms\n");
			platz++;
		}
		return sb.toString();
	}

	/**
	 * Main Method to actually run the race
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		new SimRace(50, 50);
	}
}
