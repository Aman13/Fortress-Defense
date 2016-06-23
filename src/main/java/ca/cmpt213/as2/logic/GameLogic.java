package ca.cmpt213.as2.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameLogic {
    private static final int MAX_HEALTH = 1500;
    private static final int TANKS_REQUIRED = 5;
    private Map map;
    private List<Tank> tanks = new ArrayList<>();

    private int fortressHp;

    public GameLogic() {

    }

    public void initializeGame() {
        this.fortressHp = MAX_HEALTH;
        this.map = new Map();
        for (int i = 0; i < TANKS_REQUIRED; i++) {
            Tank tank = new Tank(map.generateTankPosition());
            tanks.add(tank);
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
        this.fortressHp -= damageTaken;
    }

    public int[][] getMap() {
        return map.getMap();
    }

    public void printTestMap() {
        for (int[] row : map.getMap()) {
            System.out.println(Arrays.toString(row));
        }
    }

    public boolean updateTankAndMap(int row, int col) {
        boolean hit = map.checkForHit(row, col);
        if (hit) {
            for(Tank tank : tanks) {
                tank.updateHealth(row, col);
            }
        }
        return hit;
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

    public Tank getTank(int i) {
        return tanks.get(i);
    }
}