package ca.cmpt213.as2.logic;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GameLogicTest {
    private static final int FULL_HEALTH_TANK_VOLLEY_DAMAGE = 100;
    private static final int FORTRESS_MAX_HEALTH = 1500;
    private static final int FORTRESS_DAMAGED_FIVE_FULL_HEALTH_TANKS = 1400;
    private static final int WIN_CONDITION = 1;
    private static final int LOSE_CONDITION = -1;

    @Test
    public void testStartingFortressHealth() throws Exception {
        GameLogic logic = new GameLogic();
        Assert.assertEquals(FORTRESS_MAX_HEALTH, logic.getFortressHealth(), 0);
    }

    @Test
    public void testTankDamage() {
        GameLogic logic = new GameLogic();
        Assert.assertEquals(FULL_HEALTH_TANK_VOLLEY_DAMAGE, logic.calculateTankDamage(), 0);
    }

    @Test
    public void testFortressHealthAfterFullHealthTankDamage() {
        GameLogic logic = new GameLogic();
        logic.updateFortressHealth();
        Assert.assertEquals(FORTRESS_DAMAGED_FIVE_FULL_HEALTH_TANKS, logic.getFortressHealth(), 0);
    }

    @Test
    public void testHitTankDamageReduced() {
        GameLogic logic = new GameLogic();
        List<Integer[]> tankLocations = logic.getTank(0).getLocation();
        for (Integer[] tankPiece : tankLocations) {
            logic.updateTankAndMap(tankPiece[0], tankPiece[1]);
        }
        logic.updateFortressHealth();
        Assert.assertEquals(1420, logic.getFortressHealth(), 0);
    }

    @Test
    public void testWinCondition() {
        GameLogic logic = new GameLogic();
        List<Tank> tanks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            tanks.add(logic.getTank(i));
        }
        for (Tank tank : tanks) {
            List<Integer[]> locations = tank.getLocation();
            for (Integer[] pieces : locations) {
                logic.updateTankAndMap(pieces[0], pieces[1]);
            }
        }
        Assert.assertEquals(WIN_CONDITION, logic.checkForWinLose(), 0);
    }

    @Test
    public void testLoseCondition() {
        GameLogic logic = new GameLogic();
        for (int i = 0; i < 15; i++) {
            Assert.assertEquals(0, logic.checkForWinLose(), 0);
            logic.updateFortressHealth();
        }
        Assert.assertEquals(LOSE_CONDITION, logic.checkForWinLose(), 0);
    }

    @Test
    public void testMapTankGeneration() {
        GameLogic logic = new GameLogic();
        int[][] board = logic.getMap();
        int tankPositionsFound = 0;
        for (int[] row : board) {
            for (int value : row) {
                if (value == 1) {
                    tankPositionsFound++;
                }
            }
        }
        Assert.assertEquals(20, tankPositionsFound, 0);
    }

    @Test
    public void testMapDestroyedTanks() {
        GameLogic logic = new GameLogic();
        List<Tank> tanks = new ArrayList<>();
        int[][] board = logic.getMap();
        int destroyedTankPieces = 0;
        for (int i = 0; i < 5; i++) {
            tanks.add(logic.getTank(i));
        }
        for (Tank tank : tanks) {
            List<Integer[]> locations = tank.getLocation();
            for (Integer[] pieces : locations) {
                logic.updateTankAndMap(pieces[0], pieces[1]);
            }
        }
        for (int[] row : board) {
            for (int value : row) {
                if (value == 3) {
                    destroyedTankPieces++;
                }
            }
        }
        Assert.assertEquals(20, destroyedTankPieces, 0);
    }

    @Test
    public void testMapOneDestroyedTank() {
        GameLogic logic = new GameLogic();
        int[][] board = logic.getMap();
        int destroyedTankPieces = 0;
        int functionalTankPieces = 0;
        List<Integer[]> tankLocations = logic.getTank(0).getLocation();
        for (Integer[] tankPiece : tankLocations) {
            logic.updateTankAndMap(tankPiece[0], tankPiece[1]);
        }
        for (int[] row : board) {
            for (int value : row) {
                if (value == 3) {
                    destroyedTankPieces++;
                } else if (value == 1) {
                    functionalTankPieces++;
                }
            }
        }
        Assert.assertEquals(4, destroyedTankPieces, 0);
        Assert.assertEquals(16, functionalTankPieces, 0);
    }
}