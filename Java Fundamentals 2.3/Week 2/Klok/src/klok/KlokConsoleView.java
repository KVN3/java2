package klok;

import klok.Models.KlokModel;

import java.util.*;

public class KlokConsoleView implements Observer
{
    private KlokModel klok;

    public KlokConsoleView(KlokModel klok)
    {
        this.klok = klok;

        klok.addObserver(this);
        refresh();
    }

    public KlokModel getKlok(){
        return this.klok;
    }

    private void refresh()
    {
        System.out.println(klok);
    }

    @Override
    public void update(Observable KlokModel, Object info)
    {
        refresh();
    }
}
