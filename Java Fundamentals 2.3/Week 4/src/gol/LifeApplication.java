package gol;

import java.awt.Font;
import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JFrame;
import javax.swing.Timer;


public class LifeApplication extends JFrame {
    public LifeApplication(LifeModel model) {
        this.setBounds(0, 0, 1000, 1000);
        this.setDefaultCloseOperation(3);
        this.setFont(new Font("Comic Sans MS", 0, 20));
        this.setLayout(new GridLayout(1, 3));
        this.add(new PixelView(model));
        this.add(new VensterView(model, 0, 0, 50, 50));
        this.setVisible(true);
        Timer tick = new Timer(100, new LifeController(model));
        this.add(new ControlButtons(tick, model));
        tick.start();
    }

    public static void main(String[] args) {
        LifeModel model = Load();
        new LifeApplication(model);
    }

    private static LifeModel Load() {
        LifeModel model = null;

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("LifeModel.dat"));
            model = (LifeModel)in.readObject();
            in.close();
        } catch (ClassNotFoundException var2) {
            System.out.println("Class not found by Load in LifeApplication");
            var2.printStackTrace();
        } catch (FileNotFoundException var3) {
            System.out.println("File not found by Load in LifeApplication");
            var3.printStackTrace();
            model = new LifeModel();
        } catch (IOException var4) {
            System.out.println("I/O by Load in LifeApplication");
            var4.printStackTrace();
            model = new LifeModel();
        }

        return model;
    }
}

