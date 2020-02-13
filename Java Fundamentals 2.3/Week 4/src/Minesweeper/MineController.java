package Minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MineController implements ActionListener
{
    private MineModel model;
    private int kolom, rij;

    public MineController(MineModel model, int rij, int kolom)
    {
        this.model = model;
        this.rij = rij;
        this.kolom = kolom;
    }

    public void actionPerformed(ActionEvent event){ model.openVakje(rij, kolom);}
}
