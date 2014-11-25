package tictactoe;

import tictactoe.Controller.Game;
import tictactoe.View.View;

public class TicTacToeMain {
	
	public void launch(Game g) {
		while(g.isPlaying())
			g.play();
	}
	
}
