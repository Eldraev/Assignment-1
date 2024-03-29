package tictactoe;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import tictactoe.Controller.Game;
import tictactoe.Model.Field;
import tictactoe.Model.FieldState;
import tictactoe.View.View;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.anyString;

public class MainTests {
	
	@Test
	public void shouldPlayGame() {
		Game mockGame = mock(Game.class);
		new TicTacToeMain().launch(mockGame);
		verify(mockGame).isPlaying();
	}
	
}
