package praktikum3.schere_stein_papier;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class that represents the game itself
 * 
 * @author Mieke Narjes, Patrick Höling
 */

public class Play {
    private Player player1;
    private Player player2;
    private List<Player> playerList = new ArrayList<>();
    
    private long time = System.currentTimeMillis();
    private long timelimit;
    
    /**
     * Initializes Players and judges and starts the game
     * 
     * @throws InterruptedException
     */
    public Play(long timelimit, String player1, String player2) {
        createPlayers(player1, player2);
        play();
        this.timelimit = time + timelimit;
    }
    
    public synchronized void play() {
        Judge judge = new Judge(player1, player2);
        player1.start();
        player2.start();
        judge.start();
        
        while (System.currentTimeMillis() <= timelimit) {
            try {
                wait(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            clearTable();
            notifyAll();
        }
        
        player1.interrupt();
        player2.interrupt();
        judge.interrupt();
        
        System.out.println(endAnalysis(playerList));
    }
    
    private void createPlayers(String player1, String player2) {
        this.player1 = new Player(player1);
        this.player2 = new Player(player2);
        playerList.add(this.player1);
        playerList.add(this.player2);
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
    
    private String endAnalysis(List<Player> competetors) {
        int place = 1;
        
        this.sortByWins();
        Player p1 = competetors.get(0);
        Player p2 = competetors.get(1);
        
        StringBuilder sb = new StringBuilder();
        sb.append("---------ENDE DES CHING-CHANG-CHONG TURNIERS------------\n")
            .append(place)
            .append(". ")
            .append(p1.getName())
            .append(" Gewinne: ")
            .append(p1.getWins())
            .append("\n")
            .append(place)
            .append(". ")
            .append(p2.getName())
            .append(" Gewinne: ")
            .append(p2.getWins())
            .append("\n");
        
        return sb.toString();
    }
    
    public static void main(String[] args) {
        new Play(200000, "Anna", "Hugo");
    }
}
