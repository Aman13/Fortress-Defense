package ca.cmpt213.as2.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Map {
    private static final int UPPER_BOUND = 1000;
    private static final int LOWER_BOUND = 1;
    private static final int MAP_WIDTH = 10;
    private static final int MAP_HEIGHT = 10;
    private static final int TANK_PIECES_REQUIRED = 4;
    private static final int ARRAY_POSITION_SIZE = 2;
    private static final int ROW_INDEX = 0;
    private static final int COL_INDEX = 1;
    private static final int TANK_SYMBOL = 1;
    private static final int MISS_SYMBOL = 2;
    private static final int HIT_SYMBOL = 3;

    private static List<Integer[]> possibleLocations;
    private static int rowPosition;
    private static int colPosition;

    private int[][] board;


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

    public List<Integer[]> generateTankPosition() {
        possibleLocations = new ArrayList<>();
        Integer[] tankPiecePosition;
        List<Integer[]> tankPieces = new ArrayList<>();
        boolean validLocation;
        rowPosition = 0;
        colPosition = 0;
        tankPiecePosition = findTankStartingPosition();
        tankPieces.add(tankPiecePosition);
        tankPiecePosition = new Integer[ARRAY_POSITION_SIZE];

        while (tankPieces.size() != TANK_PIECES_REQUIRED) {
            validLocation = false;
            int index;
            Integer[] entry;
            generatePossibleLocations(rowPosition, colPosition);

            while (!validLocation) {
                //find a random number from ArrayList
                if (possibleLocations.size() == 0) {
                    //Starting Location didn't have enough space around it to create the tank
                    resetIncompleteTank(tankPieces);
                    tankPiecePosition = findTankStartingPosition();
                    tankPieces.add(tankPiecePosition);
                    tankPiecePosition = new Integer[ARRAY_POSITION_SIZE];
                    generatePossibleLocations(rowPosition, colPosition);
                    validLocation = true;
                }
                index = generateNumberBetween(LOWER_BOUND, UPPER_BOUND, possibleLocations.size());
                entry = possibleLocations.get(index);
                rowPosition = entry[0];
                colPosition = entry[1];
                if (board[rowPosition][colPosition] == 0) {
                    //Found Open Spot
                    board[rowPosition][colPosition] = TANK_SYMBOL;
                    tankPiecePosition[ROW_INDEX] = rowPosition;
                    tankPiecePosition[COL_INDEX] = colPosition;
                    tankPieces.add(tankPiecePosition);
                    tankPiecePosition = new Integer[ARRAY_POSITION_SIZE];
                    generatePossibleLocations(rowPosition, colPosition);
                    possibleLocations.remove(index);
                    validLocation = true;
                } else {
                    //Something already exists in the spot
                    possibleLocations.remove(index);
                }

            }
        }
        return tankPieces;
    }

    private Integer[] findTankStartingPosition() {
        boolean validLocation = false;
        Integer[] startingPosition = new Integer[ARRAY_POSITION_SIZE];

        while(!validLocation) {
            rowPosition = generateNumberBetween(LOWER_BOUND, UPPER_BOUND, MAP_HEIGHT);
            colPosition = generateNumberBetween(LOWER_BOUND, UPPER_BOUND, MAP_HEIGHT);
            if(board[rowPosition][colPosition] == 0) {
                board[rowPosition][colPosition] = TANK_SYMBOL;
                startingPosition[ROW_INDEX] = rowPosition;
                startingPosition[COL_INDEX] = colPosition;
                validLocation = true;
            }
        }
        return startingPosition;
    }

    private void resetIncompleteTank(List<Integer[]> incompleteTank) {
        Integer[] currentPiece;

        for (int i = 0; i < incompleteTank.size(); i++) {
            currentPiece = incompleteTank.get(i);
            board[currentPiece[ROW_INDEX]][currentPiece[COL_INDEX]] = 0;
        }
        incompleteTank.clear();
    }

    private void generatePossibleLocations(int row, int col) {
        Integer[] locationHolder = new Integer[ARRAY_POSITION_SIZE];

        if (col == 0) {
            locationHolder[0] = row;
            locationHolder[1] = col + 1;
            possibleLocations.add(locationHolder);
        } else if (col == board.length - 1) {
            locationHolder[0] = row;
            locationHolder[1] = col - 1;
            possibleLocations.add(locationHolder);
        } else {
            locationHolder[0] = row;
            locationHolder[1] = col - 1;
            possibleLocations.add(locationHolder);
            locationHolder = new Integer[ARRAY_POSITION_SIZE];
            locationHolder[0] = row;
            locationHolder[1] = col + 1;
            possibleLocations.add(locationHolder);
        }
        locationHolder = new Integer[ARRAY_POSITION_SIZE];

        if (row == 0) {
            locationHolder[0] = row + 1;
            locationHolder[1] = col;
            possibleLocations.add(locationHolder);
        } else if ( row == board.length - 1) {
            locationHolder[0] = row - 1;
            locationHolder[1] = col;
            possibleLocations.add(locationHolder);
        } else {
            locationHolder[0] = row - 1;
            locationHolder[1] = col;
            possibleLocations.add(locationHolder);
            locationHolder = new Integer[ARRAY_POSITION_SIZE];
            locationHolder[0] = row + 1;
            locationHolder[1] = col;
            possibleLocations.add(locationHolder);
        }

    }

    private int generateNumberBetween(int min, int max, int range) {
        return  (int) (Math.random() * max + min) % range;
    }

    public boolean checkForHit(int row, int col) {
        if (board[row][col] == TANK_SYMBOL) {
            board[row][col] = HIT_SYMBOL;
            return true;
        } else if (board[row][col] == HIT_SYMBOL) {
            //keep as hit symbol
            return false;
        } else {
            board[row][col] = MISS_SYMBOL;
            return false;
        }
    }

}
