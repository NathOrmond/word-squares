package com.naimuri.run;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import com.nathan.run.CommandLineArgumentRunner;

public class CommandLineArgumentRunnerTest {

	String[] validTestInput = { "4", "eeeeddoonnnsssrv" };
	String[] invalidTestInput = { "ba", "9999900" };
	public boolean valid;

	@After
	public void restoreSystemInputOutput() {
		System.setErr(System.err);
	}

	@Test
	public void runValidTest() {
		valid = true;
		System.setErr(new PrintStream(new CustomOutputStream()));
		CommandLineArgumentRunner testClass = new CommandLineArgumentRunner(validTestInput);
		testClass.run();
		Assert.assertEquals(true, valid);
	}

	@Test
	public void runInvalidTest() {
		valid = true;
		System.setErr(new PrintStream(new CustomOutputStream()));
		CommandLineArgumentRunner testClass = new CommandLineArgumentRunner(invalidTestInput);
		testClass.run();
		Assert.assertEquals(false, valid);
	}

	class CustomOutputStream extends OutputStream {
		@Override
		public final void write(int b) throws IOException {
			valid = false;
			System.out.print((char) b);
		}
	}

}
