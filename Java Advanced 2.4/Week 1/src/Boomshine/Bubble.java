package Boomshine;

import java.awt.*;
import java.util.Observable;
import java.util.Random;

import static Boomshine.BoomshineApp.PANEL_SIZE;

public class Bubble extends Observable
{
    private Point middelpunt;
    private Color kleur;
    private int dx, dy, straal;

    public synchronized void setMoving(boolean moving)
    {
        this.moving = moving;

        setChanged();
        notifyObservers();
    }

    public boolean moving;
    private Random rnd = new Random();

    public Bubble(int spawnX, int spawnY, int straal, boolean moving)
    {
        this.straal = straal;
        this.moving = moving;
        this.middelpunt = new Point(spawnX, spawnY);
        this.kleur = new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));


        if (rnd.nextBoolean()) { dx = 1; } else { dx = -1; }
        if (rnd.nextBoolean()) { dy = 1; } else { dy = -1; }
    }

    public void move()
    {
        // X-axis
        if ((middelpunt.x + straal == PANEL_SIZE - 10) || (middelpunt.x == -5)) { dx *= -1; }

        // Y-axis
        if ((middelpunt.y + straal == PANEL_SIZE - 35) || (middelpunt.y == -5)) { dy *= -1; }

        middelpunt.translate(dx, dy);

        setChanged();
        notifyObservers();
    }

    public void groei()
    {
        straal += 1;

        // Keeps the middelpunt in the middle when growing
        if (straal % 2 == 0)
        {
            middelpunt.x--;
            middelpunt.y--;
        }


        setChanged();
        notifyObservers();
    }

    public boolean botstMet(Bubble b)
    {
        int afstX = b.middelpunt.x - this.middelpunt.x;
        int afstY = b.middelpunt.y - this.middelpunt.y;
        int stralenSom = b.straal + this.straal;

        return (afstX * afstX + afstY * afstY) < stralenSom * stralenSom;
    }

    public Point getMiddelpunt()
    {
        return middelpunt;
    }

    public int getStraal()
    {
        return straal;
    }

    public Color getKleur()
    {
        return kleur;
    }

}
