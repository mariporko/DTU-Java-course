package checkers;

public class Move {
	private Coordinates from;
	private Coordinates to;
	private Player player;
	
	public Move(Coordinates from, Coordinates to, Player p) {
		this.from = from;
		this.to = to;
		this.player = p;
	}
	
	// Function that makes sure that the move that a player makes is allowed
	public boolean isAllowed(Board b) {
		boolean isClear = false;
		boolean isNear = false;
		
		if (b.getSquareStatus(to) == 0) isClear = true;
		if (player.getToken() == 1 && to.y() == from.y()+1 && (to.x() == from.x()+1 || to.x() == from.x()-1)) isNear = true; 
		else if (player.getToken() == 2 && to.y() == from.y()-1 && (to.x() == from.x()+1 || to.x() == from.x()-1)) isNear = true; 
			
		if (isClear && isNear) return true;
		else return false;
	}
	
	
	public void makeMove(Board b) {
		Board.setBoard(to, from, player);
	}
		
}
