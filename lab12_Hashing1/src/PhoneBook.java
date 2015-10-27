import java.util.*;

public class PhoneBook {
    HashMap <Person, ArrayList<PhoneNumber>> phoneBook = new HashMap <Person, ArrayList<PhoneNumber>>();

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
        ArrayList<PhoneNumber> numArr;
        if (phoneBook.containsKey(personToAdd)) {
            numArr = phoneBook.get(personToAdd);
            numArr.add(numberToAdd);
            phoneBook.put(personToAdd, numArr);
            personToAdd.hasChangedName = false;

        } else {
            numArr = new ArrayList<PhoneNumber>();
            numArr.add(numberToAdd);
            phoneBook.put(personToAdd, numArr);
            personToAdd.hasChangedName = false;
        }
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	if (phoneBook.containsKey(personToLookup)) {
            if (personToLookup.hasChangedName) {
                return null;
            } else {
                ArrayList<PhoneNumber> numArr = phoneBook.get(personToLookup);
                int size = numArr.size();
                return numArr.get(size - 1);
            }
        } else {
            throw new IllegalArgumentException("Person is not in the phonebook.");
        }
    }

}
