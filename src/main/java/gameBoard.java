import java.util.ArrayList;

public class gameBoard {
    int width = 13; // Extend width to 13
    int height = 10;
    boolean gameOver;
    ShipSquare[][] board = new ShipSquare[13][10]; // Adjust the board size to 13x10
    Ship[] fleet = new Ship[5];

    // Constructor
    public gameBoard() {
        generateFleet(); // Initialize fleet when creating the game board
    }

    // Creates ships with default lengths
    public void generateFleet(){
        fleet[0] = new Ship(2);
        fleet[1] = new Ship(3);
        fleet[2] = new Ship(3);
        fleet[3] = new Ship(4);
        fleet[4] = new Ship(5);
    }

    // Takes the coordinate of the head of the ship and places ships left to right if horizontal, top to bottom if vertical.
    public void placeShip(Ship shipToPlace,int x, int y, boolean horizontal){
        board[x][y] = shipToPlace.arrayOfSquares.getFirst();
        if(horizontal){
            for(int i = 1; i < shipToPlace.shipLength; i++){
                //TODO: add the shipSquare's coordinates.
                board[x+i][y] = shipToPlace.arrayOfSquares.get(i);
                shipToPlace.arrayOfSquares.get(i).x = x+i;
                shipToPlace.arrayOfSquares.get(i).y = y;
            }
        }
        else{
            for(int i = 1; i < shipToPlace.shipLength; i++){
                board[x][y+i] = shipToPlace.arrayOfSquares.get(i);
                shipToPlace.arrayOfSquares.get(i).x = x;
                shipToPlace.arrayOfSquares.get(i).y = y+i;
            }
        }
    }

    // Receives a fire at the specified coordinates
    public void receiveMissile(int x, int y) {
        // Check if the square has already been fired upon
        if (board[x][y] == null) {
            System.out.println("Square (" + x + ", " + y + ") has already been fired upon.");
            return; // Do nothing if the square has already been fired upon
        }

        // Mark the square as fired upon
        board[x][y].isSunk = true;

        // Check if a ship is hit
        if (board[x][y].isHeadOfShip) {
            System.out.println("Ship hit at square (" + x + ", " + y + ")!");

            // Find the ship associated with this square
            for (Ship ship : fleet) {
                if (ship != null && ship.findShipSquare(x, y)) {
                    // Mark the ship square as hit
                    for (ShipSquare square : ship.arrayOfSquares) {
                        if (square.x == x && square.y == y) {
                            square.isSunk = true;
                            break;
                        }
                    }
                    // Check if the ship is sunk
                    if (ship.isBoatSunk()) {
                        System.out.println("Ship is sunk!");
                        // You may add further actions here if needed
                    }
                    break;
                }
            }
        } else {
            System.out.println("Miss! No ship at square (" + x + ", " + y + ").");
        }
    }

    // Method to fire at another game board
    public void fire(int x, int y, gameBoard boardToFireAt) {
        boardToFireAt.receiveMissile(x, y);
    }

    // Will return false if a boat is found that isn't sunk.
    public boolean isGameOver(){
        for(int i = 0; i < 5; i++){
            if(fleet[i] == null || !fleet[i].boatIsSunk){ // Null check added here
                gameOver = false;
                return false;
            }
        }
        gameOver = true;
        return true;
    }
}
