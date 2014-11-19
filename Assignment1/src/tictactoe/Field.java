package tictactoe;

public class Field {
	
	public FieldState state;
	
	public Field() {
		setValue(FieldState.Empty);
	}
	
	public Field(FieldState fs) {
		setValue(fs);
	}
	
	public FieldState getValue() {
		return state;
	}
	
	public void setValue(FieldState fs) {
		state = fs;
	}
	
}
