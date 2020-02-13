package klok;

import klok.Models.KlokModel;
import klok.Models.WereldKlokModel;

import javax.swing.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class KlokApplicatie
{
    public KlokApplicatie()
    {
        ArrayList<KlokConsoleView> displays = new ArrayList<KlokConsoleView>();
        ArrayList<KlokModel> klokken = new ArrayList<KlokModel>();

        KlokModel klok = new KlokModel(LocalTime.now().getHour(), LocalTime.now().getMinute());
        WereldKlokModel wereldKlok = new WereldKlokModel(LocalTime.now().getHour(), LocalTime.now().getMinute());

        klokken.add(klok);
        klokken.add(wereldKlok);

        displays.add(new KlokConsoleView(klok));
        displays.add(new KlokConsoleView(wereldKlok));

        for (KlokConsoleView display : displays)
        {
            Timer pulse = new Timer(60000, new PulseController(klokken));
            int topOfMinute = 60000 - (LocalTime.now().getSecond() * 1000); // start on top of minute
            pulse.setInitialDelay(topOfMinute);
            pulse.start();

            waitForEnter();
        }
    }

    private void waitForEnter(){
        new Scanner(System.in).nextLine();
        System.exit(0);
    }

    public static void main(String[] args)
    {
        new KlokApplicatie();
    }
}
