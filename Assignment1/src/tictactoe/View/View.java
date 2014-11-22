package tictactoe.View;

import tictactoe.Model.FieldState;

public class View {

	public View() {
		
	}
	
	public void notifyPlayer() {
		System.out.println("It's your turn: ");
	}
	
	public void printFieldState(FieldState fs) {
		System.out.println("O");
	}
	
}
