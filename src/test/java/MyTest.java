import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MyBattleshipTest {
	Game theGame = new Game();
	@Test
	public void testBoardPlacement() {
		theGame.clientBoard.generateFleet();
		theGame.clientBoard.placeShip(theGame.clientBoard.fleet[0],0,0, false);
		theGame.clientBoard.placeShip(theGame.clientBoard.fleet[1],1,0, true);
		//Testing vertical ship of size 2.
		assertEquals(theGame.clientBoard.board[0][0], theGame.clientBoard.fleet[0].arrayOfSquares.get(0));
		assertTrue(theGame.clientBoard.board[0][0].isHeadOfShip);
		assertEquals(theGame.clientBoard.board[0][1], theGame.clientBoard.fleet[0].arrayOfSquares.get(1));
		assertFalse(theGame.clientBoard.board[0][1].isHeadOfShip);
		//Testing horizontal ship of size 3.
		assertEquals(theGame.clientBoard.board[1][0], theGame.clientBoard.fleet[1].arrayOfSquares.get(0));
		assertTrue(theGame.clientBoard.board[1][0].isHeadOfShip);
		assertEquals(theGame.clientBoard.board[2][0], theGame.clientBoard.fleet[1].arrayOfSquares.get(1));
		assertFalse(theGame.clientBoard.board[2][0].isHeadOfShip);
		assertEquals(theGame.clientBoard.board[3][0], theGame.clientBoard.fleet[1].arrayOfSquares.get(2));
		assertFalse(theGame.clientBoard.board[3][0].isHeadOfShip);
	}
	@Test
	public void testSunk() {
		theGame.clientBoard.generateFleet();
		theGame.clientBoard.placeShip(theGame.clientBoard.fleet[0],0,0, false);
		theGame.clientBoard.placeShip(theGame.clientBoard.fleet[1],1,0, true);
		theGame.clientBoard.receiveMissile(3,0);
		assertTrue(theGame.clientBoard.board[3][0].isSunk);
		assertFalse(theGame.clientBoard.board[2][0].isSunk);
	}
	@Test
	public void testGameOver() {
		theGame.clientBoard.generateFleet();
		theGame.clientBoard.placeShip(theGame.clientBoard.fleet[0],0,0, false);
		theGame.clientBoard.receiveMissile(0, 0);
		theGame.clientBoard.receiveMissile(0, 1);
		assertTrue(theGame.clientBoard.fleet[0].boatIsSunk);
		theGame.clientBoard.placeShip(theGame.clientBoard.fleet[1],1,0, true);
		theGame.clientBoard.receiveMissile(1,0);
		theGame.clientBoard.receiveMissile(2,0);
		theGame.clientBoard.receiveMissile(3,0);
		assertTrue(theGame.clientBoard.fleet[1].boatIsSunk);
		theGame.clientBoard.placeShip(theGame.clientBoard.fleet[2],0,2, true);
		theGame.clientBoard.receiveMissile(0,2);
		theGame.clientBoard.receiveMissile(1,2);
		theGame.clientBoard.receiveMissile(2,2);
		assertTrue(theGame.clientBoard.fleet[2].boatIsSunk);
		theGame.clientBoard.placeShip(theGame.clientBoard.fleet[3],0,3, true);
		theGame.clientBoard.receiveMissile(0,3);
		theGame.clientBoard.receiveMissile(1,3);
		theGame.clientBoard.receiveMissile(2,3);
		theGame.clientBoard.receiveMissile(3,3);
		assertTrue(theGame.clientBoard.fleet[3].boatIsSunk);
		theGame.clientBoard.placeShip(theGame.clientBoard.fleet[4],0,4, true);
		theGame.clientBoard.receiveMissile(0,4);
		theGame.clientBoard.receiveMissile(1,4);
		theGame.clientBoard.receiveMissile(2,4);
		theGame.clientBoard.receiveMissile(3,4);
		theGame.clientBoard.receiveMissile(4,4);
		assertTrue(theGame.clientBoard.fleet[4].boatIsSunk);
		assertTrue(theGame.clientBoard.gameOver);
		assertEquals(theGame.getWinner(), "Opponent Won");
	}
}
