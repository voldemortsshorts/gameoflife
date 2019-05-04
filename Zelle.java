public class Zelle {
 private int x;
 private int y;
 private boolean belegt;
 private boolean belegtNeu;

public Zelle(int x, int y, boolean belegt){
  this.x = x;
  this.y = y;
  this.belegt = belegt;
}

public int getBelegt(){
  return belegt;
}

public int getBelegtNeu(){
  return belegtNeu;
}

public void setBelegt(boolean belegt){
  this.belegt = belegt;
}

public void setBelegtNeu(boolean belegt){
  this.belegtNeu = belegt;
}

}
