package praktikum3.lydia_lösung_a2.locks;

import java.util.Random;

/**
 * @author lucas_anders, Lydia Pflug
 */
public class Spieler extends Thread {
    
    SchereSteinPapier spiel;
    int id;
    Symbol[] symbols;
    Random random;
    
    public Spieler(SchereSteinPapier spiel, int id) {
        this.spiel = spiel;
        this.id = id;
        symbols = Symbol.values();
        random = new Random();
    }
    
    @Override
    public void run() {
        while (!interrupted()) {
            try {
                Symbol symbol = symbols[(random.nextInt(3))];
                
                if (id == 1) {
                    spiel.choosePlayer1(symbol);
                } else {
                    spiel.choosePlayer2(symbol);
                }
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
        System.err.println("Spieler " + id + " beendet");
    }
}
