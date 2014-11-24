package tictactoe.Controller;

import java.util.Scanner;

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
		Scanner scan = new Scanner(System.in);
		if(scan.hasNextLine())
			scan.nextLine();
		return 0;
	}
	
}
