package gameoflifev2;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

@SuppressWarnings("Duplicates")
public class LifeApplicationn extends JFrame
{
    public LifeApplicationn(){
        LifeModell modell = new LifeModell();

        initialize();
        add(new LifePanelView(modell), BorderLayout.CENTER);

        Timer tick = new Timer(1000, new LifeController(modell));
        tick.start();
        add(new KnoppenBalk(modell, tick), BorderLayout.SOUTH);



//        while (lifeModell.getCount() > 0)
//        {
//            Timer tick = new Timer(1000, new LifeController(lifeModell));
//            tick.start();
//            waitForEnter();
//        }
    }

    private void initialize(){
        setBounds(0,0,1700,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Game of Life");
        setVisible(true);
    }

    private static void waitForEnter()
    {
        new Scanner(System.in).nextLine();
        System.exit(0);
    }

    public static void main(String[] args)
    {
        new LifeApplicationn();
    }
}
