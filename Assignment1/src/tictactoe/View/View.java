package tictactoe.View;

import tictactoe.Model.FieldState;

public class View {

	public View() {
		
	}
	
	public void notifyPlayer() {
		System.out.println("It's your turn: ");
	}
	
	public void printFieldState(FieldState fs) {
		switch(fs) {
		case Circle: System.out.println("O"); break;
		case Cross: System.out.println("X"); break;
		}
	}
	
}
