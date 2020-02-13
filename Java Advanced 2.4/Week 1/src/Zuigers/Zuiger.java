package Zuigers;

import java.util.Observable;
import java.util.Random;

public class Zuiger extends Observable
{
    public int getDeltaY()
    {
        return deltaY;
    }

    public int getHeight()
    {
        return height;
    }

    private int deltaY, height;

    public synchronized boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    private volatile boolean active = true;

    public Zuiger()
    {
        height = new Random().nextInt(381) + 50;

        if (System.nanoTime() % 2 == 0)
            deltaY = 1;
        else
            deltaY = -1;
    }

    public void changeHeight()
    {
        if (height < 50 || height > 430)
            changeDirection();

        height = height + deltaY;

        setChanged();
        notifyObservers();
    }

    public void changeDirection() { deltaY *= -1; }
}
