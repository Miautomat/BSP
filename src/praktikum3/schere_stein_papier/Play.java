package praktikum3.schere_stein_papier;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import praktikum3.schere_stein_papier.Player.Symbol;

/**
 * Class that represents the game itself
 * 
 * @author Mieke Narjes, Patrick Höling
 *
 */

public class Play extends Thread{
	private Player player1;
	private Player player2;

	/**
	 * Initializes Players and judges and starts the game 
	 */
	public Play() {
		
	}
	
	private void createPlayers() {
		player1 = new Player("Anna");
		player2 = new Player("Hugo");
	}
	
	private void judging(){
		Player winner = this.compare(player1, player2);
		
		if (winner != null){
			winner.setWin();
		}
	}
	
	/**
	 * @param o1
	 * @param o2
	 * @return winner or null if draw
	 */
	private Player compare(Player o1, Player o2){
		Symbol obj1 = o1.getObj();
		Symbol obj2 = o2.getObj();
		
		Player winner = null;
		
		if(obj1 == Symbol.SCHERE){
			switch(obj2){
			case SCHERE: break;
			case STEIN: winner = o2; break;
			case PAPIER: winner = o1; break;
			}
		} else if (obj1 == Symbol.STEIN){
			switch(obj2){
			case SCHERE: winner = o1; break;
			case STEIN: break;
			case PAPIER: winner = o2; break;
			}
		} else if (obj1 == Symbol.PAPIER){
			switch(obj2){
			case SCHERE: winner = o2; break;
			case STEIN: winner = o1; break;
			case PAPIER: break;
			}
		}
		return winner;
	}
	
//	private void clearTable(){
//		for (Player p : playerList) {
//			p.clearObj();
//		}
//	}
//	
//	private void sortByWins(){
//		playerList.sort(new Comparator<Player>(){
//			@Override
//			public int compare(Player o1, Player o2) {
//				return (o1.getWins() - o2.getWins());
//			}
//		});
//	}
}
