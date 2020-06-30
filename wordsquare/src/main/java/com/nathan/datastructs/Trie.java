package com.nathan.datastructs;

import java.util.List;

public class Trie {

	public TrieNode buildTrie(String[] words) {
		TrieNode root = new TrieNode();
		for (String word : words) {
			TrieNode current = root;
			for (char c : word.toCharArray()) {
				int index = c - 'a';
				if (current.children[index] == null) {
					current.children[index] = new TrieNode();
				}
				current = current.children[index];
			}
			current.isWord = true;
		}
		return root;
	}

	public TrieNode search(TrieNode root, String prefix) {
		TrieNode current = root;
		for (char c : prefix.toCharArray()) {
			int index = c - 'a';
			if (current.children[index] == null) {
				return null;
			}
			current = current.children[index];
		}
		return current;
	}

	public String getPrefix(List<String> square, int index) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < index; i++) {
			sb.append(square.get(i).charAt(index));
		}
		return sb.toString();
	}

	public void getChildren(TrieNode node, String s, List<String> children) {
		if (node.isWord) {
			children.add(s);
			return;
		}

		for (int i = 0; i < 26; i++) {
			if (node.children[i] != null) {
				getChildren(node.children[i], s + (char) ('a' + i), children);
			}
		}
	}

}
