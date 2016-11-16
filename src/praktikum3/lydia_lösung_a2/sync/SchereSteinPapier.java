package praktikum3.lydia_lösung_a2.sync;

import java.util.HashMap;
import java.util.Map;

public class SchereSteinPapier {
    private String player1;
    private String player2;
    private Symbol symbol1;
    private Symbol symbol2;
    private Map<Integer, Integer> ergebnis;
    private final int[][] ergebnisMatrix = {{0, 2, 1}, {1, 0, 2}, {2, 1, 0}};
    
    /**
     * @author lucas_anders, Lydia Pflug Klasse bildet die Spiellogik ab
     */
    public SchereSteinPapier(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.ergebnis = new HashMap<>();
        ergebnis.put(0, 0);
        ergebnis.put(1, 0);
        ergebnis.put(2, 0);
    }
    
    public Symbol getSymbol(int id) {
        if (id == 1) {
            return symbol1;
        } else {
            return symbol2;
        }
    }
    
    public synchronized void RundeAuswerten() throws InterruptedException {
        // warten, bis beide gesetzt haben
        while (this.symbol1 == null || this.symbol2 == null) {
            this.wait();
        }
        int ergebnis = ergebnisMatrix[symbol1.ordinal()][symbol2.ordinal()];
        this.ergebnis.put(ergebnis, (this.ergebnis.get(ergebnis) + 1));
        this.symbol1 = null;
        this.symbol2 = null;
        System.out.println("Spieler " + ergebnis + " gewinnt");
        // es kann wieder gesetzt werden
        this.notifyAll();
    }
    
    public synchronized void choosePlayer1(Symbol symbol) throws InterruptedException {
        // warten, bis Symbol zur�ckgesetzt wird
        while (symbol1 != null) {
            this.wait();
        }
        this.symbol1 = symbol;
        System.out.println("Spieler 1 w�hlt " + symbol);
        this.notifyAll();
    }
    
    public synchronized void choosePlayer2(Symbol symbol) throws InterruptedException {
        while (symbol2 != null) {
            this.wait();
        }
        this.symbol2 = symbol;
        System.out.println("Spieler 2 w�hlt " + symbol);
        this.notifyAll();
    }
    
    public String printErgebnis() {
        return ("Unentschieden: " + ergebnis.get(0) + "\n" + player1 + ": " + ergebnis.get(1) + "\n"
            + player2 + ": "
            + ergebnis.get(2));
    }
}
