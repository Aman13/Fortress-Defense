package ca.cmpt213.as2.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameLogic {
    private static final int MAX_HEALTH = 1500;
    private static final int TANKS_REQUIRED = 5;
    private static Map map;
    private static List<Tank> tanks = new ArrayList<Tank>();

    private static int fortressHp = MAX_HEALTH;

    public GameLogic() {
        this.map = new Map();
        for (int i = 0; i < TANKS_REQUIRED; i++) {
            map.generateTankPosition();
        }
    }

    public int calculateTankDamage() {
        int totalDamage = 0;
        for (Tank tank : tanks) {
            totalDamage += tank.damage();
        }
        return totalDamage;
    }

    public int getFortressHealth() {
        return this.fortressHp;
    }

    public void updateFortressHealth() {
        int damageTaken = calculateTankDamage();
        fortressHp = fortressHp - damageTaken;
    }

    public int[][] getMap() {
        return map.getMap();
    }

    public void printTestMap() {
        for (int[] row : map.getMap()) {
            System.out.println(Arrays.toString(row));
        }
    }

    public void updateTankAndMap(int row, int col) {
        boolean isHit = map.checkForHit(row, col);
        if (isHit) {
            for(Tank tank : tanks) {
                tank.updateHealth(row, col, 0);
            }
        }
    }

    /**
     *
     * @return Returns 1 for win, -1 for lose, 0 for neither
     */
    public int checkForWinLose() {
        if (fortressHp <= 0) {
            return -1;
        }
        int totalTankHealth = 0;
        for (Tank tank : tanks) {
            for (int i = 0; i < tank.getHealth().length; i++) {
                totalTankHealth += tank.getHealth()[i];
            }
        }
        if (totalTankHealth == 0) {
            return 1;
        } else {
            return 0;
        }
    }
}