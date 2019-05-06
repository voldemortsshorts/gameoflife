import java.util.*;
import java.lang.*;

public class Spiel {

    private ArrayList<ArrayList<Zelle>> feld;
    private int groesse;
    private int startCells;
    private boolean gleiter;
    private boolean zyklisch;


    public Spiel(int groesse, int startCells, boolean zyklisch, boolean gleiter) {
        feld = new ArrayList<ArrayList<Zelle>>();

        this.groesse = groesse;
        this.startCells = startCells;
        this.zyklisch = zyklisch;
        this.gleiter = gleiter;
    }


    // Spielfeld erschaffen
    public void belegen() {
        for (int i = 0; i < groesse; i++) {
            ArrayList<Zelle> zellenliste = new ArrayList<Zelle>();
            feld.add(zellenliste);
            for (int j = 0; j < groesse; j++) {
                zellenliste.add(new Zelle(i, j, false));
            }
        }
    }


    // Anfangsbelegung erstellen
    public void setup() {
        if (!gleiter) {

            for (int i = 0; i < startCells; i++) { //startCells kommt von oben
                int x = (int) (Math.random() * groesse); //random muss noch implementiert werden
                int y = (int) (Math.random() * groesse);

                if (!getBelegung(x, y)) {
                    setBelegung(x, y, true);
                } else if (getBelegung(x, y)) {
                    i--;
                }
            }
        } else if (gleiter) {
            setBelegung(1, 2, true);
            setBelegung(2, 3, true);
            setBelegung(3, 1, true);
            setBelegung(3, 2, true);
            setBelegung(3, 3, true);

        }

        // Bestimmte Muster einstellen?!

        /*
        System.out.println("Startbelegung:");
        for (int i = 0; i < groesse; i++) {
            for (int j = 0; j < groesse; j++) {

                System.out.println(i + "," + j + ": " + this.getBelegung(i, j));
            }
        }
        */

        System.out.println("Startbelegung:");
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


    //Spielzug
    public void spielzug() {

        int runde = 1;
        boolean weiterspielen = true;

        while (weiterspielen) {
            for (int i = 0; i < groesse; i++) {
                for (int j = 0; j < groesse; j++) {

                    int count = 0;

                    if (zyklisch) { //logische Ausdrücke kürzen!!!!

                        if (getBelegung((i + (groesse - 1)) % groesse, (j + (groesse - 1)) % groesse) == true) {
                            count += 1;
                        }
                        if (getBelegung(i, (j + (groesse - 1)) % groesse) == true) {
                            count += 1;
                        }
                        if (getBelegung((i + 1) % groesse, (j + (groesse - 1)) % groesse) == true) {
                            count += 1;
                        }
                        if (getBelegung((i + (groesse - 1)) % groesse, j) == true) {
                            count += 1;
                        }
                        if (getBelegung((i + 1) % groesse, j) == true) {
                            count += 1;
                        }
                        if (getBelegung((i + (groesse - 1)) % groesse, (j + 1) % groesse) == true) {
                            count += 1;
                        }
                        if (getBelegung(i, (j + 1) % groesse) == true) {
                            count += 1;
                        }
                        if (getBelegung((i + 1) % groesse, (j + 1) % groesse) == true) {
                            count += 1;
                        }

                    } else if (!zyklisch) {//logische Ausdrücke kürzen

                        if (j > 0) {
                            if (i > 0) {
                                if (getBelegung(i - 1, j - 1) == true) {
                                    count += 1;
                                }
                            }
                            if (getBelegung(i, j - 1) == true) {
                                count += 1;
                            }
                            if (i < groesse - 1) {
                                if (getBelegung(i + 1, j - 1) == true) {
                                    count += 1;
                                }
                            }
                        }

                        if (i > 0) {
                            if (getBelegung(i - 1, j) == true) {
                                count += 1;
                            }
                            if (j < groesse - 1) {
                                if (getBelegung(i - 1, j + 1) == true) {
                                    count += 1;
                                }
                            }
                        }
                        if (i < groesse - 1) {
                            if (getBelegung(i + 1, j) == true) {
                                count += 1;
                            }
                        }

                        if (j < groesse - 1) {
                            if (getBelegung(i, j + 1) == true) {
                                count += 1;
                            }
                            if (i < groesse - 1) {
                                if (getBelegung(i + 1, j + 1) == true) {
                                    count += 1;
                                }
                            }
                        }
                    }

                    // Spielzug ist am Ende


                    if (getBelegung(i, j)) {
                        if (count < 2 || count > 3) {
                            setBelegungNeu(i, j, false);

                            // System.out.println("Feld " + i + "," + j + " auf 0 gesetzt bei count " + count);
                        } else {
                            setBelegungNeu(i, j, getBelegung(i, j));
                            // System.out.println("Feld " + i + "," + j + " unverändert bei count " + count);
                        }
                    } else if (count > 2) {
                        setBelegungNeu(i, j, true);
                        // System.out.println("Feld " + i + "," + j + " auf 1 gesetzt bei count " + count);
                    } else {
                        setBelegungNeu(i, j, getBelegung(i, j));
                        // System.out.println("Feld " + i + "," + j + " unverändert bei count " + count);
                    }


                }
            }


            //Spielfeld ausdrucken
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

            // Spielende, weil alle tot
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

            // Nutzerabfrage
            Scanner scanner = new Scanner(System.in);
            System.out.print("Weiterspielen? Drücken Sie 'j' ");
            if (scanner.next().equals("j")) {

                runde++;

            } else {
                weiterspielen = false;
                System.out.println("Spiel wurde vom Nutzer beendet.");
            }

        }
//Funktion Ende

    }
}
