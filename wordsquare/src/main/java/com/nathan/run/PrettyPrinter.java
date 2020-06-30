package com.nathan.run;

import com.nathan.wordsquarelogic.IWordSquare;

public class PrettyPrinter {
	
	public void printWordSquare(IWordSquare wordSquare) { 
		for(int row = 0; row < wordSquare.squareSize(); row ++) {
			System.out.print("\n");
			for(char c : wordSquare.getWordSquareArray()[row]) { 
				System.out.print(" " + c );
			}
		}
	}
	
}
