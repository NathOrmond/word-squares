package com.nathan.run;

import java.util.Scanner;

import com.nathan.wordsquarelogic.IWordSquare;
import com.nathan.wordsquarelogic.IWordSquareFactory;
import com.nathan.wordsquarelogic.WordSquareFactoryMethod;
import com.nathan.wordsquarelogic.strategies.Strategy;

public class InteractiveRunner implements IWordSquareRunner {

	private static final int ARGS_LEN = 2;
	private IWordSquareFactory factory;
	private Scanner sc;

	public InteractiveRunner() {
		factory = new WordSquareFactoryMethod();
	}

	public void run() {
		String[] args = new String[ARGS_LEN];
		this.sc = new Scanner(System.in);
		args[0] = getUserString("Enter the length of the word square's side: ");
		args[1] = getUserString("Enter input String: ");
		if (!new ValidInputChecker().isArgumentValid(args)) {
			return;
		}
		System.out.println("\nRESULTS: ");
		new PrettyPrinter().printWordSquare(factory.createWordSquare(Strategy.TRIE_NODE_STRATEGY, Integer.parseInt(args[0]), args[1]));
		System.out.println("\n================================\n");
	}

	private String getUserString(String message) {
		System.out.println(message);
		String rv = this.sc.next();
		return rv;
	}

}
