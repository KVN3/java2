package gol;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;


public class ControlButtons extends JPanel {
    private JButton pauzeBtn = new JButton("Pauze");
    private JButton saveBtn;
    private Timer pulse;

    public ControlButtons(Timer t, LifeModel model) {
        this.pulse = t;
        this.pauzeBtn.addActionListener(new TimerController(this.pulse));
        this.add(this.pauzeBtn);
        this.saveBtn = new JButton("Save");
        this.saveBtn.addActionListener(new SaveController(model));
        this.add(this.saveBtn);
    }
}
