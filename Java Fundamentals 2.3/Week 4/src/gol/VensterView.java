package gol;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JPanel;

public class VensterView extends JPanel implements Observer, KeyListener {
    private LifeModel model;
    private int rowOffset;
    private int colOffset;
    private int rowCount;
    private int colCount;
    private JButton[][] buttons;

    public VensterView(LifeModel model, int rowOffset, int colOffset, int rowCount, int colCount) {
        this.model = model;
        this.model.addObserver(this);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();
        this.rowOffset = rowOffset;
        this.colOffset = colOffset;
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.buttons = new JButton[rowCount][colCount];
        this.setLayout(new GridLayout(rowCount, colCount));
        this.initializeGrid();
        this.setVisible(true);
    }

    private void initializeGrid() {
        for(int r = 0; r < this.rowCount; r++) {
            for(int k = 0; k < this.colCount; ++k) {
                this.buttons[r][k] = new JButton("");
                if (this.model.isLevend(r + this.rowOffset, k + this.colOffset)) {
                    this.buttons[r][k].setBackground(Color.WHITE);
                } else {
                    this.buttons[r][k].setBackground(Color.BLACK);
                }

                this.buttons[r][k].addActionListener(new CelController(this.model, new CelPosition(r, k)));
                this.add(this.buttons[r][k]);
            }
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.refresh();
    }

    private void refreshCel(int rij, int kolom) {
        if (this.model.isLevend(rij + this.rowOffset, kolom + this.colOffset)) {
            this.buttons[rij][kolom].setBackground(Color.WHITE);
        } else {
            this.buttons[rij][kolom].setBackground(Color.BLACK);
        }

    }

    public void refresh() {
        for(int r = 0; r < this.rowCount; ++r) {
            for(int k = 0; k < this.colCount; ++k) {
                this.refreshCel(r, k);
            }
        }

        this.repaint();
    }

    public void update(Observable o, Object arg) {
        this.refresh();
    }

    public void keyPressed(KeyEvent arg0) {
        int keyCode = arg0.getKeyCode();
        switch(keyCode) {
            case 37:
                --this.colOffset;
                break;
            case 38:
                --this.rowOffset;
                break;
            case 39:
                ++this.colOffset;
                break;
            case 40:
                ++this.rowOffset;
        }

        if (this.colOffset < 0) {
            this.colOffset = 0;
        } else if (this.colOffset > this.model.getMAXKOLOMMEN()) {
            this.colOffset = this.model.getMAXKOLOMMEN();
        }

        if (this.rowOffset < 0) {
            this.rowOffset = 0;
        } else if (this.rowOffset > this.model.getMAXRIJEN()) {
            this.rowOffset = this.model.getMAXRIJEN();
        }

        this.repaint();
    }

    public void keyReleased(KeyEvent arg0) {
    }

    public void keyTyped(KeyEvent arg0) {
    }
}

