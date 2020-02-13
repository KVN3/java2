package gameoflife;

import java.util.Random;

public class LifeModel {

    private boolean[][] grid;

    public LifeModel() {

        initialize();
    }

    private void initialize() {
        grid = new boolean[20][30]; // alle velden geinitialiseerd als false

        for (int rij = 1; rij < 18; rij++) { // Eerste en laatste rij leeg laten
            for (int kolom = 1; kolom < 28; kolom++) {  // Eerte en laatste kolom leeglaten
                if ((new Random().nextInt(10) + 1) % 3 == 0) { // 20%. Ik vond 10% wat kaal en komt soms in 'frozen stat	e'
                    grid[rij][kolom] = true;
                }
            }
        }
    }

    public int getCount() { // Hoeveel levende cellen zijn er nog
        int count = 0;
        for (int rij = 0; rij < 19; rij++) {
            for (int kolom = 0; kolom < 29; kolom++) {
                if (isLevend(rij, kolom)) {
                    count++;
                }
            }
        }
        return count;
    }

    public void toon() {
        for (int rij = 0; rij < 20; rij++) {
            System.out.printf("%2d", rij);
            for (int kolom = 0; kolom < 30; kolom++) {
                if (isLevend(rij, kolom) == true) {
                    System.out.print("X");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    public boolean isLevend(int rij, int kolom) {
        return grid[rij][kolom];
    }

    private boolean evolueer(int rij, int kolom) {

        int aantalBuren = telBuren(rij, kolom);

        if (isLevend(rij, kolom) & (aantalBuren < 2 || aantalBuren > 3)) return false;
        if (!isLevend(rij, kolom) & aantalBuren == 3) return true;

        return isLevend(rij, kolom);
    }

    public void volgendeGeneratie() {
        boolean[][] newGrid = new boolean[20][30];
        for (int rij = 0; rij < 19; rij++) {
            for (int kolom = 0; kolom < 29; kolom++) {
                newGrid[rij][kolom] = evolueer(rij, kolom);

            }
        }
        for (int rij = 0; rij < 19; rij++) {
            for (int kolom = 0; kolom < 29; kolom++) {
                grid[rij][kolom] = newGrid[rij][kolom];

            }
        }

    }

    /*
     * / Make a subarray taking the boundaries into account. Cells outside the
     * boundary are by default false
     */
    private int telBuren(int rij, int kolom) {
        int aantal = 0;
        boolean[][] burenGrid = new boolean[3][3];
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if ((rij + i < 0) || (rij + i > 29) || (kolom + j < 0) || (kolom + j > 19)) {
                    continue;
                } else {
                    burenGrid[i + 1][j + 1] = isLevend(rij + i, kolom + j);
                }
            }
        }

        /*
         * Daadwerkelijk tellen
         */
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 & j == 1) { // Je kunt niet je eigen buur zijn
                    continue;
                }
                if (burenGrid[i][j]) {
                    aantal++;
                }
            }
        }
        return aantal;
    }

    public void test() {

        for (int i = 0; i < 5; i++) {
            int rij = new Random().nextInt(19);
            int kolom = new Random().nextInt(29);
            System.out.println("Rij: " + rij + ", kolom: " + kolom + ", buren: " + telBuren(rij, kolom));
            System.out.println("Oude status: " + isLevend(rij, kolom) + ", nieuwe status: " + evolueer(rij, kolom));
        }
    }
}

