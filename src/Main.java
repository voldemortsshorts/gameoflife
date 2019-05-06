import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int groesse, anfBel, startCells, intZyk;
        boolean zyklisch = false; // Muss anscheinend mit einem Wert initialisiert werden. Dieser Wert wird aber auf jeden Fall ueberschrieben.


        System.out.print("Geben Sie bitte zun\u00E4chst die Gr\u00F6\u00DFe des Spielfelds ein.\n"
                + "Akzeptiert werden auch hier ausschlie\u00DFlich Integer von 1 bis einschlie\u00DFlich 100: ");

        do {
            // Nutzereingabe: Pruefung, ob Eingabe vom Typ int ist
            while (!sc.hasNextInt()) {
                String input = sc.next();
                System.out.printf("\"%s\" ist kein Integer. Bitte korrigieren Sie Ihre Eingabe: ", input);
            }

            groesse = sc.nextInt();

            // Nutzereingabe: Pruefung, ob Eingabe groesser als 0 ist
            if (groesse < 1) {
                String input = Integer.toString(groesse);
                System.out.printf("\"%s\" ist nicht gr\u00F6\u00DFer als 0. Bitte korrigieren Sie Ihre Eingabe: ", input);

            } else if (groesse > 100) {
                String input = Integer.toString(groesse);
                System.out.printf("\"%s\" ist gr\u00F6\u00DFer als 100. Bitte korrigieren Sie Ihre Eingabe: ", input);
            }
        } while (groesse < 1 || groesse > 100);


        System.out.print("Geben Sie bitte als n\u00E4chstes die prozentuale Belegung des Spielfelds ein.\n"
                + "Akzeptiert werden auch hier ausschlie\u00DFlich Integer von 1 bis einschlie\u00DFlich 100: ");

        do {
            // Nutzereingabe: Pruefung, ob Eingabe vom Typ int ist
            while (!sc.hasNextInt()) {
                String input = sc.next();
                System.out.printf("\"%s\" ist kein Integer. Bitte korrigieren Sie Ihre Eingabe: ", input);
            }

            anfBel = sc.nextInt();

            // Nutzereingabe: Pruefung, ob Eingabe groesser als 0 oder groesser als 100 ist
            if (anfBel < 1) {
                String input = Integer.toString(anfBel);
                System.out.printf("\"%s\" ist nicht gr\u00F6\u00DFer als 0. Bitte korrigieren Sie Ihre Eingabe: ", input);

            } else if (anfBel > 100) {
                String input = Integer.toString(anfBel);
                System.out.printf("\"%s\" ist gr\u00F6\u00DFer als 100. Bitte korrigieren Sie Ihre Eingabe: ", input);
            }

        } while (anfBel < 1 || anfBel > 100);

        startCells = (anfBel * (groesse * groesse)) / 100;


        System.out.print("Geben Sie bitte als n\u00E4chstes ein, ob das Spielfeld zyklisch (1) oder offen (0) sein soll.\n"
                + "Akzeptiert werden auch hier ausschlie\u00DFlich die Integer 0 oder 1: ");

        do {
            // Nutzereingabe: Pruefung, ob Eingabe vom Typ int ist
            while (!sc.hasNextInt()) {
                String input = sc.next();
                System.out.printf("\"%s\" ist kein Integer. Bitte korrigieren Sie Ihre Eingabe: ", input);
            }

            intZyk = sc.nextInt();

            // Nutzereingabe: Pruefung, ob Eingabe kleiner als 0 ist
            if (intZyk < 0) {
                String input = Integer.toString(intZyk);
                System.out.printf("\"%s\" ist kleiner als 0. Bitte korrigieren Sie Ihre Eingabe: ", input);

            } else if (intZyk > 1) {
                String input = Integer.toString(intZyk);
                System.out.printf("\"%s\" ist gr\u00F6sser als 1. Bitte korrigieren Sie Ihre Eingabe: ", input);

            } else if (intZyk == 1) {
                zyklisch = true;

            } else if (intZyk == 0) {
                zyklisch = false;
            }

        } while (intZyk < 0 || intZyk > 1);


        Spiel spiel = new Spiel(groesse, startCells, zyklisch);
        spiel.belegen();
        spiel.setup();
        spiel.spielzug();

    }
}
