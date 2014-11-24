package tictactoe;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import tictactoe.Controller.Game;
import tictactoe.Model.Board;
import tictactoe.Model.Field;
import tictactoe.Model.FieldState;
import tictactoe.View.View;

public class GameTests {
	
	private InputStream originalInput;
	private ByteArrayInputStream mockInput;
	
	@Before
	public void mockInput() {
		originalInput = System.in;
		mockInput = mock(ByteArrayInputStream.class);
		System.setIn(mockInput);
	}
	
	@Test
	public void shouldShowDialog() {
		View mockView = mock(View.class);
		Game g = new Game(mockView);
		g.play();
		InOrder inOrder = inOrder(mockView);
		for(int i=0;i<9;i++) {
			inOrder.verify(mockView).drawBoard(any(Field[][].class));
			inOrder.verify(mockView).notifyPlayer();
		}
	}
	
	@Test
	public void shouldTakeInputFromStream() {
		Game g = new Game(new View());
		g.getInput();
		InOrder inOrder = inOrder(mockInput);
		inOrder.verify(mockInput).read(any(byte[].class),any(int.class),any(int.class));
	}
	
	@Test
	public void shouldReturnParsedNumber() {
		int testInt = 2;
		Game g = new Game(new View());
		System.setIn(new ByteArrayInputStream( (testInt+"").getBytes()));
		int i = g.getInput();
		assertEquals(i,testInt);
	}
	
	@Test
	public void shouldThrowOnNegative() {
		int testInt = -2;
		Game g = new Game(new View());
		System.setIn(new ByteArrayInputStream( (testInt+"").getBytes()));
		try{
			g.getInput();
			fail();
		} catch(IllegalArgumentException e) {
			
		} 
	}
	
	@After
	public void resetOutput() {
		System.setIn(originalInput);
	}
	
}
