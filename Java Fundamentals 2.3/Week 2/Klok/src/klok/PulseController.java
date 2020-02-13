package klok;

import klok.Models.KlokModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PulseController implements ActionListener
{
    private ArrayList<KlokModel> klokken;

    public PulseController(ArrayList<KlokModel> klokken)
    {
        this.klokken = klokken;
    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        for(KlokModel klok : klokken){
            klok.volgendeMinuut();
        }
    }
}
