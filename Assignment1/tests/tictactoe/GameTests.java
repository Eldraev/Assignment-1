package tictactoe;

import static org.junit.Assert.*;

import org.junit.Test;

import tictactoe.Controller.Game;

public class GameTests {
	
	@Test
	public void shouldCreateAInstance() {
		new Game();
	}
	
	@Test
	public void shouldPlayGame() {
		Game g = new Game();
		g.play();
	}
	
}
