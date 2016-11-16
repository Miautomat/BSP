package praktikum3.schere_stein_papier;

import java.util.Random;

/**
 * Thread-class to represent Players during a Schere-Stein-Papier Game
 * 
 * @author Mieke Narjes, Patrick Höling
 */
public class Player extends Thread {
    
    private Symbol choice;
    private int wins = 0;
    
    public Player(String name) {
        super(name);
    }
    
    /**
     * Enum that represents the game-Objects
     */
    public enum Symbol {
        SCHERE, STEIN, PAPIER
    }
    
    @Override
    public synchronized void run() {
        super.run();
        
        while (!interrupted()) {
            choice();
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        interrupt();
    }
    
    public Symbol getChoice() {
        return this.choice;
    }
    
    public void clearChoice() {
        choice = null;
    }
    
    public void setWin() {
        wins++;
    }
    
    public int getWins() {
        return this.wins;
    }
    
    /**
     * represents the players choice between Schere, Stein, Papier
     * 
     * @return random GameObject
     */
    private Symbol choice() {
        Symbol choice = null;
        
        int rand = new Random().nextInt(2) + 1;
        switch (rand) {
        case 1:
            choice = Symbol.SCHERE;
            break;
        case 2:
            choice = Symbol.STEIN;
            break;
        case 3:
            choice = Symbol.PAPIER;
            break;
        }
        return choice;
    }
}
