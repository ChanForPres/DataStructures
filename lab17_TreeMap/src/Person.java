public class Person {

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
	
	// TODO add additional methods
	
}
