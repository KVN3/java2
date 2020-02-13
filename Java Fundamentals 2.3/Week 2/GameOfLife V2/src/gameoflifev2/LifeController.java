package gameoflifev2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LifeController implements ActionListener
{
    private LifeModell model;

    public LifeController(LifeModell model){ this.model = model; }

    public void actionPerformed(ActionEvent event){
        model.volgendeGeneratie();
    }
}
