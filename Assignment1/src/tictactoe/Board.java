package tictactoe;

public class Board {
	
	public Field getField(int x, int y) {
		if(x<0 || y<0 || x>2 || y>2)
			throw new IllegalArgumentException();
		return null;
	}
	
}
