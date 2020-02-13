package Melkweg;

import Melkweg.shapes.StarPolygon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class TekenPaneel extends JPanel implements MouseListener
{
    private int xPos, yPos;

    Random rnd = new Random();

    public TekenPaneel()
    {
        setBackground(Color.BLUE);
        addMouseListener(this);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        drawRandomStar(g);

//       for(int i = 0; i < 100; i++)
//       {
//           xPos = rnd.nextInt(800);
//           yPos = rnd.nextInt(800);
//           drawRandomStar(g);
//       }

    }

    private void drawRandomStar(Graphics g)
    {
        g.setColor(new Color(255,255,rnd.nextInt(150)));

        int radius = rnd.nextInt(16) + 10, innerRadius = rnd.nextInt(30) + 40;
        int vertexCount = rnd.nextInt(5) + 6;
        int startAngle = rnd.nextInt(360);

        g.fillPolygon(new StarPolygon(xPos, yPos, radius, innerRadius, vertexCount, startAngle));
    }

    public void mouseClicked(MouseEvent e)
    {
        xPos = e.getX();
        yPos = e.getY();
        repaint();
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

}
