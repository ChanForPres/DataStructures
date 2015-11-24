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
     * If personToLookUp is not in the treeMap,
     */
    public PhoneNumber getNumber(Person personToLookup){
        /*if (personToLookup.hasChangedName) {
            return null;
        }*/
    	ArrayList<PhoneNumber> numToRtn = phonebook.get(personToLookup);
        if (numToRtn == null) {
            throw new IllegalArgumentException("The person was not found in the book");
        } else {
            int size = numToRtn.size();
            return numToRtn.get(size-1);
        }
    }

    /*
     *
     */
    public ArrayList<PhoneNumber> getNumbers(Person personToLookup){
        if (phonebook.containsKey(personToLookup)) {
            ArrayList<PhoneNumber> numArr = phonebook.get(personToLookup);
            return numArr;
        } else {
            throw new IllegalArgumentException("Person is not in the book");
        }
    }
}
