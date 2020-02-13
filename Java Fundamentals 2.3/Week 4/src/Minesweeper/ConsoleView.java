package Minesweeper;

import java.util.Observable;

import java.util.Observer;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class ConsoleView implements Observer
{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private MineModel model;
    private Scanner invoer = new Scanner(System.in);

    public ConsoleView(MineModel model)
    {
        this.model = model;
        model.addObserver(this);
    }

    private void toonVakje(int rij, int kolom)
    {
        Vakje vakje = model.getVakje(rij, kolom);
        System.out.print("  ");

        if (vakje.isRevealed())
        {
            if (vakje.isBom())
                System.out.print("X");
            else
                printRevealed(model.getNeighbouringBombs(rij, kolom));
        } else
            System.out.print(" ");
    }

    public void printRevealed(int neighbouringBombs)
    {
        switch (neighbouringBombs)
        {
            case 0:
                System.out.print("_");
                break;
            case 1:
                System.out.print(ANSI_GREEN + neighbouringBombs + ANSI_RESET);
                break;
            case 2:
            case 3:
                System.out.print(ANSI_YELLOW + neighbouringBombs + ANSI_RESET);
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                System.out.print(ANSI_RED + neighbouringBombs + ANSI_RESET);
                break;
        }
    }

    private void toonVakjeTest(int rij, int kolom)
    {
        Vakje vakje = model.getVakje(rij, kolom);
        System.out.print("  ");
        if (vakje.isBom())
            System.out.print(ANSI_PURPLE + "X" + ANSI_RESET);
        else
            printRevealed(model.getNeighbouringBombs(rij, kolom));
    }

    private void printGrid()
    {
        for (int rij = 0; rij < model.getGridRows() + 1; rij++)
        {
            System.out.print(ANSI_CYAN);
            System.out.printf("%3d", (rij));
            System.out.print(ANSI_RESET);
            for (int kolom = 0; kolom < model.getGridColumns() + 1; kolom++)
            {
                if (rij == 0 && kolom != 0)
                {
                    System.out.print(ANSI_CYAN);
                    System.out.printf("%3d", (kolom));
                    System.out.print(ANSI_RESET);
                    if (kolom == model.getGridColumns())
                        System.out.print(" KOLOM");
                }

                if (rij > 0 && kolom > 0)
                    toonVakje(rij - 1, kolom - 1);
            }
            System.out.println();
        }
    }

    private void printCheatSheetGrid()
    {
        for (int rij = 0; rij < model.getGridRows() + 1; rij++)
        {
            System.out.print(ANSI_CYAN);
            System.out.printf("%3d", (rij));
            System.out.print(ANSI_RESET);
            for (int kolom = 0; kolom < model.getGridColumns() + 1; kolom++)
            {
                if (rij == 0 && kolom != 0)
                {
                    System.out.print(ANSI_CYAN);
                    System.out.printf("%3d", (kolom));
                    System.out.print(ANSI_RESET);
                }

                if (rij > 0 && kolom > 0)
                    toonVakjeTest(rij - 1, kolom - 1);
            }
            System.out.println();
        }
    }

    private void printQuestions()
    {
        System.out.print("ROW: ");
        int row = (parseInt(invoer.nextLine()) - 1);
        System.out.print("COLUMN: ");
        int column = (parseInt(invoer.nextLine()) - 1);

        model.openVakje(row, column);
    }

    private void refresh()
    {
        if (model.isGameOver())
        {
            System.out.print("GAME OVER!");
        } else
        {
            printGrid();
            printCheatSheetGrid();
            printQuestions();
        }
    }

    @Override
    public void update(Observable model, Object info)
    {
        refresh();
        System.out.println("\n");
    }
}
