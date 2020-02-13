package Zuigers;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopPanel extends JPanel implements ActionListener
{
    private Zuiger z;
    private JButton btn;
    private ZuigerPanel zP;


    public StopPanel(Zuiger zuiger, ZuigerPanel zP)
    {
        btn = new JButton("STOP");
        btn.addActionListener(this);
        add(btn);

        this.z = zuiger;
        this.zP = zP;
    }

    public synchronized void actionPerformed(ActionEvent event)
    {
        if (z.isActive())
        {
            z.setActive(false);
            btn.setText("START");
        } else
        {
            z.setActive(true);
            zP.startThread();
            btn.setText("STOP");
        }
    }

}
