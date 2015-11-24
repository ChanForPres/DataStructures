import junit.framework.TestCase;

public class PhoneBookTest extends TestCase {

	/*
	 * Tests that you can add a person,number pair to the book and later access
	 * the number for that person.
	 */
	public void testCanAddAndGet() {
		PhoneBook myBook = new PhoneBook();
		Person person1 = new Person("Sally");
		PhoneNumber num1 = new PhoneNumber("5105551234");

		myBook.addEntry(person1, num1);
		PhoneNumber numReturned = myBook.getNumber(person1);
		assertEquals("Stored Number Not Correct", num1, numReturned);
	}

	/*
	 * Tests that you can add a second phone number for the same person and that
	 * only the second phone number remains.
	 * */

	public void testCanChangeNumber() {
		PhoneBook myBook = new PhoneBook();
		Person person1 = new Person("Sally");
		PhoneNumber num1 = new PhoneNumber("5105551234");
		PhoneNumber num2 = new PhoneNumber("5105551235");

		myBook.addEntry(person1, num1);
		myBook.addEntry(person1, num2);
		PhoneNumber numReturned = myBook.getNumber(person1);
		assertEquals("Replaced Number Not Found", numReturned, num2);
		assertNotSame("Old Phone number was found", numReturned, num1);
	}

	/*
	 * Tests that if you add a person, number pair then modify the person, you
	 * can't get the number out of the phone book again.
	 *
	 *
	 */

	public void testCantAccessNumIfChangePersonObj() {
		PhoneBook myBook = new PhoneBook();
		Person person1 = new Person("Jane");
		PhoneNumber num1 = new PhoneNumber("5105551234");
		Person person2 = new Person("Anne");
		PhoneNumber num2 = new PhoneNumber("5105551235");
		Person person3 = new Person("Zora");
		PhoneNumber num3 = new PhoneNumber("5105551236");

		myBook.addEntry(person1, num1);
		myBook.addEntry(person2, num2);
		myBook.addEntry(person3, num3);
		person3.changeName("Eungie");
		assertNull(myBook.getNumber(person3));
	}

	/*
	 * Also tests that if you add a person, number pair then modify the person,
	 * you can't get the number out of the phone book again.
	 *
	 * 1) Even though We changed the String value(myName) that is inside Person object,
	 *    the Person object (person1, person2, ...) is still there. Therefore, that's why we could find the PhoneNumber
	 *    we are looking for.
	 * 2) However, String(myName) is still important because it helps the TreeMap get() method to find where to go (left subtree?/right subtree?)
	 *    So, if we change the String (myName) of the Person object that directs to the different side of the tree,
	 *    say person3 was previously in the right subtree but the new String value directs to the left subtree,
	 *    we cannot find the value and therefore throw the error.
	 *
	 * */
	public void testCantAccessNumIfChangePersonObj2() {
		PhoneBook myBook = new PhoneBook();
		Person person1 = new Person("Jane");
		PhoneNumber num1 = new PhoneNumber("5105551234");
		Person person2 = new Person("Colin");
		PhoneNumber num2 = new PhoneNumber("5105551235");
		Person person3 = new Person("Kate");
		PhoneNumber num3 = new PhoneNumber("5105551236");
		Person person4 = new Person("Zora");
		PhoneNumber num4 = new PhoneNumber("5105551237");

		myBook.addEntry(person1, num1);
		myBook.addEntry(person2, num2);
		myBook.addEntry(person3, num3);
		myBook.addEntry(person4, num4);
		person2.changeName("Alice");
		System.out.println("myBook.getNumber(person1): "+myBook.getNumber(person2));
		assertNull(myBook.getNumber(person1));
	}


	/*
	 * Tests that if you modify a PhoneNumber that is already in the Phone book
	 * that the change will be reflected the next time you look up that phone
	 * number.
	 * */

	public void testCanModifyPhoneNumberAlreadyInBook() {
		PhoneBook myBook = new PhoneBook();
		Person person1 = new Person("Jane");
		PhoneNumber num1 = new PhoneNumber("5105551234");

		myBook.addEntry(person1, num1);
		num1.changeNumber("5105551235");
		PhoneNumber num2 = myBook.getNumber(person1);
		assertEquals("Changed PhoneNumber not reflected in PhoneBook", num1,
				num2);
	}

}
