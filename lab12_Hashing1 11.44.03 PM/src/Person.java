public class Person {

	private String myName;
	boolean hasChangedName = false;

	public Person(String name) {
		this.myName = name;
	}

	// return a String representation of the Person object
	public String toString() {
		return myName;
	}

	// Change the name of the person
	public void changeName(String newName) {
		this.myName = newName;
        hasChangedName = true;
	}
	
	// TODO add additional methods
	
}
