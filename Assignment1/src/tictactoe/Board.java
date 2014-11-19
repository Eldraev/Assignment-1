package tictactoe;

import static org.junit.Assert.assertEquals;

public class Board {
	
	private Field[][] fields;
	
	public Board() {
		fields = new Field[3][3];
		clear();
	}
	
	public Field getField(int x, int y) {
		if(x<0 || y<0 || x>2 || y>2)
			throw new IllegalArgumentException();
		return fields[x][y];
	}
	
	public void clear() {
		for(int x=0;x<3;x++) {
			for(int y=0;y<3;y++) 
				fields[x][y] = new Field();
		}
	}
}
