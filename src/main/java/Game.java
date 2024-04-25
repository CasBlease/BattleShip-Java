import java.io.Serializable;

public class Game implements Serializable {
    public gameBoard clientBoard;
    public gameBoard opponentBoard;

    public Game() {
        this.clientBoard = new gameBoard();
        this.opponentBoard = new gameBoard();
    }

    public gameBoard getClientBoard() {
        return clientBoard;
    }

    public gameBoard getOpponentBoard() {
        return opponentBoard;
    }

    public String getWinner() {
        if (clientBoard.isGameOver()) {
            return "Opponent Won";
        } else if (opponentBoard.isGameOver()) {
            return "Client Won";
        } else {
            return "Game Still in Progress";
        }
    }
}
