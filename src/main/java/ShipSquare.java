import java.io.Serializable;
import java.util.function.Consumer;

public class ShipSquare {
    int x;
    int y;
    //Determines if this square is sunk.
    boolean isSunk;
    boolean isHeadOfShip;
    //If true, boat is horizontally oriented. If false, vertical.
    ShipSquare(){
        isSunk = false;
        isHeadOfShip = false;
    }
}
