package com.nathan.wordsquarelogic;

import com.nathan.wordsquarelogic.strategies.ReadableTrieNodeStrategy;
import com.nathan.wordsquarelogic.strategies.Strategy;
import com.nathan.wordsquarelogic.strategies.TrieNodeStrategy;

public class WordSquareFactoryMethod implements IWordSquareFactory {

	/**
	 * Returns a word square created according to a desired strategy.
	 * 
	 * @param strategy (Enum)
	 * @param squareSide (int)
	 * @param inputString (String)
	 * @returns IWordSquare
	 */
	public IWordSquare createWordSquare(Strategy strategy, int squareSide, String inputString) {
		switch(strategy) { 
			case TRIE_NODE_STRATEGY:
				return new WordSquare(new TrieNodeStrategy(), squareSide, inputString);
			case READABLE_TRIE_NODE_STRATEGY: 
				return new WordSquare(new ReadableTrieNodeStrategy() , squareSide, inputString);
			default: 
				return new NullWordSquare();
		}
	}

}
