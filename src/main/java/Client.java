import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public void connectToServer(String serverAddress, int port) {
        try {
            socket = new Socket(serverAddress, port);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println("Connected to server.");
        } catch (IOException e) {
            System.err.println("Error connecting to server: " + e.getMessage());
        }
    }

    public void sendGame(Game game) {
        try {
            outputStream.writeObject(game);
            outputStream.flush();
            System.out.println("Sent game to server.");
        } catch (IOException e) {
            System.err.println("Error sending game: " + e.getMessage());
        }
    }

    public Game receiveGame() {
        try {
            Game game = (Game) inputStream.readObject();
            System.out.println("Received game from server.");
            return game;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error receiving game: " + e.getMessage());
            return null;
        }
    }

    public void close() {
        try {
            if (outputStream != null)
                outputStream.close();
            if (inputStream != null)
                inputStream.close();
            if (socket != null)
                socket.close();
            System.out.println("Connection closed.");
        } catch (IOException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}
