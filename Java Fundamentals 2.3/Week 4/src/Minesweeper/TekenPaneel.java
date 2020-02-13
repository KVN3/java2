package Minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observer;

public class TekenPaneel extends JPanel implements MouseListener
{
    private MineModel model;
    private int boxSize;
    private boolean rechterMouseButton = false;

    private ArrayList<VakjeCoords> vakjeCoords = new ArrayList<>();

    public TekenPaneel(MineModel model, int boxSize)
    {
        this.boxSize = boxSize;
        this.model = model;
        addMouseListener(this);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Font comicFont = new Font("Comic Sans MS", Font.PLAIN, 24);
        g.setFont(comicFont);

        g.setColor(Color.RED);
        drawGrid(g);
    }

    public void drawGrid(Graphics g)
    {
        for (int rij = 0; rij < model.getGridRows(); rij++)
        {
            drawLine(g, rij);
            for (int kolom = 0; kolom < model.getGridColumns(); kolom++)
            {
                Vakje vakje = model.getVakje(rij, kolom);
                drawLine(g, kolom);
                saveCoords(vakje, rij, kolom);

                if (rechterMouseButton)
                {
                    markeerVakje(g, vakje);
                    rechterMouseButton = false;
                }

                else{
                    if (vakje.isRevealed())
                    {
                        revealVakje(g, vakje);
                    }
                }
            }
        }
    }

    private void markeerVakje(Graphics g, Vakje vakje)
    {
        int x = 15 + (vakje.kolom * boxSize);
        int y = 30 + (vakje.rij * boxSize);

        g.drawString("M", x, y);
    }

    private void revealVakje(Graphics g, Vakje vakje)
    {
        if (vakje.isBom())
        {
            g.drawString("GAME OVER!", 200, 200);
        } else
        {
            String bomNummer = String.valueOf(model.getNeighbouringBombs(vakje.rij, vakje.kolom));

            int x = 15 + (vakje.kolom * boxSize);
            int y = 30 + (vakje.rij * boxSize);

            g.drawString(bomNummer, x, y);
        }
    }

    private void saveCoords(Vakje vakje, int rij, int kolom)
    {
        VakjeCoords vakjeCoord = new VakjeCoords();

        vakjeCoord.vakje = vakje;

        vakjeCoord.x1 = kolom * boxSize; // begin vak x
        vakjeCoord.x2 = kolom * boxSize + 40; // eind vak x

        vakjeCoord.y1 = rij * boxSize;
        vakjeCoord.y2 = rij * boxSize + 40;

        if (!vakjeCoords.contains(vakjeCoord))
            vakjeCoords.add(vakjeCoord);
    }


    private void drawLine(Graphics g, int punt)
    {
        g.drawLine(punt * boxSize, 0, punt * boxSize, 400); // verticaal
        g.drawLine(0, punt * boxSize, 400, punt * boxSize); // horizontal
    }

    public void mousePressed(MouseEvent e)
    {
    }

    public void mouseClicked(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();

        int rij = 10;
        int kolom = 10;

        for (VakjeCoords vakje : vakjeCoords)
        {
            if ((vakje.x1 < x && x < vakje.x2) && (vakje.y1 < y && y < vakje.y2))
            {
                kolom = vakje.vakje.kolom;
                rij = vakje.vakje.rij;
            }
        }

        if (e.isMetaDown())
        {
            rechterMouseButton = true;
        } else
        {
            model.openVakje(rij, kolom);
            rechterMouseButton = false;
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

    private class VakjeCoords
    {
        private Vakje vakje;
        private int x1, y1, x2, y2;
    }
}


