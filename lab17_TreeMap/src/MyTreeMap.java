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
		return containsKeyHelper(myBST.myRoot, key);
	}

	private boolean containsKeyHelper(TreeNode thisRoot, K key) {
		if (size == 0) {
			return false;
		}

		else if (thisRoot == null) {
			return false;
		}

		else if (((KVPair)thisRoot.myItem).getKey().equals(key)) {
			return true;
		}

		else {
			if (key.compareTo(((KVPair)thisRoot.myItem).getKey()) < 0) {
				return containsKeyHelper(thisRoot.myLeft, key);
			} else if (key.compareTo(((KVPair)thisRoot.myItem).getKey()) > 0) {
				return containsKeyHelper(thisRoot.myRight, key);
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
			TreeNode placeToChange = findKVNode(myBST.myRoot, key);
			V prevVal = ((KVPair)placeToChange.myItem).getValue();
			((KVPair)placeToChange.myItem).setValue(value);
			return prevVal;

		} else {
			System.out.println("ContainsKey No: " + key);
			KVPair toAdd = new KVPair(key, value);
			myBST.add(toAdd);
			size++;
			return null;
		}
	}

	public TreeNode findKVNode(TreeNode thisRoot, K key) {

		if (((KVPair)thisRoot.myItem).getKey().equals(key)) {
			return thisRoot;
		}

		else {
			if (key.compareTo(((KVPair)thisRoot.myItem).getKey()) < 0) {
				return findKVNode(thisRoot.myLeft, key);
			} else if (key.compareTo(((KVPair)thisRoot.myItem).getKey()) > 0) {
				return findKVNode(thisRoot.myRight, key);
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
		V temp = myBST.myRoot.myItem.getValue();
		if (containsKey(key)) {
			TreeNode placeToRemove = findKVNode(myBST.myRoot, key);

			// No children
			if (placeToRemove.myRight == null && placeToRemove.myLeft == null) {
				((KVPair)placeToRemove.myItem).setKey(null);
				((KVPair)placeToRemove.myItem).setValue(null);
				placeToRemove.myRight = null;
				placeToRemove.myLeft = null;
			}

			// One child on the right subtree
			else if (placeToRemove.myRight != null && placeToRemove.myLeft == null) {
				((KVPair)placeToRemove.myItem).setKey(((KVPair)placeToRemove.myRight.myItem).getKey());
				((KVPair)placeToRemove.myItem).setValue(((KVPair)placeToRemove.myRight.myItem).getValue());
				placeToRemove.myLeft = placeToRemove.myRight.myLeft;
				placeToRemove.myRight = placeToRemove.myRight.myRight;
			}

			// One child on the left subtree
			else if (placeToRemove.myRight == null && placeToRemove.myLeft != null) {
				((KVPair)placeToRemove.myItem).setKey(((KVPair)placeToRemove.myLeft.myItem).getKey());
				((KVPair)placeToRemove.myItem).setValue(((KVPair)placeToRemove.myLeft.myItem).getValue());
				placeToRemove.myLeft = placeToRemove.myLeft.myLeft;
				placeToRemove.myRight = placeToRemove.myLeft.myRight;
			}

			// Two children
			else {

			}
		}
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value.
	 */
	public V get(K key) {
		return getHelper(myBST.myRoot, key);
	}

	private V getHelper(TreeNode thisRoot, K key) {

		if (thisRoot == null) {
			return null;
		}

		else if (((KVPair)thisRoot.myItem).getKey().equals(key)) {
			return ((KVPair)thisRoot.myItem).getValue();
		}

		else {
			if (key.compareTo(((KVPair)thisRoot.myItem).getKey()) < 0) {
				return getHelper(thisRoot.myLeft, key);
			} else if (key.compareTo(((KVPair)thisRoot.myItem).getKey()) > 0) {
				return getHelper(thisRoot.myRight, key);
			}
		}
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

		public void setKey(K newKey) {
			thisKey = newKey;
		}

		@Override
		public int compareTo(KVPair o) {
			return thisKey.compareTo(o.getKey());
		}
	}
}
