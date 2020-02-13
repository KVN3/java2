package tekenapp;

import javax.swing.*;
import java.awt.*;

public class TekenApp extends JFrame
{
    public TekenApp()
    {
        setBounds(100, 100, 350, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Teken Applicatie");
        
        TekenModel model = new TekenModel();

        TekenBalk balk = new TekenBalk(model);
        add(balk, BorderLayout.NORTH);

        TekenPaneel paneel = new TekenPaneel(model, balk);
        add(paneel);


        setVisible(true);
        
    }
    
    public static void main(String[] args)
    {
        new TekenApp();
    }
}
