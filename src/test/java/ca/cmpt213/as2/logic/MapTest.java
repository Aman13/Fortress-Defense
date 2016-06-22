package ca.cmpt213.as2.logic;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class MapTest {
    private static final int[] INITIALIZED_ROW = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static final int ONE_TANK_POSITION_COUNT = 4;
    private static final int TEN_TANK_POSITION_COUNT = 40;
    private static final int FIFTEEN_TANK_POSITION_COUNT = 60;


    @Test
    public void testInitializedMap() {
        Map map = new Map();
        int[][] board = map.getMap();
        for (int[] row : board) {
            assertArrayEquals(INITIALIZED_ROW, row);
        }
    }

    @Test
    public void testFoundValidTankPositions() {
        int tankPositionsFound = 0;
        Map map = new Map();
        map.generateTankPosition();
        int[][] board = map.getMap();
        for (int[] row : board) {
            for (int value : row) {
                if (value == 1) {
                    tankPositionsFound++;
                }
            }
        }
        Assert.assertEquals(ONE_TANK_POSITION_COUNT, tankPositionsFound, 0);
    }

    @Test
    public void testFoundValidLocationForTenTanks() {
        int tankPositionsFound = 0;
        Map map = new Map();
        for (int i = 0; i < 10; i++) {
            map.generateTankPosition();
        }
        int[][] board = map.getMap();
        for (int[] row : board) {
            for (int value: row) {
                if (value == 1) {
                    tankPositionsFound++;
                }
            }
        }
        Assert.assertEquals(TEN_TANK_POSITION_COUNT, tankPositionsFound, 0);
    }

    @Test
    public void testFoundValidLocationForFifteenTanks() {
        int tankPositionsFound = 0;
        Map map = new Map();
        for (int i = 0; i < 15; i++) {
            map.generateTankPosition();
        }
        int[][] board = map.getMap();
        for (int[] row : board) {
            for (int value: row) {
                if (value == 1) {
                    tankPositionsFound++;
                }
            }
        }
        Assert.assertEquals(FIFTEEN_TANK_POSITION_COUNT, tankPositionsFound, 0);
    }

}
