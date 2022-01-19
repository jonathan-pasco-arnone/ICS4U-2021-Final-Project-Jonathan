/*
* This is the class file holding the ships data
*
* @author  Jonathan Pasco-Arnone
* @version 1.0
* @since   2022-1-11
*/

import java.util.ArrayList;

/**
* This is the class file to hold all the pixel art.
*/
public class Ship {
    /**
    * The status of the a part of a ship.
    */
    private static ArrayList<Boolean> part = new ArrayList<Boolean>();
    /**
    * The location of each part in coordinates.
    */
    private static ArrayList<ArrayList<Integer>> location = new ArrayList<ArrayList<Integer>>();
    /**
    * The size of the ship.
    */
    private static int shipSize = 0;

    /**
    * The ship constructor.
    *
    * @param initialLocation the initial coordinates of the ships parts
    * @param initialShipSize the size of the ship
    */
    public Ship(final int initialShipSize, ArrayList<ArrayList<Integer>> initialLocation) {
        shipSize = initialShipSize;
        // Checks all the parts of the ship
        for (int counter = 0; counter < shipSize; counter++) {
            location.add(new ArrayList<Integer>());
            // Adds the row coordinate
            location.get(counter).add(initialLocation.get(counter).get(0));
            // Adds the column coordinate
            location.get(counter).add(initialLocation.get(counter).get(1));
            part.add(false);
        }
    }

    /*
    * Checks if the part is hit.
    *
    * @param rowCoord the row coordinate
    * @param columnCoord the column coordinate
    * @return returns whether or not the part is hit.
    */
    public boolean getHit(final int rowCoord, final int columnCoord) {
        boolean returnValue = false;
        // Checks all the parts of the ship
        for (int counter = 0; counter < shipSize; counter++) {
            // Checks for the correct coordinates
            if (location.get(counter).get(0) == rowCoord &&
                location.get(counter).get(1) == columnCoord) {
                // If the current part is hit
                if (part.get(counter)) {
                    returnValue = true;
                }
                break;
            }
        }

        return returnValue;
    }

    /*
    * Sets a part to hit.
    *
    * @param rowCoord the row coordinate
    * @param columnCoord the column coordinate
    */
    public void setHit(final int rowCoord, final int columnCoord) {
        // Checks all the parts of the ship
        for (int counter = 0; counter < shipSize; counter++) {
            // Checks for the correct coordinates
            if (location.get(counter).get(0) == rowCoord &&
                location.get(counter).get(1) == columnCoord) {

                // If the current part is NOT hit
                if (!part.get(counter)) {
                    // Sets the part to hit
                    part.remove(counter);
                    part.add(counter, true);
                }
                break;
            }
        }
    }

    /*
    * Checks if the ship is sunk.
    *
    * @return returns whether or not the ship is sunk.
    */
    public boolean partSunk() {
        boolean returnValue = false;
        int count = 0;

        for (int counter = 0; counter < shipSize; counter++) {
            // If the current part is hit (true == hit)
            if (part.get(counter)) {
                count++;
            }
        }

        if (count == shipSize) {
            returnValue = true;
        }

        return returnValue;
    }
}