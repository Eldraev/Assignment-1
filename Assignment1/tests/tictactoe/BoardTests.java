package tictactoe;

import static org.junit.Assert.*;

import org.junit.Test;

import tictactoe.Model.Board;
import tictactoe.Model.Field;
import tictactoe.Model.FieldState;

public class BoardTests {
	
	@Test
	public void shouldThrowOnOutOfDimensions() {
		Board b = new Board();
		try {
			b.getField(-1, 2);
			fail();
		} catch(IllegalArgumentException e) {
			
		}
		try {
			b.getField(0, -8);
			fail();
		} catch(IllegalArgumentException e) {
			
		}
		try {
			b.getField(3, 2);
			fail();
		} catch(IllegalArgumentException e) {
			
		}
		try {
			b.getField(0, 7);
			fail();
		} catch(IllegalArgumentException e) {
			
		}
	}
	
	@Test
	public void shouldInitWithEmptyField() {
		Board b = new Board();
		checkBoardFor(b,FieldState.Empty);
	}
	
	@Test
	public void shouldEmptyField() {
		Board b = new Board();
		b.getField(1, 1).setValue(FieldState.Circle);
		b.clear();
		checkBoardFor(b,FieldState.Empty);
	}
	
	@Test
	public void shouldGetBoardArrayWithOneCross() {
		Board b = new Board();
		b.clear();
		b.getField(1, 1).setValue(FieldState.Cross);
		Field[][] f = b.getBoardArray();
		assertEquals(f[1][1].getValue(),FieldState.Cross);
	}
	
	private void checkBoardFor(Board b, FieldState fs) {
		for(int x=0;x<3;x++) {
			for(int y=0;y<3;y++) 
				assertEquals(b.getField(x, y).getValue(),fs);
		}
	}
	
}
