Lab 22: More Sorting
===

In this lab, we will study

* An algorithm, derived from Quicksort, to find the *kth* largest element in an array
* How to maintain a file sorted by multiple keys (..?)
* The property of stable sorting

### Finding the kth largest element

#### Binary search tree version
1. If the right subtree has ```k-1``` items, then the root is the ```k``` item, so return it
2. If the right subtree has ```k``` or more items, find the ```k```th largest item in the right subtree
3. Otherwise, let ```j``` be the number of items in the right subtree, and find the ```k-j-1```st item in the left subtree

A binary search tree is similar to the **recursion tree produced by the Quicksort**. **Root** of the **BST** is **divider** element in **Quicksort**

#### QuickSelect
* It's similar to quicksort in general, but instead of recursing into both sides, as in quicksort, quickselect only recurses into one side - the side with the element it is searching for. This reduces average complexity from ```O(nlogn)``` to ```O(n)```.
* More specifically, when we do **One** partition, we know that our element is in either left or right. and therefore, we only need to recurse that side. 

#### Tail Recursion
Typical recursion balloons the stack as the nesting gets deeper and deeper (creating a new stack frame on top of the current, finished, frame is a waste) In order to deal with this waste, tail recursion is devised (replaces the caller in place with the callee, so that instead of nesting the stack deeper, the current stack frame is reused).

A function call is said to be *tail recursive* if there is nothing to do after the function returns except return its value.

The following is an example of tail recursion:

```
factorial1(n, accumulator) {
    if (n == 0) return accumulator;
    return factorial1(n - 1, n * accumulator);
}

factorial(n) {
    return factorial1(n, 1);
}
```

### A Linear Time Sort
#### Counting Sort
Suppose I have an array ```animals``` that is consist of millions of Strings that are only "Cat", "Dog", and "Person". If I want to sort this array to put all the "Cat" first, then all the "Dog"s and "Person"s for ```N``` runtime. 

1. Create an int array of size three, ```counts```. It will count the total number of each type of String.
2. Iterate through ```animals```. Every time you find a cat, increment ```counts[0]``` by 1 and so on. 
3. Create a new array that will be my final sorted array. called ```sorted```
4. Create a new array called ```starts``` that tells where the first cat, dog, person would go in the new array. 
5. Iterate through all of your strings, and put them into the right spot. When I find the **first cat**, it goes in ```sorted[starts[0]]```. When I find the **first dog**, it goes in ```sorted[starts[1]]```. Then if I find the **second dog**, it goes in ```sorted[starts[1]+1]```. Alternatively, I can increment ```starts[1]``` everytime I put a dog, then the next dog will always go in ```sorted[start[1]]```

* Runtime: ```N+K``` sequential loops over N and K but not nested loops.

* Weaknesses:
    * only appliable to sort **discrete** values
    * will fail if ```K```(varieties) is too large, since creating the intermediate ```counts``` array becomes too slow.

### Sorting with Multiple Keys
Consider a situation where a **key has multiple componenets** and **want to sort the keys using any of those componenets**.

#### Example
For example, the command ```ls``` sort files by name. ```ls -s``` sort files by size. ```ls -t``` sort files by the time of the last modification.

```
public class Directory {

    private FileEntry [ ] myFiles;

    private class FileEntry {
        public String myName;
        public int mySize;
        public GregorianDate myLastModifDate;
        ...
    }

    public void listByName ( ) ...
    public void listBySize ( ) ...
    public void listByLastModifDate ( ) ...
    public void add (FileEntry f)  ...
    public void remove (FileEntry f)  ...
}
```

* Then how do we exactly do ```ls```? We could use ```Arrays.sort(myFiles)``` where ```myFiles``` is the arry of ```FileEntry```. But we need to keep in mind that we should have ```FileEntry implement Comparable<FileEntry>``` and add the ```compareTo(FileEntry f)``` method.

* But how do we do ```ls -s```? We can't have ```FileEntry``` class implement two kinds of ```Comparable```. The solution is to **introduce an object called** ```Comparator``` **which is responsible for ordering the objects**. So

``` 
    public class FileSizeComparator implements Comparator<FileEntry> {
        @Override
        public int compare(FileEntry f1, FileEntry f2) {
            ...
        }
    }
    
    public class FileNameComparator implements Comparator<FileEntry> { 
        @Override
        public int compare(FileEntry f1, FileEntry f2) {
            ...
        }
    }
```

Then we can sort ```FileEntry``` either way by passing in whichever we want. <br \>
```Arrays.sort(myFiles, new FileSizeComparator());``` : sort by size <br \>
```Arrays.sort(myFiles, new FileNameComparator());``` : sort by name <br \>

* ```sort(T[] a, Comparator<? super T> c)```: Sorts the specified array of objects according to the order induced by the specified comparator.

#### Sorting by All Keys at once
What if I want to sort my files by all three qualities at once. e.g. I want to sort my files by size, but among files that are the same size, sort them by time. And among those files that have same size and time, sort them by name.

1. Create a new ```Comparator``` that considers all three qualities
2. First sort the files by size. Split the array into groups based on files with the same size. Within each group , sort them by time. Then split those groups further based on files with the same time, and within the resulting groups, sort them by name. Finally concatenate all the of the groups back together, making sure not to reorder the groups relative to each other.

#### Maintaining multiple sorts
1. Have one **copy** of the directory's file entries(```myFiles``` array) and sort the files everytime we call ```listBy```
2. Or we trade memory efficiency for time efficiency in case where the directory doesn't change very often.
Each entry in the database typically contains a **bunch of fields** and the database program maintains **index arrays** that allow the entries to be listed by one field or another.

### Stability
#### Stable Sorting
A sorting method is stable if it **maintains the relative order of records with equal keys** (keep the equivalent elements in the original order even if you sort it multiple times). In other words, a sorting algorithm is **stable** if whenever there are two records R and S with the same key and with R appearing before S in the original list, R will appear before S in the sorted list.

For example, suppose we have following data and want to **sort by date**.

```
data.txt    10003   Sept 7, 2005
hw2.java     1596   Sept 5, 2005
hw2.class    4100   Sept 5, 2005
```
A stable sorting would keep ```hw2.java``` above ```hw2.class``` while unstable sorting might switch them.

#### Importance of Stability
1. Help us with the multiple-key sorting 
    * e.g. to sort a list of (firstname/surname) pairs, we usually sort first by the first name, and then by surname. If the sorting is not stable, we would lose the benefit of the first sort.
    * e.g. say we have a list of (phone#/employee who called them) pairs. This data is added after each call. Some phone numbers may be called by several different employees. Then say I want to sort this list by phonenumber and give a bonus to the first 2 people who called any given #. We need to keep the ordering of the employee for the same phone#, but if the sorting is not stable, we may not preserve the order of callers of a given number and the wrong employees could get the bonus. 

#### ```UnstableSelectionSort.java```
This is unstable when the array contains the same element. Say we have [(3), 3, 2]. For this to be stable, we should have the fianl result of [2, (3), 3]. However, the the method ```selectionSort()``` in this class eventually results [2, 3, (3)]. But if we change the comparison statement to also consider the equality, ```if (arr[latestPos].compareTo(arr[k]) <= -1 || arr[latestPos].compareTo(arr[k]) == 0)```, it will correctly become stable sorting.

### Radix sort
#### Linear time sorting with distribution sorts
All the sorting methods we've seen so far are **comparison-based**, that is, they use **comparisons** to determine whether two elements are out of order. There's a proof that any comparison-based sorts needs at least **O(NlogN)** comparisosn to sort N elements. However, there are sorting methods that allow sorting of N elements in time proportional to N (counting sort, and radix sort!)

#### Radix sort
* *radix*: the number of values a single digit can take on
    * e.g. binary numbers: radix-2 system/ decimal numbers: radix-10 system
    * Any radix sort examines elements **in passes**, one pass for the rightmost digit, one for the next-to-rightmost digit, and so on
    * In radix sort, we pretend **each digit is a separate key**, and then we just sort on all the keys **at once**, with higher digits taking precedence over the lower ones.

* Two strategies of sorting all the multiple keys at once
    1. *LSD radix sort* : First sort everything on the least important key. Then sort everything on the next key. Continue, until you reach the highest key. This strategy requires the sorts to be **stable**.
    2. *MSD radix sort* : First sort everything on the high key. Group all the items with the same high key into buckets. Recursively radix sort each bucket on the next highest key. Concatenate your buckets back together.

* Helper method
    * Counting sort: For both LSD And MSD radix sorts, they call a helper method that sorts items based on a single digit. Because any digit can only take 10 possible values (for base-10 system), **counting sort** is a good sorting algorithm.

#### ```MSDRadixSortFromDigitInBounds```
* In MSD radix sort, there's a step where you **recursively** radix sort each bucket of items that have the same digit. 
* You can accomplish this recursion without needing to split up the array into new smaller arrays. Instead, each bucket of the array will just be a **portion of the array** known to be **between two given indices**. And make recursive calls that only sort between the indices that are the boundaries of the bucket. 
* Remember! that every digit is regarded as key and we consider the 100th digit, 10th digit, and 1st digit respectively if we have a number greater than 100.

1. Counting sort based on the highest ```digit``` 
2. Boundaries based on the highest ```digit``` as well 
3. Decrement the ```digit```






