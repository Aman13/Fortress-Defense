package ca.cmpt213.as2.ui;

import ca.cmpt213.as2.logic.GameLogic;

import java.util.InputMismatchException;
import java.util.Scanner;


public class TextUi {
    private static GameLogic logic;
    private static final int MAP_TANK = 1;
    private static final int MAP_MISS = 2;
    private static final int MAP_HIT = 3;
    private static final int ASCII_A = 97;
    private static final int PLAY = 1;
    private static final int EXIT = 2;
    private static final int ROW_INDEX = 0;
    private static final int COL_INDEX = 1;
    private static final int NUMBER_OF_TANKS = 5;

    public TextUi(GameLogic logic) {
        this.logic = logic;
    }

    public void runGame() {
        int startGame = startGame();
        if (startGame == PLAY) {
            logic.initializeGame();
            gameDriver();
        }
    }

    private void gameDriver() {
        Integer[] locations;
        while(logic.checkForWinLose() == 0) {
            displayMap();
            System.out.println("Fortress Structure Left: " + logic.getFortressHealth());
            locations = getUserInput();
            boolean hit = logic.updateTankAndMap(locations[ROW_INDEX], locations[COL_INDEX]);
            if (hit) {
                System.out.printf("%nA tank was hit!%n");
            } else {
                System.out.printf("%nYou missed!%n");
            }
            logic.updateFortressHealth();
            for (int i = 0; i < NUMBER_OF_TANKS; i ++) {
                System.out.println("You were shot for " + logic.getTankDamage(i));
            }
        }
        if (logic.checkForWinLose() == -1) {
            System.out.printf("%nGame over. You lost :(%n%n");
            displayFinalMap();
            runGame();
        } else if (logic.checkForWinLose() == 1) {
            System.out.printf("%nYou won!%n%n");
            runGame();
        }

    }

    private void displayMap() {
        int[][] map = logic.getMap();
        System.out.printf("%nGame Board: %n");
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

    private void displayNumberLegend() {
        System.out.printf("    ");
        for (int i = 0; i < logic.getMap().length; i ++) {
            System.out.printf(" %d  ", i+1);
        }
        System.out.println();
    }

    private void mapSymbolConverter(int entry) {
        if (entry == 0) {
            System.out.printf(" ~  ");
        } else if (entry == MAP_TANK) {
            System.out.printf(" ~  ");
        } else if (entry == MAP_MISS) {
            System.out.printf(" .  ");
        } else if (entry == MAP_HIT) {
            System.out.printf(" X  ");
        }
    }

    private int startGame() {
        int BUFFER = 5;
        String title = "Fortress Defense";
        String starBorder = "";
        for (int i = 0; i < title.length() + BUFFER; i++) {
            starBorder += "*";
        }
        System.out.println(starBorder);
        System.out.println("* " + title + " *");
        System.out.println(starBorder);
        return startGameScanner();
    }

    private int startGameScanner() {
        Scanner scanner = new Scanner(System.in);
        int menuChoice = 0;
        boolean valid = false;
        System.out.println("Enter 1 to play game, 2 to exit: ");
        while (!valid) {
            try {
                menuChoice = scanner.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer!");
                scanner.nextLine();
            }
        }
        if (menuChoice != PLAY && menuChoice != EXIT) {
            System.out.printf("Menu choice: %d%n", menuChoice);
            startGameScanner();
        }
        scanner.nextLine();
        return menuChoice;
    }

    private Integer[] getUserInput() {
        Scanner scanner = new Scanner(System.in);
        char rowInput = 'a';
        int colInput = 0;
        boolean valid = false;
        Integer[] location = {0,0};
        System.out.printf("%nEnter your move in the form <letter><number>: %n");
        while (!valid) {
            String input = scanner.next();
            try {
                rowInput = input.toLowerCase().charAt(0);
                colInput = Integer.parseInt(input.substring(1));
                valid = true;
            } catch (Exception e) {
                System.out.println("Invalid input. Letter must be between A-J and the integer must be between 1-10. Ex b4 ");
                scanner.nextLine();
            }
        }
        int row = ((int) rowInput) - ASCII_A;
        int col = colInput - 1; //subtract 1 to make row 0 indexed
        if (col >= 0 && col < 10  && row >= 0 && row < 10) {
            location[0] = row;
            location[1] = col;
        } else {
            System.out.println("Invalid input. Letter must be between A-J and the integer must be between 1-10. Ex b4 ");
            location = getUserInput();
        }
        return location;
    }

    private void displayFinalMap() {
        int[][] map = logic.getMap();
        System.out.printf("%nRevealed Board: %n");
        displayNumberLegend();
        char letterLegend = 'A';
        for (int[] row : map) {
            System.out.printf("  %c ", letterLegend);
            for (int entry : row) {
                mapSymbolConverterFinal(entry);
            }
            System.out.println();
            letterLegend++;
        }
    }

    private void mapSymbolConverterFinal(int entry) {
        if (entry == 0) {
            System.out.printf(" ~  ");
        } else if (entry == MAP_TANK) {
            System.out.printf(" O  ");
        } else if (entry == MAP_MISS) {
            System.out.printf(" .  ");
        } else if (entry == MAP_HIT) {
            System.out.printf(" X  ");
        }
    }
}
