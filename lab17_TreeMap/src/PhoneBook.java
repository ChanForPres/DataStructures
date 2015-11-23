import java.util.*;

public class PhoneBook {
    TreeMap<Person, ArrayList<PhoneNumber>> phonebook = new TreeMap<>();

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	ArrayList<PhoneNumber> numArr;
        if (phonebook.containsKey(personToAdd)) {
            numArr = phonebook.get(personToAdd);
            numArr.add(numberToAdd);
            phonebook.put(personToAdd, numArr);
            personToAdd.hasChangedName = false;
        } else {
            numArr = new ArrayList<PhoneNumber>();
            numArr.add(numberToAdd);
            phonebook.put(personToAdd, numArr);
            personToAdd.hasChangedName = false;
        }
    }


    /*
     * A access an entry in the phone book.
     */
    public PhoneNumber getNumber(Person personToLookup){
    	if (phonebook.containsKey(personToLookup)) {
            ArrayList<PhoneNumber> numArr = phonebook.get(personToLookup);
            int size = numArr.size();
            return numArr.get(size-1);
        } else {
            throw new IllegalArgumentException("Person is not in the book");
        }
    }

    public ArrayList<PhoneNumber> getNumbers(Person personToLookup){
        if (phonebook.containsKey(personToLookup)) {
            ArrayList<PhoneNumber> numArr = phonebook.get(personToLookup);
            return numArr;
        } else {
            throw new IllegalArgumentException("Person is not in the book");
        }
    }
}
