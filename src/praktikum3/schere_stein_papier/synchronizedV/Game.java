package praktikum3.schere_stein_papier.synchronizedV;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import praktikum3.schere_stein_papier.synchronizedV.Player.Symbol;

/**
 * Class that represents the game itself
 * 
 * @author Mieke Narjes, Patrick Höling
 */

public class Game {
    Player player1;
    Player player2;
    private Judge judge;
    private int draw;
    Symbol player1Choice;
    Symbol player2Choice;
    private List<Player> playerList = new ArrayList<>();
    
    /**
     * Initializes Players and judges and starts the game
     * 
     * @throws InterruptedException
     */
    public Game(String player1, String player2) throws InterruptedException {
        createPlayers(player1, player2);
        this.judge = new Judge(this);
    }
    
    private void createPlayers(String player1, String player2) {
        this.player1 = new Player(player1, this);
        this.player2 = new Player(player2, this);
        // List only for end-comparison
        playerList.add(this.player1);
        playerList.add(this.player2);
    }
    
    synchronized void player1Choosing(Symbol choice) throws InterruptedException {
        // wait until the table is clear
        while (player1Choice != null) {
            this.wait();
        }
        
        this.player1Choice = choice;
        // notify everyone - especially the judge
        System.out.println(player1.getName() + " chooses " + choice);
        notifyAll();
    }
    
    synchronized void player2Choosing(Symbol choice) throws InterruptedException {
        // wait until the table is clear
        while (player2Choice != null) {
            this.wait();
        }
        
        this.player2Choice = choice;
        // notify everyone - especially the judge
        System.out.println(player2.getName() + " chooses " + choice);
        notifyAll();
    }
    
    /**
     * will add a win for the winner and clear the table
     * 
     * @throws InterruptedException
     */
    synchronized void judgeRound() throws InterruptedException {
        while (player1Choice == null || player2Choice == null) {
            // wait until both players made their choice
            wait();
        }
        // find winner and set win or draw
        Player winner = this.compareChoices(player1, player2);
        if (winner != null) {
            System.out.println("\t" + winner.getName() + " wins");
            winner.setWin();
        } else {
            System.out.println("\tdraw");
            draw++;
        }
        // reset the table - start again
        clearTable();
        notifyAll();
    }
    
    /**
     * @param o1
     * @param o2
     * @return winner or null if draw
     */
    private Player compareChoices(Player o1, Player o2) {
        Symbol obj1 = player2Choice;
        Symbol obj2 = player1Choice;
        
        Player winner = null;
        switch (obj1) {
        case SCHERE:
            switch (obj2) {
            case SCHERE:
                break;
            case STEIN:
                winner = o2;
                break;
            case PAPIER:
                winner = o1;
                break;
            };
            break;
        case STEIN:
            switch (obj2) {
            case SCHERE:
                winner = o1;
                break;
            case STEIN:
                break;
            case PAPIER:
                winner = o2;
                break;
            };
            break;
        case PAPIER:
            switch (obj2) {
            case SCHERE:
                winner = o2;
                break;
            case STEIN:
                winner = o1;
                break;
            case PAPIER:
                break;
            };
            break;
        }
        return winner;
    }
    
    /**
     * clears table and players Choice
     */
    private void clearTable() {
        player1Choice = null;
        player2Choice = null;
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
    
    String endAnalysis() {
        this.sortByWins();
        Player p1 = playerList.get(1);
        Player p2 = playerList.get(0);
        
        StringBuilder sb = new StringBuilder();
        sb.append("---------ENDE DES CHING-CHANG-CHONG TURNIERS------------\n")
            .append("1rst\t")
            .append(p1.getName())
            .append(" Gewinne: ")
            .append(p1.getWins())
            .append("\n")
            .append("2nd\t")
            .append(p2.getName())
            .append(" Gewinne: ")
            .append(p2.getWins())
            .append("\n")
            .append("Unentschieden: ")
            .append(draw);
        
        return sb.toString();
    }
    
    public static void main(String[] args) throws InterruptedException {
        Game g = new Game("Anna", "Hugo");
        g.player1.start();
        g.player2.start();
        g.judge.start();
        TimeUnit.SECONDS.sleep(5);
        g.player1.interrupt();
        g.player2.interrupt();
        g.judge.interrupt();
    }
}
