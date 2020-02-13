package Zuigers;

import javax.swing.*;
import java.awt.*;

public class ZuigerApplication extends JFrame
{
    public static final int PANEL_SIZE = 600;

    public ZuigerApplication()
    {


        initialize();

        ZuigerPanel panel1 = new ZuigerPanel(new Zuiger());
        ZuigerPanel panel2 = new ZuigerPanel(new Zuiger());
        ZuigerPanel panel3 = new ZuigerPanel(new Zuiger());
        ZuigerPanel panel4 = new ZuigerPanel(new Zuiger());

        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);
    }

    private void initialize()
    {
        setBounds(100, 100, PANEL_SIZE, PANEL_SIZE);
        setTitle("Zuigers in actie...");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new GridLayout(1,4));
        setVisible(true);
    }

    public static void main(String[] args) { new ZuigerApplication(); }
}
