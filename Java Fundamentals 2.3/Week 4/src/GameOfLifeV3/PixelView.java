package GameOfLifeV3;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

public class PixelView extends JPanel implements Observer
{
    private LifeModell model;
    private BufferedImage image;

    public PixelView(LifeModell model)
    {
        this.model = model;
        model.addObserver(this);

        image = new BufferedImage(1700, 1000, BufferedImage.TYPE_INT_ARGB);
        //image.setRGB(19, 29, Color.WHITE);

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.drawImage(image, 0, 0, 1700, 1000, this);
    }


    private void refresh()
    {
        repaint();
    }

    @Override
    public void update(Observable LifeModell, Object info)
    {
        refresh();
    }
}
