package Minesweeper;

public class Vakje
{
    public int rij, kolom, aantalBuren;
    private boolean bom;

    public void setRevealed(boolean revealed)
    {
        this.revealed = revealed;
    }

    private boolean revealed;

    public Vakje(int rij, int kolom, boolean bom){
        this.rij = rij;
        this.kolom = kolom;
        this.bom = bom;

        revealed = false;
    }

    public boolean isBom()
    {
        return bom;
    }

    public boolean isRevealed() { return revealed; }
}
