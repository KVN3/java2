package EventHandlingOpdrachten;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DubbelKlikPaneel extends JPanel implements MouseListener
{
    private String s = "";

    public DubbelKlikPaneel()
    {
        add(new Label("Klik linkerknop, middenknop, rechterknop of dubbel"));
        addMouseListener(this);
    }

    public void
    paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawString(s, 50, 50);
    }

    public void mouseClicked(MouseEvent e)
    {
//        if (e.isMetaDown())
//            s = "Rechts Klik ";
//        else if (e.isAltDown())
//            s = "Midden Klik ";
//        else if (e.getClickCount() == 2)
//            s = "Dubbel Klik ";
//        else
//            s = "Links Klik ";
//        repaint();
    }

    public void mousePressed(MouseEvent event)
    {
        switch (event.getButton())
        {
            case MouseEvent.BUTTON1:
                if(event.getClickCount() == 2)
                    s = "Links dubbel";
                else s = "Links";
                break;
            case MouseEvent.BUTTON2:
                if(event.getClickCount() == 2)
                    s = "Midden dubbel";
                else s = "Midden";
                break;
            case MouseEvent.BUTTON3:
                if(event.getClickCount() == 2)
                    s = "Rechts dubbel";
                else s = "Rechts";
                break;
        }
        repaint();
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
}
