package tictactoe.Controller;

import tictactoe.Model.Board;
import tictactoe.View.View;

public class Game {

	private View view;
	
	public Game(View v) {
		view = v;
	}
	
	public void play() {
		for(int i = 0; i<9;i++) {
			view.drawBoard(new Board().getBoardArray());
			view.notifyPlayer();
		}
	}
	
	public int getInput() {
		return 0;
	}
	
}
