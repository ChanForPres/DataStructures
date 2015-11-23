
public class Person implements Comparable {

	private String myName;
	protected boolean hasChangedName;

	public Person(String name) {
		this.myName = name;
		this.hasChangedName = false;
	}

	// return a String representation of the Person object
	public String toString() {
		return myName;
	}

	// Change the name of the person
	public void changeName(String newName) {
		this.myName = newName;
		this.hasChangedName = true;
	}

	@Override
	public int compareTo(Object o) {
		return myName.compareTo(o.toString());
	}

	
}
