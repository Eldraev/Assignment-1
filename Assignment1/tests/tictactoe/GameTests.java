package tictactoe;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.InOrder;

import tictactoe.Controller.Game;
import tictactoe.Model.Board;
import tictactoe.Model.Field;
import tictactoe.View.View;

public class GameTests {
	
	@Test
	public void shouldInstantiateWithViewReference() {
		new Game(new View());
	}
	
	@Test
	public void shouldPlayGame() {
		Game g = new Game(new View());
		g.play();
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
	
}
