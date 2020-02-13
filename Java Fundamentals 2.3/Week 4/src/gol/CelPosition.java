package gol;

public class CelPosition {
    private int rij;
    private int kolom;

    public int getRij() {
        return this.rij;
    }

    public void setRij(int rij) {
        this.rij = rij;
    }

    public int getKolom() {
        return this.kolom;
    }

    public void setKolom(int kolom) {
        this.kolom = kolom;
    }

    public CelPosition(int rij, int kolom) {
        this.rij = rij;
        this.kolom = kolom;
    }
}
