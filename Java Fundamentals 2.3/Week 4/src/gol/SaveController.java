package gol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveController implements ActionListener {
    private LifeModel life;

    public SaveController(LifeModel model) {
        this.life = model;
    }

    private void Save() {
        try {
            ObjectOutputStream uit = new ObjectOutputStream(new FileOutputStream("LifeModel.dat"));
            uit.writeObject(this.life);
            uit.close();
        } catch (FileNotFoundException var2) {
            System.out.println("File not found by Save in SaveConroller");
            var2.printStackTrace();
        } catch (IOException var3) {
            System.out.println("I/O exception by Save in SaveConroller");
            var3.printStackTrace();
        }

    }

    public void actionPerformed(ActionEvent arg0) {
        this.Save();
    }
}
