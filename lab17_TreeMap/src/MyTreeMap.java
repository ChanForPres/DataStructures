/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable. 
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> {

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	private BinarySearchTree<KVPair> bst;

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		this.size = 0;
		bst = new BinarySearchTree<KVPair>();
	}


	/**
	 * Returns the number of items put into the map (and not subsequently
	 * removed).
	 */
	public int size() {
		return size;
	}


	/**
	 * Returns whether the map contains the given key.
	 */
	public boolean containsKey(K key) {
		return containsKeyHelper(bst, key);
	}


	private boolean containsKeyHelper(BinarySearchTree<KVPair> thisBST, K key) {
		System.out.println("Heelloo: "+thisBST);

		if (size == 0) {
			return false;
		}

		if (thisBST == null) {
			return false;
		}

		else if (thisBST.myRoot.myItem.getKey() == key) {
			return true;

		} else {
			if (key.compareTo(thisBST.myRoot.myItem.getKey()) < 0) {
				containsKeyHelper(thisBST.myRoot.myLeft, key);
			} else if (key.compareTo(thisBST.myRoot.myItem.getKey()) > 0) {
				containsKeyHelper(thisBST.myRoot.myRight, key);
			}
		}
		return false;
	}


	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value.
	 * 
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 */
	public V put(K key, V value) {
		System.out.println("put key: "+key);
		if (containsKey(key)) {
			System.out.println("ContainsKey Yes: " + key);
			KVPair keyValPlace = findKVPair(bst, key);
			V prevVal = keyValPlace.getValue();
			keyValPlace.setValue(value);
			return prevVal;

		} else {
			System.out.println("ContainsKey No: " + key);
			KVPair toAdd = new KVPair(key, value);
			bst.add(toAdd);
			size++;
			return null;
		}
	}


	/**
	 * A helper function that helps find the KVPair in the Binary Search Tree
	 */
	public KVPair findKVPair(BinarySearchTree<KVPair> thisBST, K key) {
		if (thisBST.myRoot.myItem.getKey() == key) {
			return thisBST.myRoot.myItem;
		} else {
			if (key.compareTo(thisBST.myRoot.myItem.getKey()) < 0) {
				return findKVPair(thisBST.myRoot.myLeft, key);
			} else if (key.compareTo(thisBST.myRoot.myItem.getKey()) > 0) {
				return findKVPair(thisBST.myRoot.myRight, key);
			}
		}
		return null;
	}

	/**
	 * Removes the key from the map.
	 * 
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		// TODO Complete this!
		return null;
	}


	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		// TODO Complete this!
		return null;
	}


	/**
	 * A class that can store a key and a value together. You can modify this 
     * class however you want.
	 */
	private class KVPair implements Comparable<KVPair> {
		private K key;
		private V value;

		public KVPair(K k, V v) {
			key = k;
			value = v;
		}

		public K getKey() {
			return this.key;
		}

		public V getValue() {
			return this.value;
		}

		public void changeKey(K newKey) {
			this.key = newKey;
		}

		public void setValue(V v) {
			value = v;
		}

		@Override
		public int compareTo(KVPair o) {
			return this.key.compareTo(o.key);
		}
	}
}
