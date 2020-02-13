package Boomshine;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import static Boomshine.BoomshineApp.PANEL_SIZE;

public class BubbleView extends JPanel implements Observer
{
    private Bubble bubble;

    public BubbleView(Bubble bubble)
    {
        this.bubble = bubble;
        this.bubble.addObserver(this);

        setOpaque(false);
        setBounds(0, 0, PANEL_SIZE, PANEL_SIZE);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.setColor(bubble.getKleur());
        g.fillOval(bubble.getMiddelpunt().x, bubble.getMiddelpunt().y, bubble.getStraal(), bubble.getStraal());
    }

    //public void refresh() { repaint(); }

    @Override
    public void update(Observable model, Object info) { repaint(); }

}
