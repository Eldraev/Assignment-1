package tictactoe.View;

import tictactoe.Model.Field;
import tictactoe.Model.FieldState;

public class View {

	public View() {
		
	}
	
	public void askForColumn() {
		System.out.print("Enter Column: ");
	}
	
	public void askForRow() {
		System.out.print("Enter Row: ");
	}
	
	public void notifyPlayer() {
		System.out.println("It's your turn: ");
	}
	
	public void printFieldState(FieldState fs) {
		if(fs == null)
			throw new IllegalArgumentException("Invalid Input!");
		switch(fs) {
		case Circle: System.out.print("O "); break;
		case Cross: System.out.print("X "); break;
		case Empty: System.out.print("  "); break;
		}
	}
	
	public void drawBoard(Field[][] board) {
		if(board == null)
			throw new IllegalArgumentException("Invalid Input!");
		for(int y = 0; y<board.length; y++) {
			for(int x = 0; x<board[y].length; x++) {
				printFieldState(board[y][x].getValue());
			}
			System.out.print("\n");
		}
	}
	
	public void printTie() {
		System.out.println("The match is a tie. A new game has been started.");
	}
	
	public void printError() {
		System.out.println("Wrong Input please try again.");
	}
	
	public void printWinner(boolean playerWon) {
		if(playerWon)
			System.out.println("You won the game. A new game has been started.");
		else
			System.out.println("You lost the game. A new game has been started.");
	}
	
}
