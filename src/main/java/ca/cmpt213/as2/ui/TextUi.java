package ca.cmpt213.as2.ui;

import ca.cmpt213.as2.logic.GameLogic;

import java.util.Arrays;


public class TextUi {
    private static GameLogic logic;
    private static final int MAP_TANK = 1;
    private static final int MAP_MISS = 2;
    private static final int MAP_HIT = 3;

    public TextUi(GameLogic logic) {
        this.logic = logic;
    }

    public void runGame() {
        areWeAlive();
        displayMap();
    }

    private void areWeAlive() {
        logic.printTestMap();
    }

    private void displayMap() {
        int[][] map = logic.getMap();
        System.out.println("Game Board: ");
        displayNumberLegend();
        char letterLegend = 'A';
        for (int[] row : map) {
            System.out.printf("  %c ", letterLegend);
            for (int entry : row) {
                mapSymbolConverter(entry);
            }
            System.out.println();
            letterLegend++;
        }
    }

    private void mapSymbolConverter(int entry) {
        if (entry == 0) {
            System.out.printf(" ~  ");
        } else if (entry == MAP_TANK) {
            System.out.printf(" !  ");
        } else if (entry == MAP_MISS) {
            System.out.printf(" .  ");
        } else if (entry == MAP_HIT) {
            System.out.printf(" X  ");
        }
    }

    private void displayNumberLegend() {
        System.out.printf("    ");
        for (int i = 0; i < logic.getMap().length; i ++) {
            System.out.printf(" %d  ", i+1);
        }
        System.out.println();
    }
}
