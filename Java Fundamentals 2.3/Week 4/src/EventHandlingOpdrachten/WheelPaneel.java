package EventHandlingOpdrachten;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WheelPaneel extends JPanel implements MouseWheelListener, MouseListener, MouseMotionListener
{
    private int grootte = 50;
    private int mouseX = 50, mouseY = 50;

    public WheelPaneel()
    {
        addMouseWheelListener(this);
        addMouseMotionListener(this);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.YELLOW);
        g.fillOval(mouseX, mouseY, grootte, grootte);
        g.setColor(Color.BLACK);
        g.drawString(grootte + "", mouseX, mouseY);

    }

    public void mouseMoved(MouseEvent e)
    {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public void mouseWheelMoved(MouseWheelEvent e)
    {
        grootte += e.getWheelRotation();
        repaint();
    }

    public void mouseClicked(MouseEvent e)
    {

    }

    public void mousePressed(MouseEvent event)
    {

    }

    public void mouseEntered(MouseEvent e)
    {
    }

    public void mouseExited(MouseEvent e)
    {
    }

    public void mouseReleased(MouseEvent e)
    {
    }

    public void mouseDragged(MouseEvent e)
    {

    }


}
