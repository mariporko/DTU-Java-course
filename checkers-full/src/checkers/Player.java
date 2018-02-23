package checkers;

public class Player {
	private static int token = 1;

	public Player(int token) {
		this.token = token;
	}
	
	public int getToken() { return token; }
	
	public void changePlayer() {
		if (token == 1) token = 2;
		else token = 1;
	}
	
}
