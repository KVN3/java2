package Minesweeper;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class MineModel extends Observable
{
    private int gridRows;

    public int getGridRows()
    {
        return gridRows;
    }

    public int getGridColumns()
    {
        return gridColumns;
    }

    private int gridColumns;


    private ArrayList<Vakje> newTempGrid;
    private ArrayList<Vakje> revealedZeros = new ArrayList<>();
    private Vakje[][] grid;
    private Random rnd = new Random();

    public boolean isGameOver()
    {
        return gameOver;
    }

    private boolean gameOver = false;


    public MineModel(int row, int column)
    {
        gridRows = row;
        gridColumns = column;

        grid = new Vakje[gridRows][gridColumns];
        Initialize();
    }

    public void Initialize()
    {
        for (int rij = 0; rij < gridRows; rij++)
        {
            for (int kolom = 0; kolom < gridColumns; kolom++)
            {
                if ((rnd.nextInt(101) + 1) % 6 == 0)
                {
                    grid[rij][kolom] = new Vakje(rij, kolom, true);
                } else
                    grid[rij][kolom] = new Vakje(rij, kolom, false);
            }
        }

        setChanged();
        notifyObservers();
    }

    public void revealNeighbours(ArrayList<Vakje> tempGrid)
    {
        newTempGrid = new ArrayList<>();

        for (Vakje vakje : tempGrid)
        {
            for (int i = -1; i < 2; i++)
            {
                for (int j = -1; j < 2; j++)
                {
                    if ((vakje.rij + i < 0) || (vakje.rij + i > gridRows - 1) || (vakje.kolom + j < 0) || (vakje.kolom + j > gridColumns - 1))
                    {
                        continue;
                    } else
                    {
                        grid[vakje.rij + i][vakje.kolom + j].setRevealed(true);

                        if (getNeighbouringBombs(vakje.rij + i, vakje.kolom + j) == 0 && !revealedZeros.contains(vakje))
                        {
                            newTempGrid.add(grid[vakje.rij + i][ vakje.kolom + j]);
                        }
                    }
                }
            }

            revealedZeros.add(vakje);
        }

        if(!newTempGrid.isEmpty()){
            revealNeighbours(newTempGrid);
        }
    }

    public int getNeighbouringBombs(int rij, int kolom)
    {
        int aantal = 0;

        boolean[][] burenGrid = new boolean[3][3];

        for (int i = -1; i < 2; i++)
        {
            for (int j = -1; j < 2; j++)
            {
                if ((rij + i < 0) || (rij + i > gridRows - 1) || (kolom + j < 0) || (kolom + j > gridColumns - 1))
                {
                    continue;
                } else
                {
                    if (grid[rij + i][kolom + j].isBom())
                    {
                        burenGrid[i + 1][j + 1] = true;
                        aantal++;
                    }
                }
            }
        }

        return aantal;
    }

    public Vakje getVakje(int rij, int kolom)
    {
        return grid[rij][kolom];
    }

    public void openVakje(int row, int column)
    {
        Vakje vakje = grid[row][column];

        if (vakje.isBom())
            gameOver = true;
        else if (getNeighbouringBombs(row, column) == 0)
        {
            ArrayList<Vakje> tempGrid = new ArrayList<>();
            tempGrid.add(vakje);
            revealNeighbours(tempGrid);
        }

        vakje.setRevealed(true);

        setChanged();
        notifyObservers();
    }



    public void revealAll()
    {
        for (int rij = 0; rij < gridRows; rij++)
        {
            for (int kolom = 0; kolom < gridColumns; kolom++)
            {
                grid[rij][kolom].setRevealed(true);
            }
        }
    }

}
