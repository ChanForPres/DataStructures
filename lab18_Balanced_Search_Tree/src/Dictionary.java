import sun.text.normalizer.Trie;

import java.util.HashMap;

public class Dictionary {

	/*
	 * Since a trie node can have so many children, the children it has are
	 * stored in a map.
	 */
	private HashMap<Character, TrieNode> myStartingLetters;

	public Dictionary() {
		myStartingLetters = new HashMap<>();
	}

	public void printKeys() {
		for (Character key:this.myStartingLetters.keySet()) {
			System.out.println(key);
			TrieNode nextChild = this.myStartingLetters.get(key);
			printMore(nextChild, key);
		}
	}

	public void printMore(TrieNode nextChild, Character keyChr) {
		for (Character key:nextChild.myNextLetters.keySet()) {
			System.out.println("more: "+key);
		}
	}


	public void insert(String str) {
		// if any part of str was not saved at all
		if (!myStartingLetters.containsKey(str.charAt(0))) {
			myStartingLetters.put(str.charAt(0), new TrieNode());
		}
		// if part of str was saved already
		insertStr(str.substring(1), myStartingLetters.get(str.charAt(0)));
	}

	// recursively insert a new word into the trie tree
	private void insertStr(String str, TrieNode trieNode) {
		final TrieNode nextChild;
		// if trie contains the next word, then next child will be the value of trieNode
		if (trieNode.myNextLetters.containsKey(str.charAt(0))) {
			nextChild = trieNode.myNextLetters.get(str.charAt(0));
		}
		// if not, we need to create one
		else {
			nextChild = new TrieNode();
			trieNode.myNextLetters.put(str.charAt(0), nextChild);
		}

		// end of the word
		if (str.length() == 1) {
			nextChild.endOfWord = true;
			return;
		}
		// recursively insert the next word in nextChild
		else {
			insertStr(str.substring(1), nextChild);
		}
	}


	/**
	 * Associates the input word with the input definition in the dictionary.
	 */
	public void addDefinition(String word, String definition) {
		if (!myStartingLetters.containsKey(word.charAt(0))) {
			throw new IllegalStateException("The word is not in the dictionary");
		}
		addDefnHelper(word.substring(1), definition, myStartingLetters.get(word.charAt(0)));
	}

	public void addDefnHelper(String word, String definition, TrieNode hashMap) {
		final TrieNode nextTrie;
		if (hashMap.myNextLetters.containsKey(word.charAt(0))) {
			nextTrie = hashMap.myNextLetters.get(word.charAt(0));
		} else {
			throw new IllegalStateException("The word is not in the dictionary");
		}
		// if end of word, add definition
		if (nextTrie.endOfWord) {
			nextTrie.myDefinition = definition;
			return;
		} else {
			addDefnHelper(word.substring(1), definition, nextTrie);
		}
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */
	public String lookupDefinition(String word) {
		if (!myStartingLetters.containsKey(word.charAt(0))) {
			throw new IllegalStateException("The word is not in the dictionary");
		}
		return lookupDefnHelper(word.substring(1), myStartingLetters.get(word.charAt(0)));
	}

	private String lookupDefnHelper(String substring, TrieNode trieNode) {
		final TrieNode nextTrie2;
		System.out.println("substring: "+substring);
		if (trieNode.myNextLetters.containsKey(substring.charAt(0))) {
			nextTrie2 = trieNode.myNextLetters.get(substring.charAt(0));
		} else {
			throw new IllegalStateException("The word is not in the dictionary");
		}
		if (nextTrie2.endOfWord && nextTrie2.myDefinition != null) {
			return nextTrie2.myDefinition;
		} else if (nextTrie2.endOfWord && nextTrie2.myDefinition == null) {
			return null;
		} else {
			return lookupDefnHelper(substring.substring(1), trieNode.myNextLetters.get(substring.charAt(0)));
		}
	}


	private class TrieNode {
		// like a recursion
		private HashMap<Character, TrieNode> myNextLetters;

		private boolean endOfWord = false;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition = null;

		private TrieNode() {
			myNextLetters = new HashMap<>();
		}
	}
}