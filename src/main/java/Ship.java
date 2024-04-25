import java.io.Serializable;
import java.util.ArrayList;

public class Ship{
    int shipLength;
    ArrayList<ShipSquare> arrayOfSquares = new ArrayList<>();
    boolean isPlaced;
    boolean boatIsSunk;

    //Creates an empty ship. By default horizontal.
    Ship(){
        shipLength = 0;
        isPlaced = false;
        boatIsSunk = false;
    }
    //Creates a ship with desired length and alignment.
    Ship(int length){
        isPlaced = false;
        boatIsSunk = false;
        this.shipLength = length;
        for(int i = 0; i < length; i++){
            ShipSquare squareToAdd = new ShipSquare();
            if(i == 0){
                squareToAdd.isHeadOfShip = true;
            }
            arrayOfSquares.add(squareToAdd);
        }
    }
    //Returns the number of squares not sunk.
    public int getRemainingSquares(){
        int counter = 0;
        for(int i = 0; i < arrayOfSquares.size(); i++){
            if(!arrayOfSquares.get(i).isSunk) {
                counter++;
            }
        }
        return counter;
    }
    //Adds an individual ship square to the ship.
    public void addShipSquare(ShipSquare squareToAdd){
        shipLength++;
        if(shipLength == 0){
            squareToAdd.isHeadOfShip = true;
        }
        arrayOfSquares.add(squareToAdd);
    }

    //Checks that all squares of boat are sunk.
    public boolean isBoatSunk(){
        if(this.getRemainingSquares() == 0){
            boatIsSunk = true;
            return true;
        }
        else{
            boatIsSunk = false;
            return false;
        }
    }
    //Returns true if ship square exists in this ship.
    public boolean findShipSquare(int x, int y){
        for (ShipSquare arrayOfSquare : arrayOfSquares) {
            if (arrayOfSquare.x == x && arrayOfSquare.y == y) {
                return true;
            }
        }
        return false;
    }
}
