package tictactoe;

import static org.junit.Assert.*;

import org.junit.Test;

public class FieldTests {

	@Test
	public void shouldCreateAInstance() {
		new Field();
	}
	
	@Test
	public void shouldGetFieldValue() {
		Field f = new Field();
		f.getValue();
	}
	
	@Test
	public void shouldSetFieldValue() {
		Field f = new Field();
		f.setValue(FieldState.Circle);
	}
	
	@Test
	public void shouldReturnCorrectValue() {
		Field f = new Field(FieldState.Circle);
		assertEquals(f.getValue(),FieldState.Circle);
	}
	
	@Test
	public void shouldInitializeWithValue() {
		Field f = new Field();
		assertEquals(f.getValue(),FieldState.Empty);
	}
	
}

