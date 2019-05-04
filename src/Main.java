import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int groesse, anfBel, intZyk;
        boolean zyklisch = false;

        System.out.print("Geben Sie bitte zunaechst die Groesse des Spielfelds ein.\n"
                + "Akzeptiert werden auch hier ausschliesslich Integer von 1 bis einschliesslich 100: ");

        do {

            // Nutzereingabe: Pruefung, ob Eingabe vom Typ int ist
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.printf("\"%s\" ist kein Integer. Bitte korrigieren Sie Ihre Eingabe: ", input);
            }
            groesse = scanner.nextInt();

            // Nutzereingabe: Pruefung, ob Eingabe groesser als 0 ist
            if (groesse < 1) {
                String input = Integer.toString(groesse);
                System.out.printf("\"%s\" ist nicht groesser als 0. Bitte korrigieren Sie Ihre Eingabe: ", input);

            }
        } while (groesse < 1);

        System.out.print("Geben Sie bitte als naechstes die prozentuale Belegung des Spielfelds ein.\n"
                + "Akzeptiert werden auch hier ausschliesslich Integer von 1 bis einschliesslich 100: ");

        do {
            // Nutzereingabe: Pruefung, ob Eingabe vom Typ int ist
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.printf("\"%s\" ist kein Integer. Bitte korrigieren Sie Ihre Eingabe: ", input);
            }
            anfBel = scanner.nextInt();

            // Nutzereingabe: Pruefung, ob Eingabe groesser als 0 oder groesser als 100 ist
            if (anfBel < 1 || anfBel > 100) {
                String input = Integer.toString(anfBel);
                System.out.printf("\"%s\" ist nicht groesser als 0. Bitte korrigieren Sie Ihre Eingabe: ", input);

            }

            if (anfBel > 100) {
                String input = Integer.toString(anfBel);
                System.out.printf("\"%s\" ist groesser als 100. Bitte korrigieren Sie Ihre Eingabe: ", input);
            }

        } while (anfBel < 1 || anfBel > 100);

        System.out.print("Geben Sie bitte als naechstes ein, ob das Spielfeld offen (1) oder zyklisch (0) sein soll.\n"
                + "Akzeptiert werden auch hier ausschliesslich Integer 0 und 1: ");

        do {
            // Nutzereingabe: Pruefung, ob Eingabe vom Typ int ist
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.printf("\"%s\" ist kein Integer. Bitte korrigieren Sie Ihre Eingabe: ", input);
            }
            intZyk = scanner.nextInt();

            // Nutzereingabe: Pruefung, ob Eingabe groesser als 0 oder groesser als 100 ist
            if (intZyk < 0) {
                String input = Integer.toString(anfBel);
                System.out.printf("\"%s\" ist nicht groesser als 0. Bitte korrigieren Sie Ihre Eingabe: ", input);

            }

            if (intZyk > 1) {
                String input = Integer.toString(anfBel);
                System.out.printf("\"%s\" ist groesser als 1. Bitte korrigieren Sie Ihre Eingabe: ", input);
            }

            if (intZyk == 0) {
                zyklisch = true;
            }

            if (intZyk == 1) {
                zyklisch = false;
            }

        } while (intZyk < 0 || intZyk > 1);

        Spiel spiel = new Spiel(groesse, anfBel, zyklisch);
        spiel.belegen();
        spiel.spielzug();
    }
}
