package tictactoe.View;

import tictactoe.Model.Field;
import tictactoe.Model.FieldState;

public class View {

	public View() {
		
	}
	
	public void notifyPlayer() {
		System.out.println("It's your turn: ");
	}
	
	public void printFieldState(FieldState fs) {
		if(fs == null)
			throw new IllegalArgumentException("Invalid Input!");
		switch(fs) {
		case Circle: System.out.println("O"); break;
		case Cross: System.out.println("X"); break;
		case Empty: System.out.println(" "); break;
		}
	}
	
	public void drawBoard(Field[][] board) {
		System.out.println(" | | \n-----\n | | \n-----\n | | ");
	}
	
}
