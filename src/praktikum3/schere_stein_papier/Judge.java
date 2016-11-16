package praktikum3.schere_stein_papier;

import praktikum3.schere_stein_papier.Player.Symbol;

/**
 * Class that represents the Judge for a Schere-Stein-Papier game
 * 
 * @author Mieke Narjes, Patrick H�ling
 */
public class Judge extends Thread {
    private Player player1, player2;
    
    public Judge(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }
    
    @Override
    public synchronized void run() {
        super.run();
        
        while (!interrupted()) {
            judging(player1, player2);
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        interrupt();
    }
    
    private void judging(Player player1, Player player2) {
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
}
