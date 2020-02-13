package Melkweg;

import javax.swing.*;
import java.awt.*;

public class MelkwegApp extends JFrame
{
    public MelkwegApp()
    {
        setBounds(100, 100, 800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Melkweg Generator");

        TekenPaneel paneel = new TekenPaneel();
        add(paneel);


        setVisible(true);

    }

    public static void main(String[] args)
    {
        new MelkwegApp();
    }
}
