package Minesweeper;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ButtonView extends JPanel implements Observer
{
    private MineModel model;
    private JButton[][] buttonsGrid;

    public ButtonView(MineModel model){
        this.model = model;
        model.addObserver(this);
        setLayout(new GridLayout(model.getGridRows() + 1, model.getGridColumns() + 1));
        initializeButtonsGrid();
    }

    private void initializeButtonsGrid()
    {
        buttonsGrid = new JButton[model.getGridRows()][model.getGridColumns()];

        for (int rij = 0; rij < (model.getGridRows() + 1); rij++)
        {
            add(new JButton("" + (rij)));
            for (int kolom = 0; kolom < (model.getGridColumns() + 1); kolom++)
            {
                if (rij == 0 && kolom != 0)
                {
                    add(new JButton("" + (kolom)));
                }

                if (rij > 0 && kolom > 0){
                    JButton vakje = new JButton("");
                    vakje.addActionListener(new MineController(model, rij - 1, kolom - 1));
                    buttonsGrid[rij - 1][kolom - 1] = vakje;
                    add(vakje);
                }
            }
        }
    }

    private void toonVakje(int rij, int kolom)
    {
        Vakje vakje = model.getVakje(rij, kolom);

        if (vakje.isRevealed())
        {
            if (vakje.isBom())
                buttonsGrid[rij][kolom].setText("X");
            else
                buttonsGrid[rij][kolom].setText("" + model.getNeighbouringBombs(rij, kolom));
        }
    }


    private void refresh()
    {

        for (int rij = 0; rij < model.getGridRows(); rij++)
        {
            for (int kolom = 0; kolom < model.getGridColumns(); kolom++)
            {
                if(model.isGameOver())
                {
                    model.revealAll();
                    buttonsGrid[rij][kolom].setEnabled(false);
                    buttonsGrid[rij][kolom].setBackground(Color.RED);
                }

                else
                    toonVakje(rij, kolom);
            }
        }
    }

    @Override
    public void update(Observable model, Object info)
    {
        if (info != null)
        {
//            CelPosition celPosition = (CelPosition) info;
//            refreshCel(celPosition.rij, celPosition.kolom);
        } else
        {
            refresh();
        }
    }
}
