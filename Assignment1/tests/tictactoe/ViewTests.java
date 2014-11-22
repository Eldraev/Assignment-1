package tictactoe;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tictactoe.View.View;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
	
	@After
	public void resetOutput() {
		System.setOut(originalOutput);
	}
	
}
