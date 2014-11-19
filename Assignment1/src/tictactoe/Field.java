package tictactoe;

public class Field {
	
	public FieldState state;
	
	public Field() {
		state = FieldState.Empty;
	}
	
	public Field(FieldState fs) {
		state = fs;
	}
	
	public FieldState getValue() {
		return state;
	}
	
	public void setValue(FieldState fs) {
		
	}
	
}
