package praktikum3.lydia_lösung_a2.locks;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lucas_anders, Lydia Pflug Klasse bildet die Spiellogik ab
 */
public class SchereSteinPapier {
    private String player1;
    private String player2;
    private Symbol symbol1;
    private Symbol symbol2;
    private Map<Integer, Integer> ergebnis;
    private final int[][] ergebnisMatrix = {{0, 2, 1}, {1, 0, 2}, {2, 1, 0}};
    private final Lock lock = new ReentrantLock();
    private Condition voll = lock.newCondition();
    private Condition nichtVoll = lock.newCondition();
    private Condition gesetzt2 = lock.newCondition();
    
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
    
    /**
     * wertet eine gesetzte Symbole aus und setzt diese wieder zur�ck
     * 
     * @throws InterruptedException
     */
    public void RundeAuswerten() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (this.symbol1 == null || this.symbol2 == null) {
                nichtVoll.await();
            }
            int ergebnis = ergebnisMatrix[symbol1.ordinal()][symbol2.ordinal()];
            this.ergebnis.put(ergebnis, (this.ergebnis.get(ergebnis) + 1));
            this.symbol1 = null;
            this.symbol2 = null;
            System.out.println("Spieler " + ergebnis + " gewinnt");
            // signalisieren, Symbole k�nnen gesetzt werden
            voll.signal();
        } finally {
            lock.unlock();
        }
    }
    
    public void choosePlayer1(Symbol symbol) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (symbol1 != null) {
                voll.await();
            }
            this.symbol1 = symbol;
            System.out.println("Spieler 1 w�hlt " + symbol);
            // signalisieren, alle haben gesetzt
            if (symbol2 != null) {
                nichtVoll.signal();
            }
            // signalisieren, anderer Spieler kann setzen
            else {
                voll.signal();
            }
            
        } finally {
            lock.unlock();
        }
    }
    
    public void choosePlayer2(Symbol symbol) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (symbol2 != null) {
                voll.await();
            }
            this.symbol2 = symbol;
            System.out.println("Spieler 2 w�hlt " + symbol);
            if (symbol1 != null) {
                nichtVoll.signal();
            } else {
                voll.signal();
            }
        } finally {
            lock.unlock();
        }
    }
    
    public String printErgebnis() {
        return ("Unentschieden: " + ergebnis.get(0) + "\n" + player1 + ": " + ergebnis.get(1) + "\n"
            + player2 + ": "
            + ergebnis.get(2));
    }
}
