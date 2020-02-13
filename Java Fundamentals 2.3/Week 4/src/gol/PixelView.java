package gol;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;


public class PixelView extends JPanel implements Observer {
    private LifeModel model;
    private BufferedImage image;
    private Color levend;
    private Color dood;

    public PixelView(LifeModel m) {
        this.model = m;
        this.model.addObserver(this);
        this.image = new BufferedImage(this.model.getMAXRIJEN(), this.model.getMAXKOLOMMEN(), 2);
        this.levend = Color.WHITE;
        this.dood = Color.BLACK;
        this.refresh();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.image, 0, 0, this);
    }

    private void refreshCel(int rij, int kolom) {
        if (this.model.isLevend(rij, kolom)) {
            this.image.setRGB(rij, kolom, this.levend.getRGB());
        } else {
            this.image.setRGB(rij, kolom, this.dood.getRGB());
        }

    }

    public void refresh() {
        for(int r = 0; r < this.model.getMAXRIJEN(); ++r) {
            for(int k = 0; k < this.model.getMAXKOLOMMEN(); ++k) {
                this.refreshCel(r, k);
            }
        }

        this.repaint();
    }

    public void update(Observable arg0, Object arg1) {
        this.refresh();
    }
}