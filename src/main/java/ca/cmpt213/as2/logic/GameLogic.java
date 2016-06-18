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

    public int getFortressHealth() {
        return this.fortressHp;
    }

    public int[][] getMap() {
        return map.getMap();
    }

    public void printTestMap() {
        for (int[] row : map.getMap()) {
            System.out.println(Arrays.toString(row));
        }
    }
}
