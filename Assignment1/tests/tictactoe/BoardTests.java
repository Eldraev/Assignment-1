package tictactoe;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTests {

	@Test
	public void shouldCreateAInstance() {
		new Board();
	}
	
	@Test
	public void shouldGetField() {
		Board b = new Board();
		Field f = b.getField(1,1);
	}
	
}
