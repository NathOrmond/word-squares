package com.nathan.datastructs;

public class TrieNode {
	TrieNode[] children;
	boolean isWord;

	TrieNode() {
		children = new TrieNode[26];
	}
}
