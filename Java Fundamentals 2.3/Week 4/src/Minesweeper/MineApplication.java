package Minesweeper;

import javax.swing.*;
import java.awt.*;

public class MineApplication extends JFrame
{
    private int row = 10, column = 10;
    private int factor = 4;

    public MineApplication()
    {
        MineModel model = new MineModel(row, column);
        //ConsoleView view = new ConsoleView(model);

        initialize();

        //add(new ButtonView(model), BorderLayout.CENTER);

        add(new TekenPaneel(model, factor * row));

        model.Initialize();
    }

    private void initialize(){
        setBounds(0,0,420,440);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Mine Bomber");

        setVisible(true);
    }

    public static void main(String[] args)
    {
        new MineApplication();
    }
}
