package EventHandlingOpdrachten;

import javax.swing.*;

public class TekenApp extends JFrame
{
    public TekenApp()
    {
        setBounds(100, 100, 350, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Teken Applicatie");

        add(new TekstPaneel());

        setVisible(true);
    }


    public static void main(String[] args)
    {
        new TekenApp();
    }
}
