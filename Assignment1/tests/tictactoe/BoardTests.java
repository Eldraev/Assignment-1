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
		b.getField(1,1);
	}
	
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
	
}
