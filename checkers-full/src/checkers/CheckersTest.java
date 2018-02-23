package checkers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CheckersTest {
	private Board board;
	private Move ok1, ok2, ok3, ex1, ex2, ex3;
	private Player p1, p2;
	
	@Before
	public void setValues() {
		board = new Board();
		
		p1 = new Player(1);
		p2 = new Player(2);
		
		ok1 = new Move(new Coordinates(1, 2), new Coordinates(0, 3), p1);
		ok2 = new Move(new Coordinates(0, 5), new Coordinates(1, 4), p1);
		ok3 = new Move(new Coordinates(2, 1), new Coordinates(1, 2), p1);
		
		ex1 = new Move(new Coordinates(4, 5), new Coordinates(5, 4), p1);
		ex2 = new Move(new Coordinates(5, 0), new Coordinates(6, 1), p1);
		ex3 = new Move(new Coordinates(0, 7), new Coordinates(1, 6), p2);
	}

	@Test
	public void testBasicGamePlay() {
		Checkers.startGame();
		assertEquals(true, ok1.isAllowed(board));
		ok1.makeMove(board);
		assertEquals(false, ok2.isAllowed(board));
		p1.changePlayer();
		assertEquals(true, ok2.isAllowed(board));
		p1.changePlayer();
		assertEquals(true, ok3.isAllowed(board));
	}

	@Test
	public void testExceptionHandling() {
		Checkers.startGame();
		assertEquals(false, ex1.isAllowed(board));
		assertEquals(false, ex2.isAllowed(board));
		assertEquals(false, ex3.isAllowed(board));
	}
}