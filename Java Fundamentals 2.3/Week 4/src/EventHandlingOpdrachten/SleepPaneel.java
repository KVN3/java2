package EventHandlingOpdrachten;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SleepPaneel extends JPanel implements MouseListener, MouseMotionListener, KeyListener
{
    private int startX, startY, eindX, eindY;
    private boolean shiftDown = false;

    public SleepPaneel()
    {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(shiftDown)
            g.setColor(Color.RED);
        else
            g.setColor(Color.BLACK);
        g.drawLine(startX, startY, eindX, eindY);
    }

    public void mousePressed(MouseEvent e)
    {

    }

    public void mouseDragged(MouseEvent e)
    {
        if(e.isShiftDown()){
            shiftDown = true;
        }
        else{
            shiftDown = false;
        }
        eindX = e.getX();
        eindY = e.getY();
        repaint();
    }

    public void
    mouseClicked(MouseEvent e)
    {
    }

    public void
    mouseEntered(MouseEvent e)
    {
    }

    public void
    mouseExited(MouseEvent e)
    {
    }

    public void
    mouseReleased(MouseEvent e)
    {
    }

    public void
    mouseMoved(MouseEvent e)
    {
        startX = e.getX();
        startY = e.getY();
    }

    public void keyPressed(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}


}
