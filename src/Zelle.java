public class Zelle {
    private int x;
    private int y;
    private int belegt;
    private int belegtNeu;

    public Zelle(int x, int y, int belegt) {
        this.x = x;
        this.y = y;
        this.belegt = belegt;
    }

    public int getBelegt() {
        return belegt;
    }

    public int getBelegtNeu() {
        return belegtNeu;
    }

    public void setBelegt(int belegt) {
        this.belegt = belegt;
    }

    public void setBelegtNeu(int belegt) {
        this.belegtNeu = belegt;
    }

}
