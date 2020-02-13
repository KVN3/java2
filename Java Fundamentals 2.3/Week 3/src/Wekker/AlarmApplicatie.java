package Wekker;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class AlarmApplicatie extends JFrame
{
    public AlarmApplicatie()
    {
        ArrayList<AlarmPanelView> views = new ArrayList<AlarmPanelView>();
        ArrayList<AlarmModel> alarmen = new ArrayList<AlarmModel>();

        AlarmModel alarm = new AlarmModel(LocalTime.now().getHour(), LocalTime.now().getMinute());

        alarmen.add(alarm);

        KnoppenBalk knoppenBalk = new KnoppenBalk(alarmen);

        views.add(new AlarmPanelView(alarm, knoppenBalk));

        Timer pulse = new Timer(60000, new ClockController(alarmen));
        int topOfMinute = 60000 - (LocalTime.now().getSecond() * 1000); // start on top of minute
        pulse.setInitialDelay(topOfMinute);

        for (AlarmPanelView view : views)
        {
            setBounds(500, 200, 300,200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Alarm Clock");
            setVisible(true);

            add(view, BorderLayout.PAGE_START);
            add(knoppenBalk, BorderLayout.SOUTH);
        }

        pulse.start();
        waitForEnter();
    }

    private void waitForEnter(){
        new Scanner(System.in).nextLine();
        System.exit(0);
    }

    public static void main(String[] args)
    {
        new AlarmApplicatie();
    }
}
