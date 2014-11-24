package tictactoe;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import tictactoe.Model.Field;
import tictactoe.Model.FieldState;
import tictactoe.View.View;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.anyString;

public class ViewTests {
	
	private PrintStream originalOutput;
	private PrintStream mockStream;
	
	@Before
	public void mockOutput() {
		originalOutput = System.out;
		mockStream = mock(PrintStream.class);
		System.setOut(mockStream);
	}
	
	@Test
	public void shouldWriteMessageToPlayer() {
		View v = new View();
		v.notifyPlayer();
		verify(mockStream).println("It's your turn: ");
	}
	
	@Test
	public void shouldPrintFieldState() {
		testFieldStateOutput(FieldState.Circle, "O");
		testFieldStateOutput(FieldState.Cross, "X");
		testFieldStateOutput(FieldState.Empty, " ");
	}
	
	@Test
	public void shouldThrowOnNullPrint() {
		View v = new View();
		try {
			v.printFieldState(null);
			fail();
		} catch(IllegalArgumentException e) {
			
		}
	}
	
	@Test
	public void shouldDrawEmptyBoard() {
		View v = new View();
		Field[][] testBoard = new Field[3][3];
		for(int y = 0; y<testBoard.length; y++) {
			for(int x = 0; x<testBoard[y].length; x++) {
				testBoard[y][x] = new Field();
			}
		}
		v.drawBoard(testBoard);
		InOrder inOrder = inOrder(mockStream);
		inOrder.verify(mockStream, times(3)).print(" ");
		inOrder.verify(mockStream, times(1)).print("\n");
		inOrder.verify(mockStream, times(3)).print(" ");
		inOrder.verify(mockStream, times(1)).print("\n");
		inOrder.verify(mockStream, times(3)).print(" ");
		inOrder.verify(mockStream, times(1)).print("\n");
	}
	
	@Test
	public void shouldDrawCrossedBoard() {
		View v = new View();
		Field[][] testBoard = new Field[3][3];
		for(int y = 0; y<testBoard.length; y++) {
			for(int x = 0; x<testBoard[y].length; x++) {
				testBoard[y][x] = new Field(FieldState.Cross);
			}
		}
		v.drawBoard(testBoard);
		InOrder inOrder = inOrder(mockStream);
		inOrder.verify(mockStream, times(3)).print("X");
		inOrder.verify(mockStream, times(1)).print("\n");
		inOrder.verify(mockStream, times(3)).print("X");
		inOrder.verify(mockStream, times(1)).print("\n");
		inOrder.verify(mockStream, times(3)).print("X");
		inOrder.verify(mockStream, times(1)).print("\n");
	}
	
	@Test
	public void shouldDrawCircledBoard() {
		View v = new View();
		Field[][] testBoard = new Field[3][3];
		for(int y = 0; y<testBoard.length; y++) {
			for(int x = 0; x<testBoard[y].length; x++) {
				testBoard[y][x] = new Field(FieldState.Circle);
			}
		}
		v.drawBoard(testBoard);
		InOrder inOrder = inOrder(mockStream);
		inOrder.verify(mockStream, times(3)).print("O");
		inOrder.verify(mockStream, times(1)).print("\n");
		inOrder.verify(mockStream, times(3)).print("O");
		inOrder.verify(mockStream, times(1)).print("\n");
		inOrder.verify(mockStream, times(3)).print("O");
		inOrder.verify(mockStream, times(1)).print("\n");
	}
	
	@After
	public void resetOutput() {
		System.setOut(originalOutput);
	}
	
	private void testFieldStateOutput(FieldState fs, String output) {
		View v = new View();
		v.printFieldState(fs);
		verify(mockStream).print(output);
	}
	
}
