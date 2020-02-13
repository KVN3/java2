package Boomshine.Controllers;

import Boomshine.*;

import java.util.ArrayList;
import java.util.Random;

public class GroeiController implements Runnable
{
    private Bubble bubble;
    private ArrayList<Bubble> bubbles;

    public GroeiController(Bubble bubble, ArrayList<Bubble> bubbles)
    {
        this.bubble = bubble;
        this.bubbles = bubbles;
    }

    public void run()
    {
        while (bubble.getStraal() < 101)
        {
            bubble.groei();

            for (Bubble movingBubble : bubbles)
            {
                if (bubble.botstMet(movingBubble))
                {
                    movingBubble.setMoving(false);
                }
            }

            try
            {
                Thread.sleep(10);
            } catch (InterruptedException ie)
            {
                ie.printStackTrace();
            }
        }
    }
}
