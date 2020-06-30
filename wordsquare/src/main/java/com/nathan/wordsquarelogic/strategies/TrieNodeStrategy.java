package com.nathan.wordsquarelogic.strategies;

import java.net.URL;
import java.util.Scanner;

/**
 * Trie Node Strategy Adapted From <https://leetcode.com/problems/word-squares/> Q-425 Discussion
 */

public class TrieNodeStrategy implements IWordSquareStrategy {

	private static int squareSide;
	private static int ALPHABET_LEN = 26;

	public char[][] calculateWordSquareArray(int squareSide, String input) {
		this.squareSide = squareSide;
		char[] charset = input.toCharArray();

		Node wordTrie = new Node();
		int[] charFreq = new int[ALPHABET_LEN];
		for (char c : charset)
			charFreq[c - 'a']++;

		URL url;
		Scanner sc;
		try {

			url = new URL("http://norvig.com/ngrams/enable1.txt");
			sc = new Scanner(url.openStream());

			//TODO Not particularly happy with sequential search through dictionary here!
			String dictWord;
			while (sc.hasNext()) {
				dictWord = sc.next();
				if (dictWord.length() == squareSide && fitsInLetterBank(dictWord, charFreq)) {
					Node curNode = wordTrie;
					for (int i = 0; i < dictWord.length(); i++) {
						int c = dictWord.charAt(i) - 'a';
						if (curNode.children[c] == null)
							curNode.children[c] = new Node(c);
						curNode = curNode.children[c];
					}
				}
			}

			sc.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		Node[][] mat = new Node[squareSide][squareSide + 1];
		for (int i = 0; i < mat.length; i++)
			mat[i][0] = wordTrie;
		int[] bank = charFreq.clone();
		if (rec(0, 1, mat, bank)) {
			char[][] result = new char[squareSide][squareSide];
			for (int r = 0; r < squareSide; r++) {
				for (int c = 0; c < squareSide; c++) {
					result[r][c] = (char) (mat[r][c + 1].val + 'a');
				}
			}
			return result;
		} else {
			return null;
		}
	}

	/**
	 * fills out the word bank, one character at a time. Keep in mind that the first
	 * column of every row contains the root node, so indexes needed to be adjusted
	 * accordingly.
	 * 
	 * @return
	 */
	private boolean rec(int r, int c, Node[][] mat, int[] bank) {
		int incrAmt = r == c - 1 ? 1 : 2; // need 1 for a diagonal, 2 otherwise (since it's mirrored)

		for (int l = 0; l < 26; l++) {
			Node node = mat[r][c - 1].children[l];
			Node nodeMirrorSide = mat[c - 1][r].children[l];
			if (node != null && nodeMirrorSide != null && bank[l] >= incrAmt) {

				mat[r][c] = node;
				mat[c - 1][r + 1] = nodeMirrorSide;
				bank[l] -= incrAmt; // remove letter from bank

				// try next position
				if (c == squareSide) { 									// no more columns in this row
					if ((r == squareSide - 1) || (rec(r + 1, r + 2, mat, bank)) ) { // no more rows either; end of word square (SUCCESS)
						return true;
					}
				} else if (rec(r, c + 1, mat, bank)) { // move to next column
					return true;
				}

				bank[l] += incrAmt; // add letter back to bank
			}
		}
		return false; // current branch cannot produce a valid word square
	}

	/**
	 * returns whether the word can be used, given the letters provided. Takes into
	 * account the fact that all but one of the letters (the one on the diagonal) in
	 * the word must occur twice in the final word square.
	 */
	private boolean fitsInLetterBank(String word, int[] charFreq) {
		int[] charsUsed = new int[26];
		boolean diagonalUsed = false;
		for (int i = 0; i < word.length(); i++) {
			int c = word.charAt(i) - 'a';

			int spaceLeft = charFreq[c] - charsUsed[c];
			if (spaceLeft > 1) { 							// try fitting letter in a non-diagonal slot
				charsUsed[c] += 2;
			} else if (spaceLeft == 1 && !diagonalUsed) { 	// else, try fitting it in the diagonal slot
				charsUsed[c] += 1;
				diagonalUsed = true;
			} else { 										// no space for letter
				return false;
			}
		}
		return true;
	}

	/**
	 * Private inner class, node data struct Used to build the trie. Sacrifices
	 * space for lightning fast lookups.
	 */
	private class Node {
		int val;
		Node[] children;

		Node() {
			children = new Node[TrieNodeStrategy.ALPHABET_LEN];
		}

		Node(int val) {
			this();
			this.val = val;
		}
	}

}
