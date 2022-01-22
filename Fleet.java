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
    private ArrayList<Ship> shipsDatabase;
    /**
    * The total number of ships in this fleet.
    */
    private int totalShips;

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
    public void makeShip(final int initialShipSize,
        final ArrayList<ArrayList<Integer>> initialLocation) {
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
        for (int ship = 0; ship < totalShips; ship++) {
            shipsDatabase.get(ship).setHit(rowCoord, columnCoord);
        }
    }

    /**
    * Determines if a ship part with the given coords is hit.
    *
    * @param rowCoord the row coordinate
    * @param columnCoord the column coordinate
    * @return returns whether or not the part is hit.
    */
    public boolean getHit(final int rowCoord, final int columnCoord) {
        boolean returnValue = false;
        // Checks each ship
        for (int ship = 0; ship < totalShips; ship++) {
            returnValue = shipsDatabase.get(ship).getHit(rowCoord, columnCoord);
            // If the coordinate is a hit then break from the loop
            if (returnValue) {
                break;
            }
        }
        return returnValue;
    }

    /**
    * Sets a coordinate to hit.
    *
    * @param rowCoord the row coordinate
    * @param columnCoord the column coordinate
    */
    public void setHit(final int rowCoord, final int columnCoord) {
        // Checks each ship
        for (int ship = 0; ship < totalShips; ship++) {
            // Attempts to execute a hit
            if (shipsDatabase.get(ship).setHit(rowCoord, columnCoord)) {
                /*
                * If the hit was successfull then there is no
                * need to check other ships
                */
                break;
            }
        }
    }

    /**
    * Determines if there is a ship in the location.
    * If there are any, then it will return the size of the ship.
    *
    * @param rowCoord the row coordinate
    * @param columnCoord the column coordinate
    * @return returns the the size of the ship with the
    *    inputted location
    */
    public int getShipSize(final int rowCoord, final int columnCoord) {
        int returnValue = 0;
        // Checks each ship
        for (int ship = 0; ship < totalShips; ship++) {
            returnValue = shipsDatabase.get(ship).checkShipSize(
                rowCoord, columnCoord);
            /*
            * If the coordinate has already been gotten, then
            * there is no reason to continue
            */
            if (returnValue > 0) {
                break;
            }
        }
        return returnValue;
    }

    /**
    * Determines if a ship is sunk.
    *
    * @param rowCoord the row coordinate
    * @param columnCoord the column coordinate
    * @return returns whether or not the part is hit.
    */
    public boolean checkSunk(final int rowCoord, final int columnCoord) {
        boolean returnValue = false;
        // Checks each ship
        for (int ship = 0; ship < totalShips; ship++) {
            returnValue = shipsDatabase.get(ship).checkSunk(
                rowCoord, columnCoord);
            // If the coordinate is a hit then break from the loop
            if (returnValue) {
                break;
            }
        }
        return returnValue;
    }
}
