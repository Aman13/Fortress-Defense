package ca.cmpt213.as2.logic;

public class Tank {
    private static final int[] STARTING_HEALTH = {1,1,1,1};
    private static final int LOCATION_MAX = 8;
    private static final int ARRAY_POSITION_SIZE = 2;
    private int[] location;
    private int[] health;

    public Tank(int[] location) {
        this.location = location;
        this.health = STARTING_HEALTH;
    }

    public int[] getLocation() {
        return this.location;
    }

    public int[] getHealth() {
        return this.health;
    }

    public void updateHealth(int row, int col, int newHealthValue) {
        int rowIndex;
        int colIndex;
        int healthIndex = 0;
        int damagedPiece = 0;
        boolean locationFound = false;
        for (rowIndex = 0; rowIndex + 1 < LOCATION_MAX; rowIndex += ARRAY_POSITION_SIZE) {
            colIndex = rowIndex + 1;
            if(location[rowIndex] == row && location[colIndex] == col) {
                locationFound = true;
                damagedPiece = healthIndex;
            }
            healthIndex++;
        }
        if (locationFound) {
            health[damagedPiece] = newHealthValue;
        }
    }

    public int damage() {
        int undamagedTankCells = 0;
        int damage = 0;
        for (int i = 0; i < health.length; i++) {
            if (health[i] == 1) {
                undamagedTankCells++;
            }
        }
        if (undamagedTankCells == 4) {
            damage = 20;
        } else if (undamagedTankCells == 3) {
            damage = 5;
        } else if (undamagedTankCells == 2) {
            damage = 2;
        } else if (undamagedTankCells == 1) {
            damage = 1;
        } else {
            damage = 0;
        }
        return damage;
    }




}


