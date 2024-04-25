import javax.swing.*;

//import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.VBox;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame {
    private final Game game;
    private final JLabel statusLabel;

    public ClientGUI() {
        this.game = new Game();

        // Set up the main frame
        setTitle("Battleship Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create game board panels
        JPanel clientBoardPanel = createBoardPanel(game.getClientBoard());
        JPanel opponentBoardPanel = createBoardPanel(game.getOpponentBoard());

        // Create status label
        statusLabel = new JLabel("Game Status: " + game.getWinner());
        statusLabel.setHorizontalAlignment(JLabel.CENTER);

        // Add components to the frame
        add(clientBoardPanel, BorderLayout.WEST);
        add(opponentBoardPanel, BorderLayout.EAST);
        add(statusLabel, BorderLayout.SOUTH);

        // Set frame size and visibility
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    private JPanel createBoardPanel(gameBoard board) {
        JPanel panel = new JPanel(new GridLayout(10, 10));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(50, 50));
                // Add action listener to handle button clicks
                button.addActionListener(e -> {
                    // Handle button click event
                    int x = panel.getComponentZOrder(button) / 10;
                    int y = panel.getComponentZOrder(button) % 10;

                    // Update the appearance of the button based on the firing action
                    button.setEnabled(false); // Disable the button to indicate it has been fired upon

                    // Call the receiveFire method of the client board
                    board.receiveMissile(x, y);

                    // Update the game status label
                    statusLabel.setText("Game Status: " + game.getWinner());
                });
                panel.add(button);
            }
        }
        return panel;
    }

    public static void main(String[] args) {
    	//not sure if I'm invoking this correctly 
    	//(everything above here I haven't touched)
    	SwingUtilities.invokeLater((Runnable) startFrame());
        SwingUtilities.invokeLater(ClientGUI::new);
    }
    
    //Start GUI edits (just GUI btw no functionality)
    public static JFrame startFrame() {
    	JFrame clientStart = new JFrame("Project 4");
    	clientStart.setSize(500, 200);
    	clientStart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	JLabel label = new JLabel("Battleships");
    	label.setHorizontalAlignment(JLabel.CENTER);
    	label.setFont(new Font("Impact", Font.PLAIN, 50));
    	
    	//Button formatting
    	JPanel wPan = new JPanel();
    	wPan.setBorder(BorderFactory.createEmptyBorder(50,10,10,10));
    	JPanel ePan = new JPanel();
    	ePan.setBorder(BorderFactory.createEmptyBorder(50,10,10,10));
    	JButton compChoice = new JButton("Play against computer");
    	//addActionListner here
    	JButton userChoice = new JButton("Play against random user");
    	//addActionListner here
    	userChoice.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
    			JOptionPane.showMessageDialog(null, "Searching for players...");
			}
    	});
    	wPan.add(compChoice, BorderLayout.CENTER);
    	ePan.add(userChoice, BorderLayout.CENTER);
    	
    	clientStart.setLayout(new BorderLayout());
    	clientStart.add(label, BorderLayout.NORTH);
    	clientStart.add(wPan, BorderLayout.WEST);
    	clientStart.add(ePan, BorderLayout.EAST);
    	
    	clientStart.setVisible(true);
    	
    	return clientStart;
    }
}
