package EventHandlingOpdrachten;

import
        javax.swing.*;
import
        java.awt.*;
import
        java.awt.event.*;

public class TekenPaneel extends JPanel implements MouseListener
{
    private int x = 50, y = 50;
    private boolean circleOff;
    private JLabel coordinateLabel;

    public TekenPaneel()
    {
        circleOff = true;
        add(new Label("Klik met de muis"));
        coordinateLabel = new JLabel();
        add(coordinateLabel);
        addMouseListener(this);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if(circleOff)
        {
            g.setColor(Color.red);
            g.fillOval(x - 10, y - 10, 20, 20);
        }
    }

    public void mousePressed(MouseEvent e)
    {
        x = e.getX();
        y = e.getY();
        circleOff = true;
        repaint();
    }

    public void mouseClicked(MouseEvent e) {

    }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseReleased(MouseEvent e)
    {
        coordinateLabel.setText("X: " + e.getX() + " Y: " + e.getY());
        circleOff = false;
        repaint();
    }
}

