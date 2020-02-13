package gameoflifev2;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("Duplicates")
public class LifePanelView extends JPanel implements Observer
{
    private LifeModell model;

    private JButton[][] buttonsGrid;
    private JButton button;

    public LifePanelView(LifeModell model)
    {
        this.model = model;
        model.addObserver(this);

        setLayout(new GridLayout(19, 29));

        initializeButtonsGrid();
    }

    private void initializeButtonsGrid()
    {
        buttonsGrid = new JButton[20][30];

        for (int rij = 0; rij < 19; rij++)
        {
            add(new JButton("" + (rij + 1)));
            for (int kolom = 0; kolom < 29; kolom++)
            {
                JButton cel = new JButton(".");
                cel.addActionListener(new CelController(model, rij, kolom));
                buttonsGrid[rij][kolom] = cel;
                add(cel);
            }
        }
    }

    private void refreshCel(int rij, int kolom)
    {
        if (model.isLevend(rij, kolom))
        {
            buttonsGrid[rij][kolom].setText("X");
        } else
        {
            buttonsGrid[rij][kolom].setText(".");
        }
    }

    private void refresh()
    {
        for (int rij = 0; rij < 19; rij++)
        {
            for (int kolom = 0; kolom < 29; kolom++)
            {
                refreshCel(rij, kolom);
            }
        }
    }

    @Override
    public void update(Observable LifeModel, Object info)
    {
        if (info != null)
        {
            CelPosition celPosition = (CelPosition) info;
            refreshCel(celPosition.rij, celPosition.kolom);
        } else
        {
            refresh();
        }
    }
}
