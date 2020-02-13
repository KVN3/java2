package EventHandlingOpdrachten;

import
        javax.swing.*;
import
        java.awt.*;
import
        java.awt.event.*;

public class TekstPaneel extends JPanel implements KeyListener
{
    private String s = "";
    private int x = 10, y = 50;
    private Color color = Color.BLACK;

    public TekstPaneel()
    {
        addKeyListener(this);
        setFocusable(true);
        requestFocus();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(color);
        g.drawString("Tik een regel tekst in", x, 20);
        g.drawString(s, x, y);
    }

    public void keyTyped(KeyEvent e)
    {
        s += e.getKeyChar();
        repaint();
    }

    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        switch (keyCode)
        {
            case KeyEvent.VK_DOWN:
                y++;
                break;
            case KeyEvent.VK_UP:
                y--;
                break;
            case KeyEvent.VK_RIGHT:
                x++;
                break;
            case KeyEvent.VK_LEFT:
                x--;
                break;
            case KeyEvent.VK_F1:
                color = Color.RED;
                break;
            case KeyEvent.VK_F2:
                color = Color.BLUE;
                break;
            case KeyEvent.VK_F3:
                color = Color.GRAY;
                break;
            case KeyEvent.VK_F4:
                color = color.GREEN;
                break;

        }
        repaint();
    }

    public void keyReleased(KeyEvent e)
    {
    }
}
