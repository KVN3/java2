package tekenapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TekenController implements ActionListener
{
    private TekenModel model;
    private int red;
    private int green;
    private int blue;

    private Color color;
    

    
    public TekenController(TekenModel model)
    {
        this.model = model;
    }
    
    public void actionPerformed(ActionEvent event)
    {
        JComponent source = (JComponent) event.getSource();

        if (source instanceof JButton)
        {
            model.nieuweTekening();
        }
    }
}
