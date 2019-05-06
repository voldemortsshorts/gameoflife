/**
 * Klasse zur Beschreibung einer Zelle. Eine Zelle speichert ihre Position (x- und y-Koordinate im Spielfeld) und ihre Belegung.
 * Die Belegung kann geaendert werden (belegt oder nicht belegt).
 */

public class Zelle {

    /**
     * Die x-Koordinate der Zelle im Spielfeld.
     * Ist diese gesetzt, kann sie nicht mehr veraendert werden.
     */
    private int x;

    /**
     * Die y-Koordinate der Zelle im Spielfeld.
     * Ist diese gesetzt, kann sie nicht mehr veraendert werden.
     */
    private int y;

    /**
     * Die aktuelle Belegung der Zelle.
     * Ist der Wert true, so ist die Zelle gerade belegt (lebendig).
     * Ist der Wert false, so ist die Zelle gerade nicht belegt (tot).
     */
    private boolean belegt;

    /**
     * Die Belegung der Zelle fuer die naechste Runde.
     * Ist der Wert true, so wird die Zelle in der naechsten Runde belegt sein (lebt weiter oder wird wiederbelebt).
     * Ist der Wert false, so wird die Zelle in der naechsten Runde nicht belegt sein (ist bereits tot oder stirbt).
     */
    private boolean belegtNeu;

    /**
     * Konstruktor fuer eine Zelle
     * @param x setzt die x-Koordinate der Zelle im Spielfeld.
     * @param y setzt die y-Koordinate der Zelle im Spielfeld.
     * @param belegt setzt die aktuelle Belegung der Zelle.
     */
    public Zelle(int x, int y, boolean belegt) {
        this.x = x;
        this.y = y;
        this.belegt = belegt;
    }


    public boolean getBelegt() {
        return belegt;
    }

    public boolean getBelegtNeu() {
        return belegtNeu;
    }

    public void setBelegt(boolean belegt) {
        this.belegt = belegt;
    }

    public void setBelegtNeu(boolean belegt) {
        this.belegtNeu = belegt;
    }
}