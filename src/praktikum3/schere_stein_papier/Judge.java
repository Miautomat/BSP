package praktikum3.schere_stein_papier;

/**
 * Class that represents the Judge for a Schere-Stein-Papier game
 * 
 * @author Mieke Narjes, Patrick Höling
 */
public class Judge extends Thread {
    private Game game;
    
    public Judge(Game game) {
        this.game = game;
    }
    
    @Override
    public void run() {
        while (!interrupted()) {
            try {
                game.judgeRound();
            } catch (InterruptedException e) {
                interrupt();
            }
        }
        System.err.println("Judge stopped");
        System.out.println(game.endAnalysis());
    }
}
