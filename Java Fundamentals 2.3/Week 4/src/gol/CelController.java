package gol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CelController implements ActionListener {
    private LifeModel model;
    private CelPosition cel;

    public CelController(LifeModel model, CelPosition cel) {
        this.model = model;
        this.cel = cel;
    }

    private void toggle(CelPosition cel) {
        if (cel != null) {
            this.model.toggle(cel.getRij(), cel.getKolom());
        }

    }

    public void actionPerformed(ActionEvent e) {
        this.toggle(this.cel);
    }
}
