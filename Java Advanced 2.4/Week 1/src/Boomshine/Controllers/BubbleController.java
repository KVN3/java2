package Boomshine.Controllers;

import Boomshine.Bubble;

import java.util.Random;

public class BubbleController implements Runnable
{
    private Bubble bubble;

    public BubbleController(Bubble bubble)
    {
        this.bubble = bubble;
    }

    public void run()
    {
        while (bubble.moving)
        {
            bubble.move();

            try
            {
                Thread.sleep(new Random().nextInt(20) + 3);
            } catch (InterruptedException ie)
            {
                ie.printStackTrace();
            }
        }
    }
}
