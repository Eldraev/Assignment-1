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
	
}

