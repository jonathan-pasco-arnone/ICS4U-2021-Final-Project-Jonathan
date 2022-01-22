/*
* This is the Battleship program.
*
* @author  Jonathan Pasco-Arnone
* @version 1.0
* @since   2021-12-20
*/

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
* This program displays what the battleships will look like on a grid.
*/
final class Battleship {

    /*
    * You may customize the following 6 constants to your hearts content
    */
    /**
    * Number of ships that take up 4 squares - default = 1.
    */
    private static final int NUMFOURS = 0;
    /**
    * Number of ships that take up 3 squares - defualt = 3.
    */
    private static final int NUMTHREES = 0;
    /**
    * Number of ships that take up 2 squares - default = 2.
    */
    private static final int NUMTWOS = 0;
    /**
    * Number of ships that take up 1 squares - default = 2.
    */
    private static final int NUMONES = 1;
    /**
    * The amount of rows in the grid - defualt = 10.
    */
    private static final int NUMROWS = 10;
    /**
    * The amount of columns in the grid - defualt = 10 - max = 26.
    */
    private static final int NUMCOLS = 10;

    /**
    * All of the ship quantities in an array.
    */
    private static ArrayList<Integer> allShipQuantities =
        new ArrayList<Integer>();

    /**
    * The amount of ships * 2 (because player and enemy).
    * Exclusively for defineing coordinate holding array.
    */
    private static final int QUANTITYTYPESOFSHIPS = 8;
    /**
    * A 3D array list holding all of the coordinates for all
    * the ships ever generated for the player.
    */
    private static Fleet allShips = new Fleet();
    /**
    * A 3D array list holding all of the coordinates for all
    * the ships ever generated for the enemy.
    */
    private static Fleet enemyAllShips = new Fleet();

    /**
    * Four as a constant.
    */
    private static final int FOUR = 4;
    /**
    * Three as a constant.
    */
    private static final int THREE = 3;
    /**
    * Two as a constant.
    */
    private static final int TWO = 2;
    /**
    * One as a constant.
    */
    private static final int ONE = 1;

    /**
    * The unicode for a blank square.
    * Used as the "water" on the grid.
    */
    private static String blank = "\u2588";

    /**
    * String that tells the player it is their grid.
    */
    private static String yourGridStr = "=====================\n"
        + "------Your Grid------\n=====================\n";
    /**
    * String that tells the player that it is the enemies grid.
    */
    private static String enemyGridStr = "\n\n\n=====================\n"
        + "------Enemy Grid-----\n=====================\n";

    /**
    * Unicode for changing text color to blue.
    * Used for the "water".
    */
    private static String blue = "\033[0;34m";
    /**
    * Unicode for changing text color to red.
    * Used for hit or sunk.
    */
    private static String red = "\033[0;31m";
    /**
    * Unicode for changing text color to yellow.
    * Used for miss.
    */
    private static String yellow = "\033[0;33m";
    /**
    * Unicode for changing text color to a bold version of magenta.
    * Used for end of game.
    */
    private static String magentaBold = "\033[1;35m";
    /**
    * Unicode for changing text color to a bold version of white.
    * Used for end of game.
    */
    private static String whiteBold = "\033[1;37m";
    /**
    * Unicode for changing text background to cyan.
    * Used for end of game.
    */
    private static String cyanBackground = "\033[46m";
    /**
    * Unicode for changing text background to yellow.
    */
    private static String yellowBackground = "\033[43m";
    /**
    * Unicode for changing text background to magenta.
    */
    private static String magentaBackground = "\033[45m";
    /**
    * Unicode for changing text background to white.
    */
    private static String whiteBackground = "\033[47m";
    /**
    * Unicode for changing text color and background color back
    * to its original form.
    */
    private static String reset = "\033[0m";

    /**
    * String for grid to represent hit.
    */
    private static String hit = "H";
    /**
    * String for grid to represent miss.
    */
    private static String miss = "M";
    /**
    * String for grid to represent sunk.
    */
    private static String sunk = "S";
    /**
    * String for four.
    */
    private static String fourStr = "4";
    /**
    * String for three.
    */
    private static String threeStr = "3";
    /**
    * String for two.
    */
    private static String twoStr = "2";
    /**
    * String for one.
    */
    private static String oneStr = "1";
    /**
    * String for player win.
    */
    private static String playerWinStr = "player win";
    /**
    * String for enemy win.
    */
    private static String enemyWinStr = "enemy win";
    /**
    * String for tie.
    */
    private static String tieStr = "tie";
    /**
    * String for fail.
    * Used for checking if ship generation failed.
    */
    private static String failStr = "fail";

    /**
    * Char array of the whole alphabet in capital and lower case.
    */
    private static char[] alphabet = ("ABCDEFGHIJKL"
        + "MNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz").toCharArray();
    /**
    * The amount of capital letters.
    */
    private static final int CAPITALLETTERS = 26;
    /**
    * The maximum integer value a char can represent.
    */
    private static final int MAXCHARVALUE = 9;
    /**
    * The amount of time in a one second pause - defualt = 1000.
    */
    private static final int ONESECOND = 1;

    /**
    * One space.
    */
    private static String oneSpace = " ";
    /**
    * Two spaces.
    */
    private static String twoSpaces = "  ";

    /**
    * Class holding all pixel art.
    */
    private static PixelArt artPrinter = new PixelArt();

    /**
    * Prevent instantiation
    * Throw an exception IllegalStateException.
    * if this ever is called
    *
    * @throws IllegalStateException
    *
    */
    private Battleship() {
        throw new IllegalStateException("Cannot be instantiated");
    }

    /**
    * Determines if the player's input is valid.
    *
    * @param enemyGrid the enemies grid
    * @param rowCoord the row coordinate
    * @param columnCoord the column coordinate
    * @throws java.util.InputMismatchException throws the error
    *     of invalid input
    */
    static void playerTurnErrorChecking(
        final ArrayList<ArrayList<String>> enemyGrid, final int rowCoord,
        final int columnCoord) {

        // If the row is out of bounds or is never assigned
        if (rowCoord > (NUMROWS - 1) || rowCoord < 0) {
            System.out.println("\nPlease input from the rows"
                + " available.\n\n");
            throw new java.util.InputMismatchException();

        }

        // If the column is never assigned a value or it is out of bounds
        if (columnCoord < 0 || columnCoord >= NUMCOLS) {
            System.out.println("\nPlease input from the "
                + "columns available.\n\n");
            throw new java.util.InputMismatchException();
        }

        // If the selected location has already been attacked
        if (enemyAllShips.getHit(rowCoord, columnCoord)
            || enemyAllShips.checkSunk(rowCoord, columnCoord)
            || enemyGrid.get(rowCoord).get(columnCoord) == miss) {
            System.out.println("\nYou have already attacked this area\n\n");
            throw new java.util.InputMismatchException();

        // If the selected place is blank
        } else if (enemyGrid.get(rowCoord).get(columnCoord) == blank) {
            System.out.print(yellow);
            System.out.println("\nMISS\n\n");
            System.out.print(reset);
            enemyGrid.get(rowCoord).remove(columnCoord);
            enemyGrid.get(rowCoord).add(columnCoord, miss);

        // If the selected place is a ship
        } else if (enemyAllShips.getShipSize(rowCoord, columnCoord) > 0) {

            System.out.print(red);
            System.out.println("\nHIT\n\n");
            System.out.print(reset);
            enemyAllShips.replace(rowCoord, columnCoord);
            
        // If the location selected is not on the grid
        } else {
            System.out.println("\nYou must select a loctaion"
                + " within the grid\n\n");
            throw new java.util.InputMismatchException();
        }
    }

    /**
    * Tells the player the rules and how to play the game.
    */
    static void rulesAndStart() {
        // Printing all the rules
        System.out.println("Welcome to Battleship.\n\n"
            + "This game is very similar to the popularly known board game "
            + "of battleship.\nThe rules are as follows:\n    1. Every turn,"
            + " you must select a coordinate to attack the opponent on their "
            + "grid. If the attack is a hit, miss, or sink, then the location"
            + " will be  labelled\n       \"Enemy Grid\" will display that.\n"
            + "    2. After your turn, the enemy will select a random "
            + "coordinate to attack, and it will be displayed on your"
            + " grid in the same way as your attacks display in theirs.\n"
            + "    3. The first person to sink all the opposing person's"
            + " ships is the winner\n    4. Have Fun! ;)");

        System.out.println("\nPress enter to begin\n");

        // Scanner to recieve input
        final Scanner start = new Scanner(System.in);
        final String startGame = start.nextLine();
    }

    /**
    * Sets up some of the arrays that will be used in the program.
    */
    static void setUpArrays() {
        // Adds all the ship quantities to this array
        allShipQuantities.add(NUMONES);
        allShipQuantities.add(NUMTWOS);
        allShipQuantities.add(NUMTHREES);
        allShipQuantities.add(NUMFOURS);

    }

    /**
    * Determines if someone has won.
    *
    * @param playerGrid the players grid
    * @param enemyGrid the enemies grid
    * @return returns the result of the game
    */
    static String gameResult(final ArrayList<ArrayList<String>> playerGrid,
                            final ArrayList<ArrayList<String>> enemyGrid) {

        final String returnString;
        boolean playerHasShips = false;
        boolean enemyHasShips = false;

        // Determines if the player or the enemy still have ships
        for (int row = 0; row < NUMROWS; row++) {
            for (int column = 0; column < NUMCOLS; column++) {
                // If the coordinate is a ship
                if (allShips.getShipSize(row, column) > 0) {
                    // If the ship is NOT sunk
                    if (!allShips.checkSunk(row, column)) {
                        playerHasShips = true;
                    }
                }
                
                // If the coordinate is a ship
                if (enemyAllShips.getShipSize(row, column) > 0) {
                    // If the ship is NOT sunk
                    if (!enemyAllShips.checkSunk(row, column)) {
                        enemyHasShips = true;
                    }
                }
            }
        }

        // Checks what the current outcome of the match is
        // If they both have ships then continue
        if (playerHasShips && enemyHasShips) {
            returnString = "continue";
        // If the player has ships and the enemy does not
        } else if (playerHasShips && !enemyHasShips) {
            returnString = playerWinStr;
        // If the enemy has ships and the player does not
        } else if (!playerHasShips && enemyHasShips) {
            returnString = enemyWinStr;
        // If neither of them has ships
        } else {
            returnString = tieStr;
        }

        return returnString;
    }

    /**
    * Executes the enemies turn.
    *
    * @param playerGrid the enemies grid
    */
    public static void enemyTurn(
        final ArrayList<ArrayList<String>> playerGrid) {

        // Will keep cycling through until a valid coordinate is chosen
        do {

            final Random randRow = new Random();
            final Random randCol = new Random();

            // Picks a random column that is not higher tan the total columns
            final int rowCoord = randRow.nextInt(NUMROWS);
            // Picks a random row that is not higher than the total rows
            final int columnCoord = randCol.nextInt(NUMCOLS);

            /*
            * Determines the alphabetical value of the random
            * horizontal coordinate
            */
            final String horizontalCoordStr =
                Character.toString(alphabet[columnCoord]);

            // If the selected place is blank
            if (playerGrid.get(rowCoord).get(columnCoord) == blank) {

                // Miss
                playerGrid.get(rowCoord).remove(columnCoord);
                playerGrid.get(rowCoord).add(columnCoord, miss);

                // Sets the color to bold white
                System.out.print(whiteBold);

                System.out.println("The computer chose ("
                    + rowCoord + "," + horizontalCoordStr + ") ");

                // Resets the color
                System.out.print(reset);
                break;

            // If the selected place is a ship
            } else if (allShips.getShipSize(rowCoord, columnCoord) > 0) {

                // Hit
                allShips.setHit(rowCoord, columnCoord);

                // Sets the color to bold white
                System.out.print(whiteBold);

                System.out.println("The computer chose ("
                    + rowCoord + "," + horizontalCoordStr + ") ");

                // Resets the color
                System.out.print(reset);
                break;
            }
            /*
            * If it is not blank or a ship then it must be a hit or miss
            * in which case, the while loop will force it to select a
            * new location.
            */

        } while (true);
    }

    /**
    * This gets the inputs from the user.
    *
    * @param enemyGrid the enemies grid
    * @throws java.util.InputMismatchException if the input is incorrect
    */
    public static void playerTurn(
        final ArrayList<ArrayList<String>> enemyGrid) {

        // Scanners to recieve inputs
        final Scanner inputs = new Scanner(System.in);

        System.out.println("\n\nChose a place to shoot");

        System.out.println("\nSelect coordinates to attack");
        final String bothCoords = inputs.nextLine();

        String rowCoordStr = "";
        String columnCoordStr = "";

        int rowCoord = -1;
        int columnCoord = -1;

        // Will go through every element in the input
        for (int index = 0; index < bothCoords.length(); index++) {
            // Checks every letter
            for (int letter = 0; letter < alphabet.length; letter++) {

                /*
                * If the current index of the inputted string is equal
                * to the current alphabetical value
                */
                if (String.valueOf(bothCoords.charAt(index)).equals(
                    Character.toString(alphabet[letter]))) {

                    // Assigns the index of the alphabet to the column value
                    columnCoordStr += Integer.toString(letter);
                }
            }

            // Checks from number 0 to 9
            for (int row = 0; row <= MAXCHARVALUE; row++) {

                /*
                * Checks if the current index is the current row
                */
                if (String.valueOf(bothCoords.charAt(index)).equals(
                    Integer.toString(row))) {

                    /*
                    * Will "add" the current index to the row coord.
                    * This will allow inputs like "12D" to be 12 as
                    * apose to adding the 1 and 2 together
                    */
                    rowCoordStr += bothCoords.charAt(index);
                }
            }
        }

        // If the column coord is valid
        if (columnCoordStr.length() >= 1) {
            /*
            * If the column coord was a lowercase letter
            * (aka. later in the alphabet array)
            */
            if (Integer.parseInt(columnCoordStr) >= CAPITALLETTERS) {
                columnCoord = (Integer.parseInt(columnCoordStr))
                    - CAPITALLETTERS;
            // If the column coord was a capital letter
            } else {
                columnCoord = Integer.parseInt(columnCoordStr);
            }
        // If the column coord is invalid
        } else {
            System.out.println("\nPlease input a column value.\n\n");
            throw new java.util.InputMismatchException();
        }

        // If the row coord is valid
        if (rowCoordStr.length() >= 1) {
            rowCoord = Integer.parseInt(rowCoordStr);
        // If the column coord is invalid
        } else {
            System.out.println("\nPlease input a rows value.\n\n");
            throw new java.util.InputMismatchException();
        }

        playerTurnErrorChecking(enemyGrid, rowCoord, columnCoord);

        System.out.println("You chose (" + rowCoord + ", "
            + alphabet[columnCoord] + ")");

        try {
            /*
            * Waits 1 second so the
            * player can see if you hit or missed.
            */
            Thread.currentThread().sleep(ONESECOND);
        } catch (InterruptedException ex) {
            System.out.print("");
        }
    }

    /**
    * Gets a letter from an integer.
    *
    * @param number the number to be used
    * @return the letter to be returned
    */
    public static String getLetter(final int number) {

        String returnValue = "";

        /*
        * The inputted number will be from 0-25. If not then it cannot
        * be assigned an alphabetical value, so it will return nothing.
        */
        if (number >= CAPITALLETTERS) {
            returnValue = "";
        } else {
            returnValue = Character.toString(alphabet[number]);
        }

        return returnValue;
    }

    /**
    * The heart of the game, where both players take their turn.
    *
    * @param playerGrid the players grid
    * @param enemyGrid the enemies grid
    */
    static void playGame(final ArrayList<ArrayList<String>> playerGrid,
                         final ArrayList<ArrayList<String>> enemyGrid) {

        boolean gameOn = true;

        do {
            try {

                /*
                * This function (as with all java functions) uses pointers,
                * which allows it to change the enemies grid
                * without having to return the arrays.
                */
                playerTurn(enemyGrid);

                /*
                * This function (as with all java functions) uses pointers,
                * which allows it to change the player grid
                * without having to return the arrays.
                */
                enemyTurn(playerGrid);

                /*
                * Waits 2 second so the
                * player can see what the enemy has done
                */
                Thread.currentThread().sleep(ONESECOND * TWO);

                final String currentResult =
                    gameResult(playerGrid, enemyGrid);

                /*
                * Checks a function to see if the game is over.
                * If so, who wins? If not, then continue.
                */
                if (currentResult.equals(playerWinStr)) {
                    gameOn = false;

                    // Prints pixel art of "You Win"
                    artPrinter.pixelArt("you win", magentaBackground,
                        whiteBackground);

                    System.out.println();

                    break;
                } else if (currentResult.equals(enemyWinStr)) {
                    gameOn = false;

                    // Soon to be you lose
                    artPrinter.pixelArt("you lose", magentaBackground,
                        whiteBackground);

                    System.out.println();

                    break;
                } else if (currentResult.equals(tieStr)) {
                    gameOn = false;

                    System.out.println("\n\n");
                    System.out.print(magentaBold);
                    System.out.print(cyanBackground);
                    System.out.print("You tie.");
                    System.out.print(reset);
                    System.out.println();

                    break;
                }

                System.out.println(yourGridStr);
                printGrid(playerGrid);

                /*
                * Waits 1 second so the
                * player can see their grid.
                */
                Thread.currentThread().sleep(ONESECOND);

                System.out.println(enemyGridStr);
                printEnemyGrid(enemyGrid);

            } catch (java.util.InputMismatchException ex) {
                System.out.print("");
            } catch (InterruptedException ex) {
                System.out.print("");
            }

        } while (gameOn);
    }

    /**
    * Prints the enemies grid from the players point of view.
    *
    * @param grid the grid
    */
    static void printEnemyGrid(final ArrayList<ArrayList<String>> grid) {
        // Prints the letters accross the top
        System.out.print(twoSpaces);
        for (int column = 0; column < NUMCOLS; column++) {
            System.out.print(getLetter(column) + oneSpace);
        }
        System.out.println("");

        for (int row = 0; row < NUMROWS; row++) {
            // Resets the color
            System.out.print(reset);
            System.out.print(row + oneSpace);

            /*
            * Determines how many spaces to leave between the row
            * value and the grid
            */
            for (int counter = 0; counter < (String.valueOf(NUMROWS
                - 1).length() - String.valueOf(row).length()); counter++) {

                System.out.print(oneSpace);
            }

            for (int column = 0; column < NUMCOLS; column++) {
                String printText = "";
                
                // If the current spot is sunk
                if (enemyAllShips.checkSunk(row, column)) {
                    printText = sunk;
                    // Sets the color to yellow
                    System.out.print(red);

                // If the current spot is a hit
                } else if (enemyAllShips.getHit(row, column)) {
                    printText = hit;
                    // Sets the color to red
                    System.out.print(red);
                    
                // If the current spot is a miss
                } else if (grid.get(row).get(column).equals(miss)) {
                    printText = miss;
                    // Sets the color to yellow
                    System.out.print(yellow);
                // If the location has not been attacked yet
                } else {
                    printText = blank;
                    // Sets the color to blue
                    System.out.print(blue);
                // If there is a ship but has not been hit or sunk yet
                }
                System.out.print(printText + oneSpace);
            }
            System.out.println("");
        }
        // Resets the color
        System.out.print(reset);

    }

    /**
    * Prints the grid.
    *
    * @param grid the grid
    */
    static void printGrid(final ArrayList<ArrayList<String>> grid) {

        // Prints the letters accross the top
        System.out.print(twoSpaces);
        for (int column = 0; column < NUMCOLS; column++) {
            System.out.print(getLetter(column) + oneSpace);
        }
        System.out.println("");

        for (int row = 0; row < NUMROWS; row++) {
            // Resets the color
            System.out.print(reset);
            // Prints all the letters across the side
            System.out.print(row + oneSpace);

            /*
            * Determines how many spaces to leave between the row
            * value and the grid
            */
            for (int counter = 0; counter < (String.valueOf(
                NUMROWS - 1).length() - String.valueOf(row).length());
                counter++) {

                System.out.print(oneSpace);
            }

            // Prints the main contents of the grid
            for (int column = 0; column < NUMCOLS; column++) {
                String printText = "";
                
                // If the current spot is sunk
                if (allShips.checkSunk(row, column)) {
                    printText = sunk;
                    // Sets the color to yellow
                    System.out.print(red);

                // If the current spot is a hit
                } else if (allShips.getHit(row, column)) {
                    printText = hit;
                    // Sets the color to red
                    System.out.print(red);
                    
                // If the current spot is a miss
                } else if (grid.get(row).get(column).equals(miss)) {
                    printText = miss;
                    // Sets the color to yellow
                    System.out.print(yellow);
                // If there is no ship there
                } else if (allShips.getShipSize(row, column) < 1) {
                    printText = blank;
                    // Sets the color to blue
                    System.out.print(blue);
                // If there is a ship but has not been hit or sunk yet
                } else {                    
                    // Sets the print text to the the ship size (4, 3, 2, or 1)
                    printText = Integer.toString(allShips.getShipSize(
                        row, column));
                    // Resets the color
                    System.out.print(reset);
                }
                System.out.print(printText + oneSpace);

                //System.out.print(printText + oneSpace);
            }
            System.out.print("\n");
        }
        // Resets the color
        System.out.print(reset);
    }

    /**
    * Checks if horizontal is valid.
    *
    * @param initialGrid the grid
    * @param shipSize how large the ship is
    * @return returns whether it is true or false
    */
    static boolean isHorizontalnValid(
        final ArrayList<ArrayList<String>> initialGrid, final int shipSize) {

        boolean returnValue = false;

        // Checks all the rows
        for (int row = 0; row < NUMROWS; row++) {
            int count = 0;

            // Checks all the columns
            for (int column = 0; column < NUMCOLS; column++) {
                if (initialGrid.get(row).get(column) == blank) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == shipSize) {
                    returnValue = true;
                }
            }
        }
        return returnValue;
    }

    /**
    * Checks if vertical is valid.
    *
    * @param initialGrid the grid
    * @param shipSize how large the ship is
    * @return returns whether it is true or false
    */
    static boolean isVerticalValid(
        final ArrayList<ArrayList<String>> initialGrid, final int shipSize) {

        boolean returnValue = false;

        // Checks all the rows
        for (int row = 0; row < NUMROWS; row++) {
            int count = 0;

            // Checks all the columns
            for (int column = 0; column < NUMCOLS; column++) {
                if (initialGrid.get(row).get(column) == blank) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == shipSize) {
                    returnValue = true;
                }
            }
        }
        return returnValue;
    }

    /**
    * Generates the fours.
    *
    * @param initialGrid the grid
    * @param shipSize how large the ship is
    * @param enemy tells the program if it is the enemies turn
    *     (if yes, then enemy = 4, if not, then enemy = 0)
    * @return the new grid with ships
    */
    static ArrayList<ArrayList<String>> generateEachShip(
        final ArrayList<ArrayList<String>> initialGrid, final int shipSize,
        final int enemy) {

        // This array list is only used to return that the attempt has failed.
        final ArrayList<ArrayList<String>> fail =
            new ArrayList<ArrayList<String>>();
        fail.add(new ArrayList<String>());
        fail.get(0).add(failStr);

        ArrayList<ArrayList<String>> returnGrid =
            new ArrayList<ArrayList<String>>();
        returnGrid = initialGrid;

        ArrayList<ArrayList<String>> finalGrid =
            new ArrayList<ArrayList<String>>();

        boolean shipNotGenerated = true;
        do {
            // It will either round to 0 or 1. 0 = Vertical and 1 = Horizontal
            final int orientation = (int) Math.round(Math.random());

            // Vertical
            if (orientation == 0 && isVerticalValid(returnGrid, shipSize)) {
                final Random rand = new Random();
                final Random randTwo = new Random();
                // Picks a random column that is not higher than 10
                final int randCol = rand.nextInt(NUMCOLS);
                /*
                * Picks a random row from 0 - 6 to leav space for the 4 ship spots
                */
                final int randRow = randTwo.nextInt(NUMROWS - shipSize + 1);

                int count = 0;
                for (int counter = 0; counter < shipSize; counter++) {
                    if (returnGrid.get(randRow + counter).get(randCol)
                        == blank) {

                        count++;
                    }
                    if (count == shipSize) {

                        // Temporarily stores all the coordinates to be placed in the ship constructor.
                        ArrayList<ArrayList<Integer>> locations = new ArrayList<ArrayList<Integer>>();

                        // Generates the ship
                        for (int row = 0; row < shipSize; row++) {
                            returnGrid.get(randRow + row).remove(randCol);
                            returnGrid.get(randRow + row).add(randCol, ""
                                + shipSize);

                            locations.add(new ArrayList<Integer>());
                            locations.get(row).add(randRow + row);
                            locations.get(row).add(randCol);
                        }

                        // If it is the enemy grid
                        if (enemy == 4) {
                            enemyAllShips.makeShip(shipSize, locations);
                        // If it is the players grid
                        } else {
                            allShips.makeShip(shipSize, locations);
                        }

                        // A ship has now been generated
                        shipNotGenerated = false;
                    }
                }

                finalGrid = returnGrid;
            // Horizontal
            } else if (orientation == 1 && isHorizontalnValid(
                returnGrid, shipSize)) {

                final Random rand = new Random();
                final Random randTwo = new Random();

                // Picks a random column that is not higher than 10
                final int randCol = rand.nextInt(NUMCOLS - shipSize + 1);
                /*
                * Picks a random row from the 0th to the last which
                * happens to be 4
                */
                final int randRow = randTwo.nextInt(NUMROWS);

                int count = 0;
                for (int counter = 0; counter < shipSize; counter++) {
                    if (returnGrid.get(randRow).get(randCol + counter)
                        == blank) {

                        count++;
                    }
                    if (count == shipSize) {

                        // Temporarily stores all the coordinates to be placed in the ship constructor.
                        ArrayList<ArrayList<Integer>> locations = new ArrayList<ArrayList<Integer>>();

                        for (int column = 0; column < shipSize; column++) {
                            returnGrid.get(randRow).remove(randCol + column);
                            returnGrid.get(randRow).add(randCol + column,
                                Integer.toString(shipSize));

                            locations.add(new ArrayList<Integer>());
                            locations.get(column).add(randRow);
                            locations.get(column).add(randCol + column);
                        }

                        // If it is the enemy grid
                        if (enemy == 4) {
                            enemyAllShips.makeShip(shipSize, locations);
                        // If it is the players grid
                        } else {
                            allShips.makeShip(shipSize, locations);
                        }

                        // a ship has now been generated
                        shipNotGenerated = false;
                    }
                }

                finalGrid = returnGrid;
            } else {
                finalGrid = fail;
            }
        } while (shipNotGenerated);
        return finalGrid;
    }

    /**
    * Calls all the ships to be generated.
    *
    * @param initialGrid the grid
    * @param enemy tells the program whos turn it is
    * @return the new grid with ships
    */
    static ArrayList<ArrayList<String>> generateShips(
        final ArrayList<ArrayList<String>> initialGrid, final int enemy) {

        ArrayList<ArrayList<String>> returnGrid =
            new ArrayList<ArrayList<String>>();

        returnGrid = initialGrid;

        // Goes through all the types of ships
        for (int shipSize = 1; shipSize <= FOUR; shipSize++) {

            // Makes all the ships of type of quantity
            for (int counter = 0; counter < allShipQuantities.get(shipSize
                - ONE); counter++) {

                if (returnGrid.get(0).get(0) == failStr) {
                    System.out.println("Could not print "
                        + allShipQuantities.get(shipSize - ONE) + oneSpace
                        + shipSize);
                    break;
                } else {
                    returnGrid = generateEachShip(returnGrid, shipSize,
                        enemy);
                }
            }
        }

        return returnGrid;
    }

    /**
    * This sets up the game.
    *
    * @param enemy tells the program whos turn it is
    * @return returns the fully made grid
    */
    static ArrayList<ArrayList<String>> setUpGame(final int enemy) {
        final ArrayList<ArrayList<String>> grid =
            new ArrayList<ArrayList<String>>();

        ArrayList<ArrayList<String>> finalGrid =
            new ArrayList<ArrayList<String>>();

        for (int counter = 0; counter < NUMROWS; counter++) {
            // Adds a new row
            grid.add(new ArrayList<String>());
            for (int counterTwo = 0; counterTwo < NUMCOLS; counterTwo++) {
                // Adds a new column and a filler spot for it
                grid.get(counter).add(blank);
            }
        }

        finalGrid = generateShips(grid, enemy);

        return finalGrid;
    }

    /**
    * This is the main function.
    *
    * @param args this will not be used
    */
    public static void main(final String[] args) {
        setUpArrays();

 //       rulesAndStart();

        // Checks if there are more columns than letters
        if (NUMCOLS > CAPITALLETTERS) {
            System.out.println("There are too many columns. Please make it"
                + " between 1 and 26");
            // Ends the program
            System.exit(0);
        }

        ArrayList<ArrayList<String>> playerGrid =
            new ArrayList<ArrayList<String>>();

        // The 0 tells the enemy that it is the players grid
        playerGrid = setUpGame(0);

        // If the generation failed at any point
        if (playerGrid.get(0).get(0) != failStr) {
            System.out.println(yourGridStr);
            printGrid(playerGrid);
        }

        ArrayList<ArrayList<String>> enemyGrid =
            new ArrayList<ArrayList<String>>();

        // The 4 tells the program that it is the enemies grid
        enemyGrid = setUpGame(FOUR);

        // If the generation failed at any point
        if (enemyGrid.get(0).get(0) != failStr) {
            System.out.println(enemyGridStr);
            // Prints the enemies grid from the players perspective
            printEnemyGrid(enemyGrid);
        }

        if (playerGrid.get(0).get(0) != failStr && enemyGrid.get(0).get(0)
            != failStr) {

            playGame(playerGrid, enemyGrid);
        }
    }
}
