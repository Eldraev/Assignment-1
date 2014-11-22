package tictactoe;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
	public void shouldCreateAInstance() {
		new View();
	}
	
	@Test
	public void shouldWriteMessageToPlayer() {
		View v = new View();
		v.notifyPlayer();
		verify(mockStream).println("It's your turn: ");
	}
	
	@Test
	public void shouldPrintFieldStateCircle() {
		View v = new View();
		v.printFieldState(FieldState.Circle);
		verify(mockStream).println("O");
	}
	
	@Test
	public void shouldPrintFieldStateCross() {
		View v = new View();
		v.printFieldState(FieldState.Cross);
		verify(mockStream).println("X");
	}
	
	@After
	public void resetOutput() {
		System.setOut(originalOutput);
	}
	
}
