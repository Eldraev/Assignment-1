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
		int output = -1;
		if(scan.hasNextInt())
			output = scan.nextInt();
		if(output < 0)
			throw new IllegalArgumentException();
		return output;
	}
	
}
