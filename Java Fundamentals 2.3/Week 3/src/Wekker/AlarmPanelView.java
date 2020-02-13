package Wekker;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class AlarmPanelView extends JPanel implements Observer
{
    private boolean on;
    private JLabel klokTijd;
    private JLabel alarmTijd;

    private KnoppenBalk knoppenBalk;

    private AlarmModel alarm;

    public AlarmPanelView(AlarmModel alarm, KnoppenBalk knoppenBalk)
    {
        this.alarm = alarm;
        this.knoppenBalk = knoppenBalk;

        setLayout(new FlowLayout());

        klokTijd = new JLabel();
        add(klokTijd);

        alarmTijd = new JLabel();
        add(alarmTijd);

        alarm.addObserver(this);
        refresh();
    }

    private void refresh()
    {
        klokTijd.setText("Current time: " + alarm.getClock());
        alarmTijd.setText("Alarm at: " + alarm.getAlarm());

        if (alarm.isOn())
        {
            knoppenBalk.getStartKnop().setBackground(Color.GREEN);
            if (alarm.isRinging())
            {
                alarmTijd.setForeground(Color.RED);
                alarm.playSound();
            } else
            {
                alarmTijd.setForeground(Color.GREEN);
            }
        } else
        {
            knoppenBalk.getStartKnop().setBackground(Color.DARK_GRAY);
            alarmTijd.setForeground(Color.BLACK);
            alarm.stopSound();
        }
    }


    @Override
    public void update(Observable AlarmModel, Object info)
    {
        refresh();
    }
}
