package tictactoe;

import tictactoe.Controller.Game;
import tictactoe.View.View;

public class TicTacToeMain {
	
	public static void main(String[] args) {
		new TicTacToeMain().launch(new Game(new View()));
	}
	
	public void launch(Game g) {
		while(g.isPlaying())
			g.play();
	}
	
}
