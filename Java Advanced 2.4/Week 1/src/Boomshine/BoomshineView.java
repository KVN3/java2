package Boomshine;

import Boomshine.Controllers.GroeiController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static Boomshine.BoomshineApp.PANEL_SIZE;

public class BoomshineView extends JPanel implements MouseListener
{
    ArrayList<Bubble> bubbles;
    private int xPos, yPos;
    private boolean clicked;

    public BoomshineView(ArrayList<Bubble> bubbles)
    {
        this.bubbles = bubbles;
        this.clicked = false;

        addMouseListener(this);

        setLayout(null);
        drawBubbles();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.setColor(Color.BLUE);
        g.fillRect(0, 0, PANEL_SIZE, PANEL_SIZE);

        if (clicked)
        {
            g.setColor(Color.ORANGE);
            Bubble bubble = new Bubble(xPos, yPos, 1, false);
            add(new BubbleView(bubble));

            Thread thread = new Thread(new GroeiController(bubble, bubbles));
            thread.start();

            clicked = false;
        }

    }

    private void drawBubbles()
    {
        for (Bubble bubble : bubbles)
        {
            add(new BubbleView(bubble));
        }
    }

    public void mouseClicked(MouseEvent e)
    {
        xPos = e.getX();
        yPos = e.getY();
        clicked = true;
        repaint();
    }

    public void mousePressed(MouseEvent event) { }

    public void mouseEntered(MouseEvent e) { }

    public void mouseExited(MouseEvent e) { }

    public void mouseReleased(MouseEvent e) {}
}
