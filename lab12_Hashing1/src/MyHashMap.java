import java.util.Iterator;
import java.util.LinkedList;

/* YOU ARE NOT allowed to use any library function related to hash maps
 if you feel the need you may use the ArrayList or LinkedList class */

public class MyHashMap<K, V> implements Iterable<K> {
	/* Default size of the map if not set in constructor */
	private static final int DEFAULT_CAPACITY = 30;

	/* Default load factor if not set in constructor */
	private static final double DEFAULT_LOAD_FACTOR = 0.7;

	private int capacity; // the number of buckets in the map
	private int size; // the number of items that have been put into the map

	// TODO You may declare new instance variables here
	/**
	 * Declaring arrays of LinkedList<HashItem> myTable
	 * array is a container object that holds a fixed number of values of a single type
 	 */
	private LinkedList<KVPair>[] myTable;
	private double loadFactor;


	/**
	 * Constructs an empty map.
	 */
	public MyHashMap() {
		this.capacity = DEFAULT_CAPACITY;
		this.size = 0;
		this.loadFactor = DEFAULT_LOAD_FACTOR;
		myTable = new LinkedList[this.capacity];
	}

	/**
	 * Constructs an empty map with the given initial capacity.
	 */
	public MyHashMap(int initialCapacity) {
		this.capacity = initialCapacity;
		this.size = 0;
		this.loadFactor = DEFAULT_LOAD_FACTOR;
		myTable = new LinkedList[this.capacity];
	}

	/**
	 * Constructs an empty map with the given intial capacity and the given load
	 * factor.
	 *
	 * @param loadFactor
	 *            A fraction greater than 0 and less than 1. Once the size /
	 *            capacity exceeds this number, the map's underlying array
	 *            should expand.
	 */
	public MyHashMap(int initialCapacity, double loadFactor) {
		this.capacity = initialCapacity;
		this.size = 0;
		this.loadFactor = loadFactor;
		myTable = new LinkedList[this.capacity];
	}

	/**
	 * Returns the number of items put into the map (and not subsequently
	 * removed).
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns the capacity of the underlying array of the map.
	 */
	public int capacity() {
		return this.capacity;
	}

	/**
	 * Returns whether the map contains the given key. Runs in O(1) time on
	 * average, relative to the size of the map.
	 */
	public boolean containsKey(K key) {
		int hashCode = key.hashCode();
		hashCode = hashCode % capacity;
		if (size == 0 || myTable[hashCode] == null) {
			return false;
		} else {
			for (int i = 0; i < myTable[hashCode].size(); i++) {
				if (myTable[hashCode].get(i).getKey().equals(key)) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * Returns whether there is some key in the map that contains this value.
	 * How fast can you make this run?
	 */
	public boolean containsValue(V value) {
		for (int i = 0; i < myTable.length; i++) {
			if (myTable[i] != null) {
				for (int j = 0; j < myTable[i].size(); j++) {
					if (myTable[i].get(j).getValue().equals(value)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean needLoadFactor() {
		if (((double)this.size/(double)this.capacity)>this.loadFactor) {
			return true;
		}
		return false;
	}
	/**
	 * Puts the key in the map with the given value. If the key is already in
	 * the map, replaces the value. Should run in O(1) time on average with
	 * respect to the size of the map.
	 *
	 * Returns the previous value associated with the key, or null if there was
	 * no such value.
	 *
	 * Note: If this method causes size / capacity to be greater than the load
	 * factor, then this method should also expand the map.
	 */


	public V put(K key, V value) {
		int hashCode = key.hashCode();
		hashCode = hashCode % this.capacity;
		//System.out.println("hashCode: "+ hashCode + " for "+key);

		// if key is not there
		if (myTable[hashCode] == null) {
			KVPair kvPair = new KVPair(key, value);
			myTable[hashCode] = new LinkedList<KVPair>();
			myTable[hashCode].add(kvPair);
			size++;
			return null;

		} else if (myTable[hashCode].size() == 0) {
			KVPair toadd = new KVPair(key, value);
			myTable[hashCode].add(toadd);
			size++;
			return null;

		} else if (containsKey(key)){
			for (int i = 0; i < myTable[hashCode].size(); i++) {
				if (myTable[hashCode].get(i).getKey().equals(key)) {
					myTable[hashCode].get(i).changeValue(value);
					size++;
				}
			}
			return value;
			
		} else {
			myTable[hashCode].add(new KVPair(key, value));
			size++;
			if (needLoadFactor() == true) {
				expand(this.capacity * 2);
			}
		}
		return null;
	}

	/**
	 * Removes the key from the map. Should run in O(1) time on average with
	 * respect to the size of the map.
	 *
	 * Returns the value associated with the key, or null if there was no key.
	 */
	public V remove(K key) {
		int hashCode = key.hashCode();
		hashCode = hashCode % capacity;

		if (myTable[hashCode] == null) {
			return null;
		}
		if (myTable[hashCode].size() == 0) {
			return null;
		}
		if (myTable[hashCode] == null) {
			return null;
		} else {
			for (int i = 0; i < myTable[hashCode].size(); i++) {
				if (myTable[hashCode].get(i).getKey().equals(key)) {
					V valueToRtn = myTable[hashCode].get(i).getValue();
					myTable[hashCode].remove(i);
					size--;
					return valueToRtn;
				}
			}
			return null;
		}
	}

	/**
	 * Returns the value associated with the key in the map, or null if there is
	 * no such value. Should run in O(1) time on average with respect to the
	 * size of the map.
	 */
	public V get(K key) {
		int hashCode = key.hashCode();
		hashCode = hashCode % capacity;
		if (myTable[hashCode] == null || size == 0) {
			return null;
		} else {
			for (int i = 0; i < myTable[hashCode].size(); i++) {
				if (myTable[hashCode].get(i).getKey().equals(key)) {
					V valueToRtn = myTable[hashCode].get(i).getValue();
					return valueToRtn;
				}
			}
			return null;
		}
	}

	/**
	 * Expands the underlying array to the given capacity.
	 *
	 * ALSO, rehashes all of the items into this new array. Items will likely
	 * end up in different buckets after the expansion.
	 */
	private void expand(int newCapacity) {
		LinkedList<KVPair>[] tempTable = this.myTable;
		this.myTable = new LinkedList[newCapacity];
		this.size = 0;

		for (int i = 0; i < tempTable.length; i++) {
			if (tempTable[i] == null) {
				continue;
			} else if (tempTable[i] != null) {
				for (int j = 0; j< tempTable[i].size(); j++) {
					put(tempTable[i].get(j).getKey(), tempTable[i].get(j).getValue());
				}
			}
		}
		this.capacity = newCapacity;
	}

	/**
	 * Returns an iterator over the keys of this map.
	 */
	public Iterator<K> iterator() {
		return new HashMapIterator();
	}

	/**
	 * An iterator for the keys of the enclosing map.
	 */
	private class HashMapIterator implements Iterator<K> {
		int tableIndex;
		int listIndex;

		public HashMapIterator() {
			tableIndex = 0;
			listIndex = 0;
		}

		@Override
		public boolean hasNext() {
			return (tableIndex < capacity);
		}

		@Override
		public K next() {
			K toRtn = myTable[tableIndex].get(listIndex).getKey();
			if (toRtn != null) {
				return toRtn;
			} else if (toRtn == null) {
				tableIndex++;
				next();
			}
			if (myTable[tableIndex].get(listIndex + 1) == null) {
				tableIndex++;
				listIndex = 0;
			} else if (myTable[tableIndex].get(listIndex + 1) != null) {
				listIndex++;
			}
			return toRtn;
		}
	}

	/**
	 * A class that can store a key and a value together.
	 */
	private class KVPair {
		private K key;
		private V value;

		public KVPair(K keyInput, V valueInput) {
			this.key = keyInput;
			this.value = valueInput;
		}

		public K getKey() {
			return this.key;
		}

		public V getValue() {
			return this.value;
		}

		public void changeKey(K newKey){
			this.key = newKey;
		}

		public void changeValue(V newValue){
			this.value = newValue;
		}
	}

}
