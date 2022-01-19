/*
* This is the class file holding all of the ships together
*
* @author  Jonathan Pasco-Arnone
* @version 1.0
* @since   2022-1-18
*/

import java.util.ArrayList;

/**
* This is the class file to hold all the pixel art.
*/
public class Fleet {
    /**
    * A 2D ArrayList holding all the ships and their data.
    */
    private static ArrayList<Ship> shipsDatabase;
    /**
    * The total number of ships in this fleet.
    */
    private static int totalShip;

    /**
    * An empty constructor.
    */
    public Fleet() {
        shipsDatabase = new ArrayList<Ship>();
        totalShips = 0;
    }

    /**
    * Initiates a new ship.
    *
    * @param initialLocation the initial coordinates of the ships parts
    * @param initialShipSize the size of the ship
    */
    public void makeShip(final int initialShipSize, ArrayList<ArrayList<Integer>> initialLocation) {
        shipsDatabase.add(new Ship(initialShipSize, initialLocation));
        totalShips++;
    }

    /**
    * Sets a coordinate to be replaced with a hit.
    *
    * @param rowCoord the row coordinate
    * @param columnCoord the column coordinate
    */
    public void replace(final int rowCoord, final int columnCoord) {
        for (int ship = 0; ship < totalShip; ship++) {
            shipsDatabase.get(ship).setHit(rowCoord, columnCoord);
        }
    }

    /**
    * Sets a coordinate to be sunk.
    *
    * @param rowCoord the row coordinate
    * @param columnCoord the column coordinate
    */
    public boolean getHit(final int rowCoord, final int columnCoord) {
        boolean returnValue = false;
        // Checks each ship
        for (int ship = 0; ship < totalShip; ship++) {
            returnValue = shipsDatabase.get(ship).getHit(rowCoord, columnCoord);
            // If the coordinate is a hit then break from the loop
            if (returnValue) {
                break;
            }
        }
        return returnValue;
    }
}