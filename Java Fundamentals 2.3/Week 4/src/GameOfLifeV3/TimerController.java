package GameOfLifeV3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class TimerController implements ActionListener
{
    private LifeModell model;
    private Timer tick;
    private JButton pauseKnop;

    public TimerController(LifeModell model, Timer tick, JButton pauseKnop)
    {
        this.model = model;
        this.tick = tick;
        this.pauseKnop = pauseKnop;
    }

    public void actionPerformed(ActionEvent event)
    {
        if (tick.isRunning())
        {
            tick.stop();
            pauseKnop.setBackground(Color.RED);
        } else
        {
            tick.start();
            pauseKnop.setBackground(Color.GREEN);
        }
    }

    private static void waitForEnter()
    {
        new Scanner(System.in).nextLine();
        System.exit(0);
    }
}
