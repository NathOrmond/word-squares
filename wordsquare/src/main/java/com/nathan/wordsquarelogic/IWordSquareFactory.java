package com.nathan.wordsquarelogic;

import com.nathan.wordsquarelogic.strategies.Strategy;

public interface IWordSquareFactory {
	
	IWordSquare createWordSquare(Strategy strategy, int squareSide, String inputString);

}
