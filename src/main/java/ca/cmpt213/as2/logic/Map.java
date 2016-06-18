package ca.cmpt213.as2.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Map {
    private static final int UPPER_BOUND = 1000;
    private static final int LOWER_BOUND = 1;
    private static final int MAP_WIDTH = 10;
    private static final int MAP_HEIGHT = 10;
    private static final int TANK_SYMBOL = 1;
    private static final int TANK_LOCATIONS = 8;
    private static final int ARRAY_POSITION_SIZE = 2;

    private static int[][] board;
    private static List<Integer[]> possibleLocations;
    private static int rowPosition;
    private static int colPosition;


    public Map() {
        board = new int[MAP_HEIGHT][MAP_WIDTH];
        for (int[] row : board) {
            Arrays.fill(row, 0);
        }
    }

    public int[][] getMap() {
        return this.board;
    }

    private void printMap() {
        for (int[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }

    public int[] generateTankPosition() {
        possibleLocations = new ArrayList<>();
        int[] tankPosition = new int[TANK_LOCATIONS];
        int tankIndex = 0;
        boolean validLocation = false;
        rowPosition = 0;
        colPosition = 0;
        int[] startingPos = findTankStartingPosition();
        tankPosition[tankIndex] = startingPos[tankIndex];
        tankIndex++;
        tankPosition[tankIndex] = startingPos[tankIndex];
        tankIndex++;

        while (tankIndex < TANK_LOCATIONS) {
            validLocation = false;
            int index;
            Integer[] entry;
            generatePossibleLocations(rowPosition, colPosition);

            while (!validLocation) {
                //find a random number from ArrayList
                if (possibleLocations.size() == 0) {
                    //Starting Location didn't have enough space around it to create the tank
                    System.out.println(" \n\n\n******** MAKE RESET NOW ********\n\n\n");
                    resetIncompleteTank(tankPosition, tankIndex);
                    startingPos = findTankStartingPosition();
                    tankIndex = 0;
                    tankPosition[tankIndex] = startingPos[tankIndex];
                    tankIndex++;
                    tankPosition[tankIndex] = startingPos[tankIndex];
                    tankIndex++;
                    generatePossibleLocations(rowPosition, colPosition);
                    validLocation = true;
                }
                printLocationsFound();
                index = generateNumberBetween(LOWER_BOUND, UPPER_BOUND, possibleLocations.size());
                entry = possibleLocations.get(index);
                rowPosition = entry[0];
                colPosition = entry[1];
                if (board[rowPosition][colPosition] == 0) {
                    //Found Open Spot
                    board[rowPosition][colPosition] = TANK_SYMBOL;
                    tankPosition[tankIndex] = rowPosition;
                    tankIndex++;
                    tankPosition[tankIndex] = colPosition;
                    tankIndex++;
                    possibleLocations.remove(index);
                    validLocation = true;
                } else {
                    //Something already exists in the spot
                    possibleLocations.remove(index);
                }

            }
        }

        System.out.println("\t****\nPrinting tank positions:\n\t****");
        for (int i = 0; i < TANK_LOCATIONS; i++) {
            System.out.printf("row: %d col: %d\n", tankPosition[i], tankPosition[i+1]);
            i++;
        }
        System.out.println("\t****\n\t****");

        return tankPosition;
    }

    private int[] findTankStartingPosition() {

        boolean validLocation = false;
        int index = 0;
        int[] startingPosition = new int[ARRAY_POSITION_SIZE];

        while(!validLocation) {
            rowPosition = generateNumberBetween(LOWER_BOUND, UPPER_BOUND, MAP_HEIGHT);
            colPosition = generateNumberBetween(LOWER_BOUND, UPPER_BOUND, MAP_HEIGHT);
            if(board[rowPosition][colPosition] == 0) {
                System.out.println("Row: " + rowPosition + " Col: " + colPosition);
                board[rowPosition][colPosition] = TANK_SYMBOL;
                startingPosition[index] = rowPosition;
                index++;
                startingPosition[index] = colPosition;
                validLocation = true;
            }
        }

        return startingPosition;
    }

    private void resetIncompleteTank(int[] incompleteTank, int tankIndex) {
        int i = 0;
        int row;
        int col;
        while (i < tankIndex) {
            row = incompleteTank[i];
            col = incompleteTank[i+1];
            board[row][col] = 0;
            i++;
            i++;
        }
    }

    private void generatePossibleLocations(int row, int col) {
        Integer[] temp = new Integer[ARRAY_POSITION_SIZE];

        if (col == 0) {
            temp[0] = row;
            temp[1] = col + 1;
            possibleLocations.add(temp);
        } else if (col == board.length - 1) {
            temp[0] = row;
            temp[1] = col - 1;
            possibleLocations.add(temp);
        } else {
            temp[0] = row;
            temp[1] = col - 1;
            possibleLocations.add(temp);
            temp = new Integer[ARRAY_POSITION_SIZE]; // <-Why do I have to reset?
            temp[0] = row;
            temp[1] = col + 1;
            possibleLocations.add(temp);
        }

        temp = new Integer[ARRAY_POSITION_SIZE];
        if (row == 0) {
            temp[0] = row + 1;
            temp[1] = col;
            possibleLocations.add(temp);
        } else if ( row == board.length - 1) {
            temp[0] = row - 1;
            temp[1] = col;
            possibleLocations.add(temp);
        } else {
            temp[0] = row - 1;
            temp[1] = col;
            possibleLocations.add(temp);
            temp = new Integer[ARRAY_POSITION_SIZE];
            temp[0] = row + 1;
            temp[1] = col;
            possibleLocations.add(temp);
        }

    }

    private void printLocationsFound() {
        System.out.println("Possible Locations");
        for (Integer[] entry : possibleLocations) {
            for (Integer value : entry) {
                System.out.printf("%d ", value);
            }
            System.out.println();
        }
    }

    private int generateNumberBetween(int min, int max, int range) {
        return  (int) (Math.random() * max + min) % range;
    }

}
