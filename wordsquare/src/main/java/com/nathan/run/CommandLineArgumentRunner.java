package com.nathan.run;

import com.nathan.wordsquarelogic.IWordSquareFactory;
import com.nathan.wordsquarelogic.WordSquareFactoryMethod;
import com.nathan.wordsquarelogic.strategies.Strategy;

public class CommandLineArgumentRunner implements IWordSquareRunner{

	private String[] args;
	private IWordSquareFactory factory;
	
	public CommandLineArgumentRunner(String [] args) {
		this.args = args;
		factory = new WordSquareFactoryMethod();
	}
	
	public void run() {
		if(! new ValidInputChecker().isArgumentValid(this.args)) { 
			return;
		}
		System.out.println("\nRESULTS: ");
		new PrettyPrinter().printWordSquare(factory.createWordSquare(Strategy.TRIE_NODE_STRATEGY, Integer.parseInt(this.args[0]), this.args[1]));
		System.out.println("\n================================\n");
	}
	
}
