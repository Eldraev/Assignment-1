package tictactoe;

import static org.junit.Assert.*;

import org.junit.Test;

public class FieldTests {
	
	@Test
	public void shouldInitializeWithValue() {
		Field f = new Field();
		assertEquals(f.getValue(),FieldState.Empty);
	}
	
	@Test
	public void shouldChangeValue() {
		Field f = new Field();
		f.setValue(FieldState.Cross);
		assertEquals(f.getValue(),FieldState.Cross);	
	}
	
	@Test
	public void shouldNotTakeNull() {
		try{
			Field f = new Field(null);
			fail();
		} catch(IllegalArgumentException e) {
		}
	}
}

