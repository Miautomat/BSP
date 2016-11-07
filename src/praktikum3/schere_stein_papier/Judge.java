package praktikum3.schere_stein_papier;

import praktikum3.schere_stein_papier.Player.Symbol;

/**
 * Class that represents the Judge for a Schere-Stein-Papier game
 * 
 * @author Mieke Narjes, Patrick Höling
 *
 */
public class Judge extends Thread{
	
	private void judging(){
		//this.compare(o1, o2)
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
}
