package checkers;

public class Board {
	
	private static int[][] board = {
		{0,1,0,1,0,1,0,1},
		{1,0,1,0,1,0,1,0},
		{0,1,0,1,0,1,0,1},
		{0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0},
		{2,0,2,0,2,0,2,0},
		{0,2,0,2,0,2,0,2},
		{2,0,2,0,2,0,2,0}
	};
	
	
	public static int[][] getBoard() { return board; }
	
	public static void setBoard(Coordinates moveTo, Coordinates moveFrom, Player p) {
		board[moveFrom.y()][moveFrom.x()] = 0;
		board[moveTo.y()][moveTo.x()] = p.getToken();
	}
	
	public static int getSquareStatus(Coordinates c) {
		return board[c.y()][c.x()];
	}
	
	public static void printBoard() {
		System.out.println("   0 1 2 3 4 5 6 7  <- X axis");
		System.out.println("  +----------------+"); 
		
		int count = 0;
		
		for (int[] line : board) {
			System.out.print(count + " |");
			for (int i : line) {
				if (i == 0) System.out.print("  ");
				else System.out.print(i + " ");
			}
			count++;
			System.out.print("| \n");
		}

		System.out.println("  +----------------+"); 
		System.out.println("   0 1 2 3 4 5 6 7 \n");
	}
	
	public boolean movingIsPossible(Coordinates from, Player player) {
		boolean leftClear = false;
		boolean rightClear = false;
		
		if (player.getToken() == 1) {
			if (from.x() < 7 && (getSquareStatus(new Coordinates(from.x()+1, from.y()+1)) == 0)) rightClear = true;
			if (from.x() > 1 && (getSquareStatus(new Coordinates(from.x()-1, from.y()+1)) == 0)) leftClear = true;
		}
		
		if (player.getToken() == 2) {
			if (from.x() < 7 && (getSquareStatus(new Coordinates(from.x()+1, from.y()-1)) == 0)) rightClear = true;
			if (from.x() > 1 && (getSquareStatus(new Coordinates(from.x()-1, from.y()-1)) == 0)) leftClear = true;
		}
		
		if (leftClear || rightClear) return true;
		else return false;
	}

}
