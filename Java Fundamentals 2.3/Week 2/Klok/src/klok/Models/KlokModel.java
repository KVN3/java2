package klok.Models;

import java.util.*;

public class KlokModel extends Observable
{
    protected int uur;
    protected int minuut;

    public KlokModel(int uur, int minuut)
    {
        this.uur = uur;
        this.minuut = minuut;
    }

    public void volgendeMinuut()
    {
        minuut++;

        if (minuut > 59)
        {
            minuut = 0;
            volgendUur();
        }

        setChanged();
        notifyObservers();
    }

    private void volgendUur() {

        uur++;

        if (uur > 23) {
            uur = 0;
        }
    }

    public String toString()
    {
        return String.format("KLOK: [%02d:%02d]", this.uur, this.minuut);
    }
}
