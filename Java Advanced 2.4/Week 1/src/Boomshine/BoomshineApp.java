package Boomshine;

import Boomshine.Controllers.BubbleController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class BoomshineApp extends JFrame
{
    public static final int PANEL_SIZE = 900;
    private ArrayList<Bubble> bubbles = new ArrayList<>();
    private int spawnX, spawnY, straal;
    private Random rnd = new Random();

    public BoomshineApp()
    {
        initialize();
        setBackground(Color.BLUE);

        spawnBubbles(PANEL_SIZE / 40);

        BoomshineView boomshineView = new BoomshineView(bubbles);
        add(boomshineView, BorderLayout.CENTER);
    }

    private void spawnBubbles(int amountOfBubbles)
    {
        for (int i = 0; i < amountOfBubbles; i++)
        {
            spawnX = rnd.nextInt((PANEL_SIZE - 99)) + 1;
            spawnY = rnd.nextInt((PANEL_SIZE - 99)) + 1;
            straal = rnd.nextInt(30) + 30;
            Bubble bubble = new Bubble(spawnX, spawnY, straal, true);

            Thread thread = new Thread(new BubbleController(bubble));
            thread.start();

            bubbles.add(bubble);
        }
    }

    private void initialize()
    {
        setBounds(100, 100, PANEL_SIZE, PANEL_SIZE);
        setTitle("Boomshine ... magic bubbles game hey");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new BoomshineApp();
    }
}
