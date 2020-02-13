package Wekker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClockController implements ActionListener
{
    private ArrayList<AlarmModel> alarmen;

    public ClockController(ArrayList<AlarmModel> alarmen)
    {
        this.alarmen = alarmen;
    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        for (AlarmModel alarm : alarmen)
        {
            alarm.volgendeKlokMinuut();

            if(alarm.isOn())
            {
                if(alarm.getClock().equals(alarm.getAlarm())){
                    alarm.setRinging(true);
                }
                else{
                    alarm.setRinging(false);
                }
            }
        }
    }
}