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
			nextChild.myNextLetters.put(str.charAt(0), nextChild);
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
		System.out.println("word: "+word.charAt(0));
		System.out.println("myNextLetters: "+hashMap.myNextLetters);
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
		// TODO your code here!
		return null;
	}

	private class TrieNode {
		// like a recursion
		private HashMap<Character, TrieNode> myNextLetters;

		private boolean endOfWord = false;

		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<>();
		}
	}
}