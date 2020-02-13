package Wekker;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class KnoppenBalk extends JPanel
{
    private AlarmController controller;
    private JButton plusKnop;
    private JButton minKnop;

    private JButton startKnop;

    public KnoppenBalk(ArrayList<AlarmModel> alarmen)
    {
        this.controller = new AlarmController(alarmen);
        plusKnop = new JButton("+");
        plusKnop.addActionListener(controller);

        minKnop = new JButton("-");
        minKnop.addActionListener(controller);

        startKnop = new JButton("On | Off");
        startKnop.addActionListener(controller);

        setLayout(new GridLayout(1, 3));
        add(minKnop);
        add(plusKnop);
        add(startKnop);
    }

    public JButton getStartKnop()
    {
        return startKnop;
    }
}
