package tictactoe;

import static org.junit.Assert.*;

import org.junit.Test;

public class FieldTests {

	@Test
	public void shouldCreateAInstance() {
		new Field();
	}
	
	@Test
	public void shouldCreateWithState() {
		new Field(FieldState.Empty);
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
	
}

