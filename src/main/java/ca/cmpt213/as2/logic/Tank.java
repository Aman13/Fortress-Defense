package ca.cmpt213.as2.logic;

import java.util.List;
import java.util.ArrayList;



public class Tank {
    private static final int TANK_PIECES_REQUIRED = 4;
    private static final int ROW_INDEX = 0;
    private static final int COL_INDEX = 1;

    private static final int[] STARTING_HEALTH = {1,1,1,1};
    private List<Integer[]> location;
    private int[] health;

    public Tank(List<Integer[]> location) {
        this.location = new ArrayList<>(location);
        this.health = STARTING_HEALTH;
    }

    public List<Integer[]> getLocation() {
        return this.location;
    }

    public int[] getHealth() {
        return this.health;
    }

    public void updateHealth(int row, int col, int newHealthValue) {
        int locationIndex = 0;
        int damagedPiece = 0;
        boolean locationFound = false;
        for (int i = 0; i < TANK_PIECES_REQUIRED; i++) {
            if(location.get(i)[ROW_INDEX] == row && location.get(i)[COL_INDEX] == col) {
                locationFound = true;
                damagedPiece = i;
            }
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

