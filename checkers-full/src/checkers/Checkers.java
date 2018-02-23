package checkers;

import java.util.Scanner;

//	EXERCISE DESCRIPTION:
//	Implement the assignment using 
// 	-	object orientation [check]
//	-	exceptions handling (for example, in case of bad input from the user) [check]
//	-	unit tests. [check]
//	Also, provide a class diagram explaining your solution. It is not mandatory to use generics.
//	The code has to be located in a GitHub repository and the class diagram has to be uploaded as PDF file in the repository2.

public class Checkers {
	public static Coordinates oldPosition, newPosition;
	public static Board board;
	public static Move move;
	public static boolean gameOn;
	public static Player player;

	// Function for taking in the coordinates from the player, and validating them
	public static int takeValue( ) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		
		if (n >= 0 && n <= 7) return n;
		else {
			System.out.print("Please enter a number between 0 and 7: ");
			return takeValue();
		}
	}
	
	// Exception class, for handling bad input from user
	static class InputException extends Exception {
		
		public InputException(String message) {
			super(message);
		}
	}
	
	public static void startGame() {
		board = new Board();
		player = new Player(1);
		gameOn = true;
	}
	
	// Function for setting the position of piece to move. Throws exception, if the piece isn't owned by player.
	public static void setOldPosition() throws InputException {
		System.out.println("Coordinate of piece to move");
		System.out.print("Enter X: ");
		int x = takeValue();
		System.out.print("Enter Y: ");
		int y = takeValue();
		
		oldPosition = new Coordinates(x, y);
		
		if (board.getSquareStatus(oldPosition) != player.getToken()) {
			throw new InputException("You can only move your own pieces!");
		} else if  (!board.movingIsPossible(oldPosition, player)) {
			throw new InputException("This piece can't be moved yet. Select another piece.");
		}
	}

	// Function for setting new position. Throws exception, if the piece isn't owned by player.
	public static void setNewPosition() throws InputException {
		System.out.println("Coordinate of new position");
		System.out.print("Enter X: ");
		int newX = takeValue();
		System.out.print("Enter Y: ");
		int newY = takeValue();

		newPosition = new Coordinates(newX, newY);
		move = new Move(oldPosition, newPosition, player);
		
		if (!move.isAllowed(board)) {
			throw new InputException("You can only move forward, diagonally, and to empty cells.");
		} 
	}
	
	public static void endGameCheck() {
		System.out.println("Do you want to continue the game? (Y/N) ");
		Scanner s = new Scanner(System.in);
		String c = s.next();
		
		if (c == "n" || c == "N") { 
			gameOn = false;
			System.out.println("You have ended the game.");
		}
	}
	
	
	public static void main(String[] args) throws InputException {
		
		startGame();
		
		// loop for gameplay, each loop is a turn in the game
		while (gameOn) {
			board.printBoard();
			
			System.out.println("Turn of player no. " + player.getToken());

			boolean pieceOwned = false;
			while (!pieceOwned) {
				try {
				      setOldPosition();
				      pieceOwned = true;
				    }
				    catch(InputException e) {
				      System.out.println(e.getMessage());
				    }
			}
			
			boolean movePossible = false;
			while (!movePossible) {
				try {
				      setNewPosition();
				      movePossible = true;
				    }
				    catch(InputException e) {
				      System.out.println(e.getMessage());
				    }
			}
			
			// Making the move 			
			move.makeMove(board);
			System.out.println("Piece moved! \n");
			
			// Change the turn
			player.changePlayer();
			
			// Check to see if player wants to continue
			endGameCheck();
		}
	}
}
