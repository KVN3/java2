package gameoflifev2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class KnoppenBalk extends JPanel
{
    private TimerController controller;
    private JButton pauseKnop;

    public KnoppenBalk(LifeModell model, Timer tick)
    {
        pauseKnop = new JButton("Pause");
        this.controller = new TimerController(model, tick, pauseKnop);
        pauseKnop.addActionListener(controller);

        pauseKnop.setBackground(Color.GREEN);
        setLayout(new GridLayout(1, 1));
        add(pauseKnop);


    }
}
