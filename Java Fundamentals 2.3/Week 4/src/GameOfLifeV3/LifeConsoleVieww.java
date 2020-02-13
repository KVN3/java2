package GameOfLifeV3;

import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("Duplicates")
public class LifeConsoleVieww implements Observer
{
    private LifeModell model;

    public LifeConsoleVieww(LifeModell model)
    {
        this.model = model;
        model.addObserver(this);
    }

    private void toonCel(int rij, int kolom)
    {
        if (model.isLevend(rij, kolom))
        {
            System.out.print("X");
        } else
        {
            System.out.print(".");
        }
    }

    private void refresh()
    {
        for (int rij = 0; rij < 20; rij++) {
            System.out.printf("%2d", (rij+1));
            for (int kolom = 0; kolom < 30; kolom++) {
                toonCel(rij, kolom);
            }
            System.out.println();
        }
    }

    @Override
    public void update(Observable LifeModel, Object info)
    {
        refresh();
        System.out.println("\n");
    }
}
