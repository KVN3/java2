package GameOfLifeV3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CelController implements ActionListener
{
    private LifeModell model;
    private CelPosition celPosition;

    private int rij;
    private int kolom;

    public CelController(LifeModell model, int rij, int kolom)
    {
        this.model = model;
        celPosition = new CelPosition(rij, kolom);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        model.toggle(celPosition);
    }
}
