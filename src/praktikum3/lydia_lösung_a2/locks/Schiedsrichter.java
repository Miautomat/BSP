package praktikum3.lydia_lösung_a2.locks;

/**
 * @author lucas_anders, Lucas Anders
 */
public class Schiedsrichter extends Thread {
    
    private SchereSteinPapier spiel;
    
    public Schiedsrichter(SchereSteinPapier spiel) {
        this.spiel = spiel;
    }
    
    @Override
    public void run() {
        while (!interrupted()) {
            try {
                spiel.RundeAuswerten();
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
        System.out.println(spiel.printErgebnis());
        System.err.println("Schiedsrichter beendet");
    }
}
