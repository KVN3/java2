package gol;

import java.io.Serializable;
import java.util.Observable;
import java.util.Random;

public class LifeModel extends Observable implements Serializable {
    private boolean[][] grid = new boolean[600][600];
    private final int MAXRIJEN = 600;
    private final int MAXKOLOMMEN = 600;
    private Random generator = new Random();

    public int getMAXRIJEN() {
        return 600;
    }

    public int getMAXKOLOMMEN() {
        return 600;
    }

    public LifeModel() {
        this.initialize();
    }

    private void initialize() {
        for(int rij = 1; rij < 599; ++rij) {
            for(int kolom = 1; kolom < 599; ++kolom) {
                if ((this.generator.nextInt(10) + 1) % 3 == 0) {
                    this.grid[rij][kolom] = true;
                }
            }
        }

    }

    public boolean isLevend(int rij, int kolom) {
        return this.grid[rij][kolom];
    }

    public void toggle(int rij, int kolom) {
        this.grid[rij][kolom] = !this.grid[rij][kolom];
        this.setChanged();
        this.notifyObservers();
    }

    private int telBuren(int rij, int kolom) {
        int buren = 0;
        boolean[][] burenGrid = new boolean[3][3];

        int i;
        int j;
        for(i = -1; i < 2; ++i) {
            for(j = -1; j < 2; ++j) {
                if (rij + i >= 0 && rij + i <= 599 && kolom + j >= 0 && kolom + j <= 599) {
                    burenGrid[i + 1][j + 1] = this.isLevend(rij + i, kolom + j);
                }
            }
        }

        for(i = 0; i < burenGrid.length; ++i) {
            for(j = 0; j < burenGrid.length; ++j) {
                if ((i != 1 || j != 1) && burenGrid[i][j]) {
                    ++buren;
                }
            }
        }

        return buren;
    }

    private boolean evolueer(int rij, int kolom) {
        int aantalBuren = this.telBuren(rij, kolom);
        if (this.isLevend(rij, kolom) & (aantalBuren < 2 || aantalBuren > 3)) {
            return false;
        } else {
            return !this.isLevend(rij, kolom) & aantalBuren == 3 ? true : this.isLevend(rij, kolom);
        }
    }

    public void volgendeGeneratie() {
        boolean[][] newGrid = new boolean[600][600];

        int rij;
        int kolom;
        for(rij = 0; rij < 600; ++rij) {
            for(kolom = 0; kolom < 600; ++kolom) {
                newGrid[rij][kolom] = this.evolueer(rij, kolom);
            }
        }

        for(rij = 0; rij < 600; ++rij) {
            for(kolom = 0; kolom < 600; ++kolom) {
                this.grid[rij][kolom] = newGrid[rij][kolom];
            }
        }

        this.setChanged();
        this.notifyObservers();
    }

    public int getCount() {
        int count = 0;

        for(int rij = 0; rij < 599; ++rij) {
            for(int kolom = 0; kolom < 599; ++kolom) {
                if (this.isLevend(rij, kolom)) {
                    ++count;
                }
            }
        }

        return count;
    }
}