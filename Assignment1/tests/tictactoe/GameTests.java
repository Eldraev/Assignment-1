package tictactoe;

import static org.junit.Assert.*;

import org.junit.Test;

import tictactoe.Controller.Game;
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
	
}
