package tekenapp;

import java.awt.*;
import java.util.Observable;

public class TekenModel extends Observable
{
    public TekenModel()
    {

    }

    public void nieuweTekening()
    {


        setChanged();
        notifyObservers();
//        paneel.repaint();
//        paneel.getG().setColor(
//                new Color(Integer.parseInt(balk.getRedValue().getText())
//                        , Integer.parseInt(balk.getGreenValue().getText())
//                        , Integer.parseInt(balk.getBlueValue().getText())));
//        paneel.paintComponent(paneel.getG());
    }
}
