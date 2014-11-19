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
	
	@Test
	public void shouldThrowOnOutOfDimensionsX() {
		Board b = new Board();
		try {
			Field f = b.getField(-1, 2);
			fail();
		} catch(IllegalArgumentException e) {
			
		}
	}
	
	@Test
	public void shouldThrowOnOutOfDimensionsY() {
		Board b = new Board();
		try {
			Field f = b.getField(2, -2);
			fail();
		} catch(IllegalArgumentException e) {
			
		}
	}
	
	@Test
	public void shouldThrowOnOutOfDimensionsXUpper() {
		Board b = new Board();
		try {
			Field f = b.getField(3, 2);
			fail();
		} catch(IllegalArgumentException e) {
			
		}
	}
	
	@Test
	public void shouldThrowOnOutOfDimensionsYUpper() {
		Board b = new Board();
		try {
			Field f = b.getField(0, 7);
			fail();
		} catch(IllegalArgumentException e) {
			
		}
	}
	
}
