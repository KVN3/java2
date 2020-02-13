package tekenapp;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class TekenPaneel extends JPanel implements Observer
{
    private TekenBalk balk;
    private TekenModel model;

    private Color color = Color.BLACK;

    public TekenPaneel(TekenModel model, TekenBalk balk)
    {
        this.model = model;
        this.balk = balk;

        model.addObserver(this);
        refresh();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.setColor(balk.getColor());

        paintPlu(g);
        g.drawString("( ͡° ͜ʖ ͡°)", 150, 100);
    }

    private void paintPlu(Graphics g)
    {
        g.fillArc(85, 70, 170, 100, -180, -180);
        g.drawLine(170, 50, 170, 180);
        g.drawArc(170, 160, 30, 40, 180, 180);
    }

    private void refresh()
    {
        repaint();
    }

    @Override
    public void update(Observable TekenModel, Object info)
    {
        refresh();
    }

}
