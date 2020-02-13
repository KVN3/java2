package Wekker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AlarmController implements ActionListener
{
    private ArrayList<AlarmModel> alarmen;

    public AlarmController(ArrayList<AlarmModel> alarmen)
    {
        this.alarmen = alarmen;
    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        JComponent source = (JComponent) event.getSource();
        String buttonType = event.getActionCommand();

        if (source instanceof JButton)
        {
            for(AlarmModel alarm : alarmen)
            {
                if (buttonType.equals("+"))
                {
                    alarm.addAlarmMinuut();
                }

                if (buttonType.equals("-"))
                {
                    alarm.substractAlarmMinuut();
                }

                if(buttonType.equals("On | Off")){
                    if(alarm.isOn()){
                        alarm.setOn(false);
                        alarm.setRinging(false);
                    }
                    else{
                        alarm.setOn(true);
                    }

                }
            }

        }
    }
}