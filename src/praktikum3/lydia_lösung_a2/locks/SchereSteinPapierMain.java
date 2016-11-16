package praktikum3.lydia_lösung_a2.locks;

import java.util.concurrent.TimeUnit;

/**
 * @author lucas_anders, Lydia Pflug
 */
public class SchereSteinPapierMain {
    
    public static void main(String[] args) throws InterruptedException {
        
        SchereSteinPapier spiel = new SchereSteinPapier("Player1", "Player2");
        
        Spieler spieler1 = new Spieler(spiel, 1);
        Spieler spieler2 = new Spieler(spiel, 2);
        Schiedsrichter schiedsrichter = new Schiedsrichter(spiel);
        
        spieler1.start();
        spieler2.start();
        schiedsrichter.start();
        TimeUnit.SECONDS.sleep(3);
        spieler1.interrupt();
        spieler2.interrupt();
        schiedsrichter.interrupt();
    }
    
}
