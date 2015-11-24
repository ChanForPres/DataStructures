import java.util.Map;

/* The "extends Comparable<K>" syntax just means that whatever K you use, it has to implement Comparable.
 * Makes sense, right? Otherwise there would be no way to tell whether it should go on 
 * the left or right branch of the tree.
 */
public class MyTreeMap<K extends Comparable<K>, V> extends BinarySearchTree {

	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	protected BinarySearchTree<KVPair> myBST;

	/**
	 * Constructs an empty map.
	 */
	public MyTreeMap() {
		size = 0;
		myBST = new BinarySearchTree<>();
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
		// TODO Complete this!
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
			TreeNode placeToChange = findKVNode(myBST.myRoot, key);
			V prevVal = ((KVPair)placeToChange.myRoot.myItem).getValue();
			((KVPair)placeToChange.myRoot.myItem).setValue(value);
			return prevVal;

		} else {
			System.out.println("ContainsKey No: " + key);
			KVPair toAdd = new KVPair(key, value);
			myBST.add(toAdd);
			size++;
			return null;
		}
	}

	public TreeNode findKVNode(TreeNode thisBST, K key) {
		if (((KVPair)thisBST.myItem).getKey().equals(key)) {
			return thisBST;
		}

		else if (key.compareTo(((KVPair)thisBST.myItem).getKey()) < 0) {
			return findKVNode(thisBST.myLeft, key);
		}

		else if (key.compareTo(((KVPair)thisBST.myItem).getKey()) > 0) {
			return findKVNode(thisBST.myRight, key);
		}
		return null;
	}


	private V putHelper(TreeNode curRoot, K key, V value) {

		if (curRoot == null) {
			System.out.println("1");
			KVPair toAdd = new KVPair(key, value);
			curRoot = new TreeNode(toAdd);
			size++;
			return null;
		}

		else if (((KVPair)curRoot.myItem).getKey().equals(key)) {
			System.out.println("2");
			V temp = ((KVPair)curRoot.myItem).getValue();
			((KVPair)curRoot.myItem).setValue(value);
			return temp;
		}

		else if (((KVPair)curRoot.myItem).getKey().compareTo(key) < 0) {
			System.out.println("3");
			if (curRoot.myRight != null) {
				return putHelper(curRoot.myRight, key, value);
			} else {
				KVPair toAdd = new KVPair(key, value);
				curRoot.myRight = new TreeNode(toAdd);
				size++;
				return null;
			}
		}

		else if (((KVPair)curRoot.myItem).getKey().compareTo(key) > 0) {
			if (curRoot.myLeft != null) {
				return putHelper(curRoot.myLeft, key, value);
			} else {
				KVPair toAdd = new KVPair(key, value);
				curRoot.myLeft = new TreeNode(toAdd);
				size++;
				return null;
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
	public class KVPair implements Comparable<KVPair> {
		private K thisKey;
		private V thisValue;

		public KVPair(K k, V v) {
			thisKey = k;
			thisValue = v;
		}

		public K getKey() {
			return thisKey;
		}

		public V getValue() {
			return thisValue;
		}

		public void setValue(V v) {
			thisValue = v;
		}

		@Override
		public int compareTo(KVPair o) {
			return thisKey.compareTo(o.getKey());
		}
	}
}
