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

    public TextUi(GameLogic logic) {
        this.logic = logic;
    }

    public void runGame() {
        int startGame = startGame();
        if (startGame == PLAY) {
            getUserInput();
            displayMap();
        }
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

    public int startGame() {
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
            System.out.printf("menu choice: %d%n", menuChoice);
            startGameScanner();
        }
        scanner.nextLine();
        return menuChoice;
    }

    public Integer[] getUserInput() {
        Scanner scanner = new Scanner(System.in);
        char rowInput = 'a';
        int colInput = 0;
        boolean valid = false;
        Integer[] location = {0,0};
        System.out.println("Please enter a letter A-J: ");
        while (!valid) {
            try {
                //int col = ((int) scanner.next().toLowerCase().charAt(0)) - ASCII_A;
                rowInput = scanner.next().toLowerCase().charAt(0);
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a character!");
                scanner.nextLine();
            }
        }
        valid = false;
        System.out.println("Please enter an integer 1-10: ");
        while (!valid) {
            try {
                colInput = scanner.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer!");
                scanner.nextLine();
            }
        }
        int row = ((int) rowInput) - ASCII_A;
        int col = colInput - 1; //subtract 1 to make row 0 indexed
        if(col >= 0 && col < 10  && row >= 0 && row < 10) {
            location[0] = row;
            location[1] = col;
        } else {
            System.out.println("Invalid input. Letter must be between A-J and the integer must be between 1-10. ");
            getUserInput();
        }
        return location;
    }
}