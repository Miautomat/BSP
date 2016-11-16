package praktikum3.schere_stein_papier;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import praktikum3.schere_stein_papier.Player.Symbol;

/**
 * Class that represents the game itself
 * 
 * @author Mieke Narjes, Patrick H�ling
 *
 */

public class Play extends Thread {
	private Player player1;
	private Player player2;
	private List<Player> playerList = new ArrayList<>();
	
	private int time = 0;
	private int timelimit = 0;

	/**
	 * Initializes Players and judges and starts the game
	 */
	public Play() {
		/**
		 * create Player
		 * 
		 * while(timelimit noch nicht erreicht){ pausiere Player judging
		 * clearTable restart Player }
		 * 
		 * endauswertung syso
		 */
		createPlayers();
		
		while(time <= timelimit){
			//pausiere Player
			judging();
			clearTable();
			// restart Player
		}
		System.out.println(endAnalysis(playerList));
	}

	private void createPlayers() {
		player1 = new Player("Anna");
		player2 = new Player("Hugo");
		playerList.add(player1);
		playerList.add(player2);
	}

	private void judging() {
		Player winner = this.compare(player1, player2);

		if (winner != null) {
			winner.setWin();
		}
	}

	/**
	 * @param o1
	 * @param o2
	 * @return winner or null if draw
	 */
	private Player compare(Player o1, Player o2) {
		Symbol obj1 = o1.getChoice();
		Symbol obj2 = o2.getChoice();

		Player winner = null;

		if (obj1 == Symbol.SCHERE) {
			switch (obj2) {
			case SCHERE:
				break;
			case STEIN:
				winner = o2;
				break;
			case PAPIER:
				winner = o1;
				break;
			}
		} else if (obj1 == Symbol.STEIN) {
			switch (obj2) {
			case SCHERE:
				winner = o1;
				break;
			case STEIN:
				break;
			case PAPIER:
				winner = o2;
				break;
			}
		} else if (obj1 == Symbol.PAPIER) {
			switch (obj2) {
			case SCHERE:
				winner = o2;
				break;
			case STEIN:
				winner = o1;
				break;
			case PAPIER:
				break;
			}
		}
		return winner;
	}

	private void clearTable() {
		player1.clearChoice();
		player2.clearChoice();
	}

	private void sortByWins() {
		playerList.sort(new Comparator<Player>() {
			@Override
			public int compare(Player o1, Player o2) {
				return (o1.getWins() - o2.getWins());
			}
		});
	}
	
	private String endAnalysis(List<Player> competetors){
		int place = 1;

		this.sortByWins();
		Player p1 = competetors.get(0);
		Player p2 = competetors.get(1);

		StringBuilder sb = new StringBuilder();
		sb.append("---------ENDE DES CHING-CHANG-CHONG TURNIERS------------\n").append(place).append(". ").append(p1.getName()).append(" Gewinne: ").append(p1.getWins())
		.append("\n").append(place).append(". ").append(p2.getName()).append(" Gewinne: ").append(p2.getWins())
		.append("\n");

		return sb.toString();
	}
}