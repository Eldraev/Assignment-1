package tictactoe;

import static org.junit.Assert.*;

import org.junit.Test;

import tictactoe.Model.Field;
import tictactoe.Model.FieldState;

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
			new Field(null);
			fail();
		} catch(IllegalArgumentException e) {
		}
	}
}

