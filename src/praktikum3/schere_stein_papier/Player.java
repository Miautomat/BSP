package praktikum3.schere_stein_papier;

import java.util.Random;

/**
 * Thread-class to represent Players during a Schere-Stein-Papier Game
 * 
 * @author Mieke Narjes, Patrick Höling
 */
public class Player extends Thread {
    
    private String name;
    private Game game;
    private Symbol choice;
    private int wins = 0;
    
    public Player(String name, Game game) {
        super(name);
        this.name = name;
        this.game = game;
    }
    
    /**
     * Enum that represents the game-Objects
     */
    public enum Symbol {
        SCHERE, STEIN, PAPIER
    }
    
    /**
     * player will make a choice and 'give' it to the game
     */
    @Override
    public void run() {
        while (!interrupted()) {
            choice = choice();
            System.out.println(this.getName() + " making new choice " +
                choice);
            try {
                game.playersChoice(choice, this);
                System.out.println(this.getName() + " woke up from wait in playersChoice");
            } catch (InterruptedException e) {
                interrupt();
            }
        }
        System.err.println("Player " + name + " stopped");
    }
    
    /**
     * represents the players choice between Schere, Stein, Papier
     * 
     * @return random GameObject
     */
    Symbol choice() {
        System.out.println(this.getName() + " choice entered");
        Symbol choice = null;
        
        int rand = new Random().nextInt(3);
        System.out.println(this.getName() + " " + rand);
        switch (rand) {
        case 0:
            choice = Symbol.SCHERE;
            break;
        case 1:
            choice = Symbol.STEIN;
            break;
        case 2:
            choice = Symbol.PAPIER;
            break;
        }
        return choice;
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
}
