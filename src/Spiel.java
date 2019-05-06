import java.util.*;
import java.lang.*;

/**
 * Klasse zur Beschreibung von John Conway's Game of Life.
 * Hier wird das Spielfeld initialisiert, die Anfangsbelegung angewand und Spielzuege durchgefuehrt.
 * Ausserdem kann das Spiel durch den Nutzer beendet werden.
 */
public class Spiel {

    /**
     * Eine ArrayList von einer Array List.
     * Diese bildet in Form einer zweidimensionalen Matrix das Feld ab und enthaelt die Zellen.
     */
    private ArrayList<ArrayList<Zelle>> feld;

    /**
     * Die Groesse des Spielfelds.
     * Gemeint ist damit die Seitenlaenge.
     * Das Spielfeld hat die Form eines Quadrats.
     */
    private int groesse;

    /**
     * Die Menge an Zellen, die im Rahmen der Startbelebung auf das Feld gesetzt wird.
     */
    private int startCells;

    /**
     * Festlegung, ob das Spielfeld offen oder zyklisch ist.
     * Offen bedeutet, dass das Spielfeld Raender hat. Hinter diesen endet das Spielfeld.
     * Zyklisch bedeutet, dass das Spielfeld am linken Rand rechts wieder weiter geht.
     * Gleiches gilt fuer oben und unten.
     */
    private boolean zyklisch;

    /**
     * Konstruktor fuer ein Spiel.
     *
     * @param groesse    setzt die Groesse (Seitenlaenge) des Spielfelds.
     * @param startCells setzt die Menge an Anfangszellen auf dem Spielfeld.
     * @param zyklisch   bestimmt, ob das Feld zyklisch (true) oder offen (false; also mit Raendern) ist.
     */
    public Spiel(int groesse, int startCells, boolean zyklisch) {
        feld = new ArrayList<ArrayList<Zelle>>();

        this.groesse = groesse;
        this.startCells = startCells;
        this.zyklisch = zyklisch;
    }


    /**
     * Erschafft das Spielfeld.
     * Dabei nutzt es die Groesse (Seitenlaenge), um so eine zweidimensionale Matrix aus ArrayLists zu erstellen.
     * Jedes Feld enthaelt eine Zelle.
     * Alle Zellen werden in Bezug auf ihre Belegung zunaechst auf false gesetzt.
     */
    public void belegen() {
        for (int i = 0; i < groesse; i++) {
            ArrayList<Zelle> zellenliste = new ArrayList<Zelle>();
            feld.add(zellenliste);
            for (int j = 0; j < groesse; j++) {
                zellenliste.add(new Zelle(i, j, false));
            }
        }
    }


    /**
     * Verteilt die Anfangszellen auf dem Spielfeld.
     * Zufaellig ausgewaehlte Zellen auf dem Spielfeld werden in Bezug auf ihre Belegung auf true gesetzt.
     */
    public void setup() {
        for (int i = 0; i < startCells; i++) { //startCells ergibt sich aus anfBel und der groesse des Spielfelds
            int x = (int) (Math.random() * groesse); // zufaellige x-Koordinate
            int y = (int) (Math.random() * groesse); // zufaellige y-Koordinate

            if (!getBelegung(x, y)) {
                setBelegung(x, y, true); // Wenn eine tote Zelle gefunden wurde, wird diese belebt

            } else if (getBelegung(x, y)) {
                i--; // Wenn eine bereits lebende Zelle gefunden wurde, muss an ihrer statt eine andere Zelle belebt werden
            }
        }

        /* Bestimmte Muster einstellen?
           Klassissche GOL-Muster funktionieren nicht, da Regeln fuer Wiederbelebung im OOP abweichen.
           Klassisch: Genau drei Nachbarn fuer Wiederbelebung
           OOP: Mindestens drei Nachbarn fuer Wiederbelebung
         */

        /*
        System.out.println("Startbelegung:");
        for (int i = 0; i < groesse; i++) {
            for (int j = 0; j < groesse; j++) {

                System.out.println(i + "," + j + ": " + this.getBelegung(i, j)); // Druckt Anfagsbelegung aller Zellen als "Text"
            }
        }
        */

        System.out.println("Startbelegung:"); // Druckt Anfangsbelegung des Spielfelds
        for (int i = 0; i < groesse; i++) {
            for (int j = 0; j < groesse; j++) {

                if (getBelegung(i, j)) {
                    System.out.print("X ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println("");
        }
    }


    public boolean getBelegung(int x, int y) {
        return feld.get(x).get(y).getBelegt();
    }

    public boolean getBelegungNeu(int x, int y) {
        return feld.get(x).get(y).getBelegtNeu();
    }

    public void setBelegung(int x, int y, boolean belegt) {
        feld.get(x).get(y).setBelegt(belegt);
    }

    public void setBelegungNeu(int x, int y, boolean belegt) {
        feld.get(x).get(y).setBelegtNeu(belegt);
    }


    /**
     * Fuehrt einen Spielzug durch.
     * Dabei wird gezaehlt, in welcher Runde sich das Spiel befindet.
     * Fuer jede Zelle wird die Anzahl ihrer lebendigen Nachbarn ermittelt.
     * Den Regeln des OOP-GOL entsprechend wird sie dann entweder weiterleben, weiterhin tot sein, sterben oder wiederbelebt werden.
     * Wenn die Runde mit einem Spielfeld endet, das nur tote Zellen enthaelt endet das Spiel automatisch.
     * Wenn noch Zellen leben, hat der Nutzer die Wahl, das Spiel zu beenden oder weiterzuspielen.
     */
    public void spielzug() {

        int runde = 1;
        boolean weiterspielen = true;

        while (weiterspielen) {
            for (int i = 0; i < groesse; i++) {
                for (int j = 0; j < groesse; j++) {

                    int count = 0;

                    if (zyklisch) { // Anzahl an Nachbarn einer jeden Zelle wird gemaess den Regeln fuer ein zyklisches Spielfeld ermittelt

                        if (getBelegung((i + (groesse - 1)) % groesse, (j + (groesse - 1)) % groesse) == true) {  // oben links
                            count += 1;
                        }
                        if (getBelegung(i, (j + (groesse - 1)) % groesse) == true) { // mitte links
                            count += 1;
                        }
                        if (getBelegung((i + 1) % groesse, (j + (groesse - 1)) % groesse) == true) { // unten links
                            count += 1;
                        }
                        if (getBelegung((i + (groesse - 1)) % groesse, j) == true) { // mitte oben
                            count += 1;
                        }
                        if (getBelegung((i + 1) % groesse, j) == true) { // mitte unten
                            count += 1;
                        }
                        if (getBelegung((i + (groesse - 1)) % groesse, (j + 1) % groesse) == true) { // oben rechts
                            count += 1;
                        }
                        if (getBelegung(i, (j + 1) % groesse) == true) { // mitte rechts
                            count += 1;
                        }
                        if (getBelegung((i + 1) % groesse, (j + 1) % groesse) == true) { // unten rechts
                            count += 1;
                        }

                    } else if (!zyklisch) { // Anzahl an Nachbarn einer jeden Zelle wird gemaess den Regeln fuer ein offenes Spielfeld (mit Raendern) ermittelt

                        if (j > 0) {
                            if (i > 0) {
                                if (getBelegung(i - 1, j - 1) == true) { // oben links
                                    count += 1;
                                }
                            }
                            if (getBelegung(i, j - 1) == true) { // mitte links
                                count += 1;
                            }
                            if (i < groesse - 1) {
                                if (getBelegung(i + 1, j - 1) == true) { // unten links
                                    count += 1;
                                }
                            }
                        }

                        if (i > 0) {
                            if (getBelegung(i - 1, j) == true) { // mitte oben
                                count += 1;
                            }
                            if (j < groesse - 1) {
                                if (getBelegung(i - 1, j + 1) == true) { // oben rechts
                                    count += 1;
                                }
                            }
                        }
                        if (i < groesse - 1) {
                            if (getBelegung(i + 1, j) == true) { // mitte unten
                                count += 1;
                            }
                        }

                        if (j < groesse - 1) {
                            if (getBelegung(i, j + 1) == true) { // mitte rechts
                                count += 1;
                            }
                            if (i < groesse - 1) {
                                if (getBelegung(i + 1, j + 1) == true) { // unten rechts
                                    count += 1;
                                }
                            }
                        }
                    }

                    // Spielzug ist am Ende


                    if (getBelegung(i, j)) { // Falls Zelle lebt
                        if (count < 2 || count > 3) { // Bei weniger als zwei oder mehr als drei Nachbarn, stirbt Zelle
                            setBelegungNeu(i, j, false);

                            // System.out.println("Feld " + i + "," + j + " auf 0 gesetzt bei count " + count);
                        } else {
                            setBelegungNeu(i, j, getBelegung(i, j)); // Falls genau zwei oder drei Nachbarn, lebt Zelle weiter
                            // System.out.println("Feld " + i + "," + j + " unverändert bei count " + count);
                        }
                    } else if (count > 2) { // Falls Zelle tot und mehr als zwei Nachbarn, wird Zelle wiederbelebt
                        setBelegungNeu(i, j, true);
                        // System.out.println("Feld " + i + "," + j + " auf 1 gesetzt bei count " + count);
                    } else { // Falls Zelle tot und weniger als drei Nachbarn, bleibt Zelle tot
                        setBelegungNeu(i, j, getBelegung(i, j));
                        // System.out.println("Feld " + i + "," + j + " unverändert bei count " + count);
                    }


                }
            }


            // Spielfeld ausdrucken
            System.out.println("Neuer Stand nach Runde " + runde + ":");
            for (int i = 0; i < groesse; i++) {
                for (int j = 0; j < groesse; j++) {

                    setBelegung(i, j, getBelegungNeu(i, j));

                    if (getBelegung(i, j)) {
                        System.out.print("X ");
                    } else {
                        System.out.print("- ");
                    }
                }
                System.out.println("");
            }

            // Spielende, weil alle Zellen tot
            int k = 0;
            for (int i = 0; i < groesse; i++) {
                for (int j = 0; j < groesse; j++) {

                    if (getBelegung(i, j))
                        k++;
                }
            }

            if (k == 0) {
                System.out.println("Alle Zellen sind tot. Das Spiel ist zu Ende.");
                break;
            }

            // Nutzerabfrage, ob weitergespielt werden soll
            Scanner scanner = new Scanner(System.in);
            System.out.print("Weiterspielen? Drücken Sie 'j' :");
            if (scanner.next().equals("j")) {

                runde++;

            } else {
                weiterspielen = false;
                System.out.println("Spiel wurde vom Nutzer beendet.");
            }
        }
    }
}
