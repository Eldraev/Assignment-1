package tictactoe.Controller;

import java.util.Random;
import java.util.Scanner;

import tictactoe.Model.Board;
import tictactoe.Model.FieldState;
import tictactoe.View.View;

public class Game {

	private Board board;
	private View view;
	private boolean playing;
	private Scanner scan;
	
	public Game(View v) {
		view = v;
		board = new Board();
		playing = true;
		scan = new Scanner(System.in);
	}
	
	public boolean play() {
		view.drawBoard(board.getBoardArray());
		view.notifyPlayer();
		view.askForColumn();
		int inCol = -1;
		int inRow = -1;
		try {
			inCol = getInput();
		} catch(IllegalArgumentException e) {
			view.printError();
			return false;
		}
		view.askForRow();
		try {
			inRow = getInput();
		} catch(IllegalArgumentException e) {
			view.printError();
			return false;
		}
		try{
		setField(inRow,inCol,FieldState.Circle);
		} catch (IllegalArgumentException e) {
			view.printError();
			return false;
		}
		if(checkForWin()) {
			view.printWinner(true);
			clearBoard();
			return true;
		}
		doAITurn();
		if(checkForWin()) {
			view.printWinner(false);
			clearBoard();
		}
		return true;
	}
	
	public int getInput() {
		int output = -1;
		if(scan.hasNextInt())
			output = scan.nextInt();
		if(output <= 0 || output > 3)
			throw new IllegalArgumentException();
		
		return output;
	}
	
	public Scanner getScanner() {
		return scan;
	}
	
	public void setScanner(Scanner s) {
		scan = s;
	}
	
	public boolean isFieldFree(int x, int y) {
		return board.getField(x, y).getValue() == FieldState.Empty;
	}
	
	public void setPlaying(boolean b) {
		playing = b;
	}
	
	public boolean isPlaying() {
		return playing;
	}
	
	public void clearBoard() {
		board.clear();
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void setField(int x, int y, FieldState fs) {
		x--;
		y--;
		if(!isFieldFree(x,y))
			throw new IllegalArgumentException();
		board.getField(x, y).setValue(fs);
	}
	
	public void doAITurn() {
		Random rand = new Random();
		int randX;
		int randY;
		do{
			randX = rand.nextInt(3);
			randX++;
			randY = rand.nextInt(3);
			randY++;
			try {
				setField(randX,randY,FieldState.Cross);
				break;
			} catch(IllegalArgumentException e) {
				
			}
		} while(true);
	}
	
	public boolean checkForWin() {
		for(int i=0;i<3;i++) {
			if(board.getField(i, 0).getValue() == board.getField(i, 1).getValue() 
			&& board.getField(i, 1).getValue() == board.getField(i, 2).getValue() 
			&& board.getField(i, 0).getValue() != FieldState.Empty)
				return true;
		} 
		for(int i=0;i<3;i++) {
			if(board.getField(0, i).getValue() == board.getField(1, i).getValue() 
			&& board.getField(1, i).getValue() == board.getField(2, i).getValue() 
			&& board.getField(0, i).getValue() != FieldState.Empty)
		return true;
		}
		
		if(board.getField(0, 0).getValue() == board.getField(1, 1).getValue() 
		&& board.getField(1, 1).getValue() == board.getField(2, 2).getValue()
		&& board.getField(1, 1).getValue() != FieldState.Empty)
			return true;
		
		if(board.getField(0, 2).getValue() == board.getField(1, 1).getValue() 
		&& board.getField(1, 1).getValue() == board.getField(2, 0).getValue()
		&& board.getField(1, 1).getValue() != FieldState.Empty)
			return true;
		
		return false;
	}
	
}
