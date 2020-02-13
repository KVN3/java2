package gol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class TimerController implements ActionListener {
    private Timer pulse;

    public TimerController(Timer t) {
        this.pulse = t;
    }

    public void actionPerformed(ActionEvent arg0) {
        if (this.pulse.isRunning()) {
            this.pulse.stop();
        } else {
            this.pulse.start();
        }

    }
}
