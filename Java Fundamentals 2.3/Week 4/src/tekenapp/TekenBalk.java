package tekenapp;

import javax.swing.*;
import java.awt.*;

import static java.lang.Integer.parseInt;

public class TekenBalk extends JPanel
{
    private TekenModel model;

    private JButton knop;

    private JTextField redValue;
    private JTextField greenValue;
    private JTextField blueValue;
    private JLabel label;

    public Color getColor()
    {
        String red = redValue.getText();
        String green = greenValue.getText();
        String blue = blueValue.getText();

        int redVal = parseInt(red);
        int greenVal = parseInt(green);
        int blueVal = parseInt(blue);

        Color color = new Color(redVal, greenVal, blueVal);

        return color;
    }

    
    public TekenBalk(TekenModel model)
    {
        this.model = model;

        label = new JLabel("Select R G and B values (0-255): ");
        label.setPreferredSize(new Dimension(200, 10));
        
        redValue = new JTextField();
        redValue.setPreferredSize(new Dimension(10,10));
        greenValue = new JTextField();
        blueValue = new JTextField();
        
        knop = new JButton("Teken Paraplu");
        knop.addActionListener(new TekenController(model));
        
        setLayout(new GridLayout(1, 2));
        
        add(label);
        add(redValue);
        add(greenValue);
        add(blueValue);
        add(knop);
    }
}
