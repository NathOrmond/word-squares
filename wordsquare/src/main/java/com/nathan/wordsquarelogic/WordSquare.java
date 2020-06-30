package com.nathan.wordsquarelogic;

import com.nathan.wordsquarelogic.strategies.IWordSquareStrategy;

public class WordSquare implements IWordSquare {
	
	private int squareSide;
	private String input = null;
	private char[][] wordSquare = null;
	private IWordSquareStrategy strategy;
	
	public WordSquare(IWordSquareStrategy strategy, int squareSide, String input) {
		this.strategy = strategy;
		this.squareSide = squareSide;
		this.input = input;
		this.wordSquare = strategy.calculateWordSquareArray(this.squareSide, this.input);
	}

	public char[][] getWordSquareArray() {	
		return this.wordSquare;
	}

	public boolean isNUll() {
		return false;
	}

	public int squareSize() {
		return this.squareSide;
	}

}
