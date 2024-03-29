package tictactoe;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import tictactoe.Controller.Game;
import tictactoe.Model.Board;
import tictactoe.Model.Field;
import tictactoe.Model.FieldState;
import tictactoe.View.View;

public class GameTests {
	
	private InputStream originalInput;
	private ByteArrayInputStream mockInput;
	
	@Before
	public void mockInput() {
		originalInput = System.in;
		mockInput = mock(ByteArrayInputStream.class);
		System.setIn(mockInput);
	}
	
	@Test
	public void shouldReturnFalseOnCatch() {
		View mockView = mock(View.class);
		Game g = new Game(mockView);
		
		assertFalse(g.play());
		verify(mockInput, times(1)).read(any(byte[].class),any(int.class),any(int.class));
	}
	
	@Test
	public void shouldWriteMessageOnReturnFalse() {
		View mockView = mock(View.class);
		Game g = new Game(mockView);
		g.play();
		verify(mockView).printError();
		}
	
	@Test
	public void shouldGetScannerAfterSettingIt() {
		Game g = new Game(new View());
		Scanner tryOut = new Scanner(System.in);
		g.setScanner(tryOut);
		assertEquals(g.getScanner(),tryOut);
	}
	
	@Test
	public void shouldInitializeWithScanner() {
		Game g = new Game(new View());
		assertFalse(g.getScanner()==null);
	}
	
	@Test
	public void shouldEndGameOnTie() {
		System.setIn(new ByteArrayInputStream("3 3".getBytes()));
		View mockView = mock(View.class);
		Game g = new Game(mockView);
		g.setField(1, 1, FieldState.Cross);
		g.setField(1, 2, FieldState.Circle);
		g.setField(1, 3, FieldState.Cross);
		g.setField(2, 1, FieldState.Circle);
		g.setField(2, 2, FieldState.Cross);
		g.setField(2, 3, FieldState.Circle);
		g.setField(3, 1, FieldState.Circle);
		g.setField(3, 2, FieldState.Cross);
		g.play();
		verify(mockView).printTie();
		assertTrue(getNumberStatesInField(g.getBoard().getBoardArray(), FieldState.Empty)==9);
	}
	
	@Test
	public void shouldAskForInputAfterDialog() {
		View mockView = mock(View.class);
		Game g = new Game(mockView);
		g.play();
		InOrder inOrder = inOrder(mockView, mockInput);
		inOrder.verify(mockView).drawBoard(g.getBoard().getBoardArray());
		inOrder.verify(mockView).notifyPlayer();
		inOrder.verify(mockView).askForColumn();
		inOrder.verify(mockInput).read(any(byte[].class),any(int.class),any(int.class));
	}
	
	@Test
	public void shouldCallIsTie() {
		View mockView = mock(View.class);
		Game g = new Game(mockView);
		g.isTie();
	}
	
	@Test
	public void shouldFindTie() {
		View mockView = mock(View.class);
		Game g = new Game(mockView);
		g.setField(1, 1, FieldState.Cross);
		g.setField(1, 2, FieldState.Circle);
		g.setField(1, 3, FieldState.Cross);
		g.setField(2, 1, FieldState.Circle);
		g.setField(2, 2, FieldState.Cross);
		g.setField(2, 3, FieldState.Circle);
		g.setField(3, 1, FieldState.Circle);
		g.setField(3, 2, FieldState.Cross);
		g.setField(3, 3, FieldState.Circle);
		assertTrue(g.isTie());
	}
	
	@Test
	public void shouldSetACircleAt11ByPlaying() {
		System.setIn(new ByteArrayInputStream("1 1".getBytes()));
		Game g = new Game(new View());
		g.play();
		Field[][] f = g.getBoard().getBoardArray();
		assertEquals(f[0][0].getValue(),FieldState.Circle);
	}
	
	@Test
	public void shouldCatchSetFieldException() {
		System.setIn(new ByteArrayInputStream("1 1 1 1".getBytes()));
		View mockView = mock(View.class);
		Game g = new Game(mockView);
		g.play();
		g.play();
		verify(mockView).printError();
	}
	
	@Test
	public void shouldNotDoTurnOnSetFieldException() {
		System.setIn(new ByteArrayInputStream("1 1 1 1".getBytes()));
		View mockView = mock(View.class);
		Game g = new Game(mockView);
		g.play();
		g.play();
		assertTrue(getNumberStatesInField(g.getBoard().getBoardArray(), FieldState.Cross)==1);
		
	}
	
	@Test
	public void shouldLetPlayerWin() {
		System.setIn(new ByteArrayInputStream("1 1".getBytes()));
		View mockView = mock(View.class);
		Game g = new Game(mockView);
		g.setField(1, 2, FieldState.Circle);
		g.setField(1, 3, FieldState.Circle);
		g.play();
		verify(mockView).printWinner(true);
	}
	
	@Test
	public void shouldStartNewGameAfterWin() {
		System.setIn(new ByteArrayInputStream("1 1".getBytes()));
		View mockView = mock(View.class);
		Game g = new Game(mockView);
		g.setField(1, 2, FieldState.Circle);
		g.setField(1, 3, FieldState.Circle);
		g.play();
		assertTrue(getNumberStatesInField(g.getBoard().getBoardArray(), FieldState.Empty)==9);
	}
	
	@Test
	public void shouldStartNewGameAfterAIWin() {
		System.setIn(new ByteArrayInputStream("1 1".getBytes()));
		View mockView = mock(View.class);
		Game g = new Game(mockView);
		g.setField(1, 2, FieldState.Cross);
		g.setField(1, 3, FieldState.Cross);
		g.setField(2, 2, FieldState.Cross);
		g.setField(2, 3, FieldState.Cross);
		g.play();
		assertTrue(getNumberStatesInField(g.getBoard().getBoardArray(), FieldState.Empty)==9);
	}
	
	@Test
	public void shouldRedrawFieldAfterWin() {
		System.setIn(new ByteArrayInputStream("1 1".getBytes()));
		View mockView = mock(View.class);
		Game g = new Game(mockView);
		g.setField(1, 2, FieldState.Circle);
		g.setField(1, 3, FieldState.Circle);
		g.play();
		verify(mockView, times(2)).drawBoard(any(Field[][].class));
	}
	
	@Test
	public void shouldRedrawFieldAfterAIWin() {
		System.setIn(new ByteArrayInputStream("1 1".getBytes()));
		View mockView = mock(View.class);
		Game g = new Game(mockView);
		g.setField(1, 2, FieldState.Cross);
		g.setField(1, 3, FieldState.Cross);
		g.setField(2, 2, FieldState.Cross);
		g.setField(2, 3, FieldState.Cross);
		g.play();
		verify(mockView, times(2)).drawBoard(any(Field[][].class));
	}
	
	@Test
	public void shouldLetAIWin() {
		System.setIn(new ByteArrayInputStream("1 1".getBytes()));
		View mockView = mock(View.class);
		Game g = new Game(mockView);
		g.setField(1, 2, FieldState.Cross);
		g.setField(1, 3, FieldState.Cross);
		g.setField(2, 2, FieldState.Cross);
		g.setField(2, 3, FieldState.Cross);
		g.play();
		verify(mockView).printWinner(false);
	}
	
	@Test
	public void shouldReturnParsedNumber() {
		int testInt = 2;
		System.setIn(new ByteArrayInputStream( (testInt+"").getBytes()));
		Game g = new Game(new View());
		int i = g.getInput();
		assertEquals(i,testInt);
	}
	
	@Test
	public void shouldThrowOnNegative() {
		int testInt = -2;
		Game g = new Game(new View());
		System.setIn(new ByteArrayInputStream( (testInt+"").getBytes()));
		try{
			g.getInput();
			fail();
		} catch(IllegalArgumentException e) {
			
		} 
	}
	
	@Test
	public void shouldThrowOnGreaterThanThree() {
		int testInt = 6;
		Game g = new Game(new View());
		System.setIn(new ByteArrayInputStream( (testInt+"").getBytes()));
		try{
			g.getInput();
			fail();
		} catch(IllegalArgumentException e) {
			
		} 
	}
	
	@Test
	public void shouldThrowOnZero() {
		int testInt = 0;
		Game g = new Game(new View());
		System.setIn(new ByteArrayInputStream( (testInt+"").getBytes()));
		try{
			g.getInput();
			fail();
		} catch(IllegalArgumentException e) {
			
		} 
	}
	
	@Test
	public void shouldGiveFreeField() {
		Game g = new Game(new View());
		assertTrue(g.isFieldFree(1,1));	
	}
	
	@Test
	public void shouldNotGiveFreeField() {
		Game g = new Game(new View());
		g.getBoard().getField(1, 1).setValue(FieldState.Cross);
		assertFalse(g.isFieldFree(1, 1));
	}
	
	@Test
	public void shouldStartNewBoard() {
		Game g = new Game(new View());
		g.getBoard().getField(1, 1).setValue(FieldState.Circle);
		g.clearBoard();
		assertEquals(g.getBoard().getField(1, 1).getValue(),FieldState.Empty);
	}
	
	@Test
	public void shouldGetBoardThatIsNotNull() {
		Game g = new Game(new View());
		assertTrue(g.getBoard()!=null);
	}
	
	 @Test
	 public void shouldGetBoardThatKeepsValues() {
		 Game g = new Game(new View());
		 g.getBoard().clear();
		 g.getBoard().getField(1, 1).setValue(FieldState.Circle);
		 assertEquals(g.getBoard().getField(1, 1).getValue(),FieldState.Circle);
	 }
	 
	 @Test
	 public void shouldSetAndGetField11() {
		 Game g = new Game(new View());
		 g.setField(1, 1, FieldState.Circle);
		 assertEquals(g.getBoard().getField(0, 0).getValue(),FieldState.Circle);
	 }
	 
	 @Test
	 public void shouldThrowOnFieldIsSet() {
		 Game g = new Game(new View());
		 g.setField(1, 1, FieldState.Circle);
		 try{
			 g.setField(1, 1, FieldState.Cross);
			 fail();
		 } catch(IllegalArgumentException e) {
			 
		 }
	 }
	 
	 @Test
	 public void shouldOnlyDoOneAITurn() {
		 System.setIn(new ByteArrayInputStream("1 1".getBytes()));
		 Game g = new Game(new View());
		 g.play();
		 Field[][] f = g.getBoard().getBoardArray();
		 int crossCounter = 0;
		 for(int y=0;y<3;y++) {
			 for(int x=0;x<3;x++) {
				 if(f[x][y].getValue() == FieldState.Cross)
					 crossCounter++;
			 }
		 }
		 assertTrue(crossCounter==1);
	 }
	 
	 @Test
	 public void shouldLessEmptyFieldsAfterAITurn() {
		 Game g = new Game(new View());
		 g.doAITurn();
		 Field[][] f = g.getBoard().getBoardArray();
		 int previousEmpty = 9;
		 int emptyCounter = 0;
		 for(int y=0;y<3;y++) {
			 for(int x=0;x<3;x++) {
				 if(f[x][y].getValue() == FieldState.Empty)
					 emptyCounter++;
			 }
		 }
		 assertTrue(previousEmpty>emptyCounter);
		 previousEmpty = emptyCounter;
		 emptyCounter = 0;
		 g.doAITurn();
		 f = g.getBoard().getBoardArray();
		 for(int y=0;y<3;y++) {
			 for(int x=0;x<3;x++) {
				 if(f[x][y].getValue() == FieldState.Empty)
					 emptyCounter++;
			 }
		 }
		 assertTrue(previousEmpty>emptyCounter);
	 }
	 
	 @Test
	 public void shouldFindWin() {
		 Game g = new Game(new View());
		 g.setField(1, 1, FieldState.Circle);
		 g.setField(1, 2, FieldState.Circle);
		 g.setField(1, 3, FieldState.Circle);
		 assertTrue(g.checkForWin());
	 }
	 
	 @Test
	 public void shouldNotFindWin() {
		 Game g = new Game(new View());
		 g.setField(1, 1, FieldState.Circle);
		 g.setField(1, 2, FieldState.Cross);
		 g.setField(1, 3, FieldState.Circle);
		 assertFalse(g.checkForWin());
	 }
	 
	 @Test
	 public void shouldAlsoNotFindWin() {
		 Game g = new Game(new View());
		 g.setField(1, 1, FieldState.Circle);
		 g.setField(2, 1, FieldState.Cross);
		 g.setField(3, 1, FieldState.Circle);
		 assertFalse(g.checkForWin());
	 }
	 
	 @Test
	 public void shouldAlsoFindWin() {
		 Game g = new Game(new View());
		 g.setField(1, 1, FieldState.Cross);
		 g.setField(2, 1, FieldState.Cross);
		 g.setField(3, 1, FieldState.Cross);
		 assertTrue(g.checkForWin());
	 }
	 
	 @Test
	 public void shouldGiveWrongRowImput() {
		 System.setIn(new ByteArrayInputStream("1 -1".getBytes()));
		 Game g = new Game(new View());
		 assertFalse(g.play());
	 }
	 
	 @Test
	 public void shouldGiveNoTie() {
		 Game g = new Game(new View());
		 g.setField(1, 1, FieldState.Cross);
		 g.setField(2, 1, FieldState.Cross);
		 g.setField(3, 1, FieldState.Cross);
		 g.setField(1, 2, FieldState.Cross);
		 g.setField(2, 2, FieldState.Cross);
		 g.setField(3, 2, FieldState.Cross);
		 g.setField(1, 3, FieldState.Cross);
		 g.setField(2, 3, FieldState.Cross);
		 g.setField(3, 3, FieldState.Cross);
		 assertFalse(g.isTie());
	 }
	 
	 @Test
	 public void shouldFindWinOnDiagonal() {
		 Game g = new Game(new View());
		 g.setField(1, 1, FieldState.Cross);
		 g.setField(2, 2, FieldState.Cross);
		 g.setField(3, 3, FieldState.Cross);
		 assertTrue(g.checkForWin());
	 }
	 @Test
	 public void shouldFindWinOnOtherDiagonal() {
		 Game g = new Game(new View());
		 g.setField(1, 3, FieldState.Cross);
		 g.setField(2, 2, FieldState.Cross);
		 g.setField(3, 1, FieldState.Cross);
		 assertTrue(g.checkForWin());
	 }
	 
	 @Test
	 public void shouldGetPlaying() {
		 Game g = new Game(new View());
		 assertTrue(g.isPlaying());
	 }
	 
	 @Test
	 public void shouldGetNotPlaying() {
		 Game g = new Game(new View());
		 g.setPlaying(false);
		 assertFalse(g.isPlaying());
	 }
	 
	 private int getNumberStatesInField(Field[][] f, FieldState fs) {
		 int output = 0;
		 for(int y=0;y<3;y++) {
			 for(int x=0;x<3;x++) {
				 if(f[x][y].getValue() == fs)
					 output++;
			 }
		 }
		 return output;
	 }
	 
	 @After
	 public void resetOutput() {
		 System.setIn(originalInput);
	 }
	
}
