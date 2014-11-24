package tictactoe.Controller;

import java.util.Scanner;

import tictactoe.Model.Board;
import tictactoe.View.View;

public class Game {

	private Board board;
	private View view;
	
	public Game(View v) {
		view = v;
		board = new Board();
	}
	
	public void play() {
		for(int i = 0; i<9;i++) {
			view.drawBoard(new Board().getBoardArray());
			view.notifyPlayer();
			view.askForColumn();
			try {
				getInput();
			} catch(IllegalArgumentException e) {
				
			}
			view.askForRow();
			try {
				getInput();
			} catch(IllegalArgumentException e) {
				
			}
		}
	}
	
	public int getInput() {
		Scanner scan = new Scanner(System.in);
		int output = -1;
		if(scan.hasNextInt())
			output = scan.nextInt();
		scan.close();
		if(output <= 0 || output > 3)
			throw new IllegalArgumentException();
		
		return output;
	}
	
	public boolean isFieldFree(int x, int y) {
		return false;
	}
	
	public void clearBoard() {
		board.clear();
	}
	
	public Board getBoard() {
		return board;
	}
	
}
