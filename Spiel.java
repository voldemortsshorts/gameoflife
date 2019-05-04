import java.util.*;
import java.lang.*;

public class Spiel {

private ArrayList<ArrayList<Zelle>> feld;
private int groesse;
private int anfBel;
private boolean zyklisch;



public Spiel (int groesse, int anfBel, boolean zyklisch) {
  feld = new ArrayList<ArrayList<Zelle>>();

  this.groesse = groesse;
  this.anfBel = anfBel;
  this.zyklisch = zyklisch;
}

//ANfang belegen
public void belegen() {//FIXME;
for (int i = 0; i < groesse; i++){
  ArrayList<Zelle> zellenliste = new ArrayList<Zelle>();
  feld.add(zellenliste);
   for (int j = 0; j < groesse; j++) {
     zellenliste.add(new Zelle(i,j,false));



    }
  }
/*for (ArrayList<Zelle> zeile : belegung){
  for (Zelle zelle : zeile){
    System.out.println(zelle);
  }
}*/

public void setup() {
  for (int i = 0; i < startCells; i++){ //startCells kommt von oben
    int x = r(0, groesse); //random muss noch implementiert werden
    int y = r(0, groesse);

    setBelegung(x, y, true);
  }
}

//Muster?

System.out.println("Startbelegung:");
for (int i = 0; i < groesse; i++){
  for (int j = 0; j < groesse; j++) {

  System.out.println(i + "," + j + ": " + this.getBelegung(i,j));


}}
}

//Ende belegen

public int getBelegung(int x, int y){
  return feld.get(x).get(y).getBelegt();
}

public int getBelegungNeu(int x, int y){
  return feld.get(x).get(y).getBelegtNeu();
}

public void setBelegung(int x, int y, boolean belegt){
  feld.get(x).get(y).setBelegt(belegt);
}

public void setBelegungNeu(int x, int y, boolean belegt){
  feld.get(x).get(y).setBelegtNeu(belegt);
}


//Spielzug

public void spielzug(){
  int runde = 1;
  boolean weiterspielen = true;
while (weiterspielen){
  for (int i = 0; i < groesse; i++){
    for (int j = 0; j < groesse; j++) {
      int count = 0;
if (zyklisch){ //logische Ausdrücke kürzen!!!!
      if (getBelegung((i+(groesse-1))%groesse, (j+(groesse-1))%groesse) == 1){count += 1;}
        if (getBelegung(i, (j+(groesse-1))%groesse) == 1){count += 1;}
          if (getBelegung((i+1)%groesse, (j+(groesse-1))%groesse) == 1){count += 1;}
            if (getBelegung((i+(groesse-1))%groesse, j) == 1){count += 1;}
              if (getBelegung((i+1)%groesse, j) == 1){count += 1;}
                if (getBelegung((i+(groesse-1))%groesse, (j+1)%groesse) == 1){count += 1;}
                  if (getBelegung(i, (j+1)%groesse) == 1){count += 1;}
                    if (getBelegung((i+1)%groesse, (j+1)%groesse) == 1){count += 1;}}

else if (!zyklisch){//logische Ausdrücke kürzen
  if (j>0){
  if (i>0) {if (getBelegung(i-1, j-1) == 1){count += 1;}}
    if (getBelegung(i, j-1) == 1){count += 1;}
    if (i<groesse-1) { if (getBelegung(i+1, j-1) == 1){count += 1;}}}

        if(i>0) {
          if (getBelegung(i-1, j) == 1){count += 1;}
        if(j<groesse-1) { if (getBelegung(i-1, j+1) == 1){count += 1;}}}
        if(i<groesse-1) { if (getBelegung(i+1, j) == 1){count += 1;}}

          if (j<groesse-1) {   if (getBelegung(i, j+1) == 1){count += 1;}
            if(i<groesse-1)  {  if (getBelegung(i+1, j+1) == 1){count += 1;}}}
}



    if (getBelegung(i,j)){
      if (count<2 || count >3) {
        setBelegungNeu(i,j,false);

    System.out.println("Feld " + i + "," + j + " auf 0 gesetzt bei count " + count);}
      else {
        setBelegungNeu(i, j, getBelegung(i,j));
        System.out.println("Feld " + i + "," + j + " unverändert bei count " + count);
      } } else if  (count >2) {
      setBelegungNeu(i,j,true);
      System.out.println("Feld " + i + "," + j + " auf 1 gesetzt bei count " + count);
    } else {
      setBelegungNeu(i, j, getBelegung(i,j));
      System.out.println("Feld " + i + "," + j + " unverändert bei count " + count);
    }

    }}

//Spielzug Ende

//Spielfeld ausdrucken
System.out.println("Neuer Stand Runde " + runde);
for (int i = 0; i < groesse; i++){
  for (int j = 0; j < groesse; j++) {
setBelegung(i, j, getBelegungNeu(i,j));

//Spielende weil alle tot??

//System.out.println(i + "," + j + ": " + getBelegung(i,j));
if (getBelegung(i,j)) {System.out.print("X ");} else {System.out.print("- ");}
} System.out.println("");
}

//Abfrage
 Scanner scanner = new Scanner(System.in);
System.out.println("Weiterspielen? Drücken Sie 'j' ");
if(scanner.next().equals("j")){

  runde +=1;


} else {weiterspielen = false;
  System.out.println("Spiel Ende");}

}
//Funktion Ende
}



}
