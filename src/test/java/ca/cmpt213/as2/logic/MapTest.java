package ca.cmpt213.as2.logic;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class MapTest {
    private static final int[] initializedRow = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static final int oneTankPosition = 4;
    private static final int tenTankPosition = 40;
    private static final int fifteenTankPosition = 60;


    @Test
    public void testInitializedMap() {
        Map map = new Map();
        int[][] board = map.getMap();
        for (int[] row : board) {
            assertArrayEquals(row, initializedRow);
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
        Assert.assertEquals(oneTankPosition, tankPositionsFound, 0);
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
        Assert.assertEquals(tenTankPosition, tankPositionsFound, 0);
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
        Assert.assertEquals(fifteenTankPosition, tankPositionsFound, 0);
    }

}
