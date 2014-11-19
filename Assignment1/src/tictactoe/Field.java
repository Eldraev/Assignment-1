package tictactoe;

public class Field {
	
	public FieldState state;
	
	public Field() {
		setValue(FieldState.Empty);
	}
	
	public Field(FieldState fs) {
		if(fs == null) {
			throw new IllegalArgumentException("Invalid FieldState!");
		}
		setValue(fs);
	}
	
	public FieldState getValue() {
		return state;
	}
	
	public void setValue(FieldState fs) {
		state = fs;
	}
	
}
