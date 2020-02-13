package Zuigers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Observable;
import java.util.Observer;

public class ZuigerPanel extends JPanel implements Observer, Runnable, MouseWheelListener
{
    private Zuiger zuiger;
    private Thread t;
    private int posX = 10, posY = 10, speed = 2;
    private static final int BOTTOM_POS = 530;
    private static final int ZUIGER_WIDTH = 60;

    public ZuigerPanel(Zuiger z)
    {
        this.zuiger = z;

        zuiger.addObserver(this);
        addMouseWheelListener(this);

        startThread();

        setBorder(BorderFactory.createTitledBorder(t.getName()));
        add(new StopPanel(this.zuiger, this), BorderLayout.SOUTH);
    }

    public void startThread()
    {
        t = new Thread(this);
        t.start();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // bodem
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(3, posY + 500, this.getWidth() - 5, 50);

        // zuiger
        g.setColor(Color.BLUE);

        int posX = (this.getWidth() / 2) - (ZUIGER_WIDTH / 2);
        g.fillRect(posX, posY + (BOTTOM_POS - zuiger.getHeight()),
                ZUIGER_WIDTH, zuiger.getHeight());
    }

    @Override
    public void update(Observable obs, Object object) { repaint(); }

    @Override
    public void run()
    {
        while (zuiger.isActive())
        {
            zuiger.changeHeight();

            try
            {
                Thread.sleep(speed);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void mouseWheelMoved(MouseWheelEvent e)
    {
        speed += (e.getWheelRotation() * 3);
        if (speed < 2)
            speed = 2;
        if (speed > 100)
            speed = 100;
        repaint();
    }

}
