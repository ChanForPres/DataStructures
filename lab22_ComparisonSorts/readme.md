Lab 22: Comparison Sorts
===

Sorting an array or linked list of ```integers``` or any kind of collection of ```Comparable``` objects.

1. *Comparison Sorts*: making pairwise comparisons between elements to sort (Insertion sort, Selection sort, Heap sort, Merge sort, Quick sort)

2. *Distribution Sorts*: group elements based on their "digits" and then sort and combine the groups. Do not need to compare individual elements to each other. (Quickselect, Counting sort, Radix sort)

### Insertion Sorts
1. Start a loop over all items in your collection
2. For each item you find, insert it into its correct place **among all items you've looked at so far** (the first *n* items are correctly sorted essentially)

* Awesome: when the array is already sorted
* Terrible: when the array is reversely sorted

#### ```insert``` method
* Implement an insertion sort algorithm on a doubly linked list instead of an array
* ```current``` is the current pointer in the linked list that would be ready to swap
* ```soFar``` is the linkedlist pointer that we have traversed so far and is sorted already.
* In short, for every element *e* in the array, we swap the *e* if it's smaller than the element *p* that is in the already sorted list so far.
* One important part that derived the answer was that the method ```swap()``` returned the ```ListNode```. So the returned ```ListNode``` contains linked list nodes that contain elements that are swapped.

#### Tree Sort (Insertion sort variation)
The insertion sort algorithm did its insertion in an array, where elements had to be shifted over to make room for the new elements. A **binary search tree** produces a faster insertion. 

* So alternative to insertion sort is to first build the tree through repeated insertions
* then at the end traverse it in linear time to produce the sorted sequence
= *tree sort*

### Selection Sort
Of ```N``` elements, we do the following kind of loop
```
for (N):
    find and remove the smallest element remaining in the collection
    and add it to the end of another sorted collection
```

Q. Like insertion sort, is selection sort faster if the array starts off already sorted? No <br \>
Q. What's the aymptotic runtime of selection sort? <br \>
The total number of comparisons is ```(N-1) + (N-2) + ... + 1 = N*(N-1)/2``` no matter what the ordering of elements in the array or linked list. Hence it's O(N^2) algorithm. Unlike **insertion sort** that has a better case (already sorted), **selection sort** *doesn't* have a better case. <br \>
Q. Why do we use selection sort over insertion sort? <br \>
As a recap, given a list, **selection sort** takes the current element and exchange it with the smallest element on the **right** hand side of the current element. On the other hand, **Insertion sort**, takes the current element and insert it at the proper position of the list on the **left**, adjusting the list every time you insert. <br \>
Usually, insertion sort performs less comparisons than selection sort. (For already sorted or almost sorted array, insertion sort performs in O(n) times) However, one advantage of selection sort is that the **number of swaps** in selection sort is **O(n)**, while in insertion sort it is in O(n^2). This is useful when sorting on Flash memory, because swaps reduce the lifespan of Flash memory.

#### Heap sort (Selection sort variation)
Finding the smallest element in the collection usually takes linear time in an array. To quickly find and remove the smallest element, **min heap** would do since it takes O(logN) time to find and remove. 

1. Put all of the numbers in a max heap ```NlogN``` (heapify makes it faster ```N```)
2. Repeatedly remove the max element from the heap, and store them in an array in that order ```log N```

So overall, runtime is no worse than ```NlogN```. 

### Divide and Conquer
Both insertion and selection sorting algorithms, have at their basis the structure of **iterating through each item in the collection one-by-one**. However, there's another approach called **divide-and-conquer**. 

#### Basis

1. Split the elements to be sorted into two collections
2. Sort each collections recursively
3. Combine the sorted collections

#### Merge Sort
Merge sort works as follows:

1. Split the collection to be sorted in half somehow
2. Sort each half (recursively)
3. Merge the sorted half-lists

It's fast because **merging two lists** that are already sorted takes linear time proportional to the sum of the lengths of the two lists in the worst case.

Each operations of splitting and merging takes N operations, and the levels are in total of 2logN levels, the total time is ```O(NlogN)```.

#### ```mergeSort()```
* Splitting method: Have two pointers run at the same time. While one pointer ```a``` is moving one at a time, the other pointer ```b``` is moving twice at a time. Once ```b``` reaches the end, the point where ```a``` remains is the splitting point.

* Since it's recursive, I created another method that takes parameter head and recursively splits the linked list given the head. 

* It was important to keep in mind that since it's a linked list, when we split, it's important to **first** set the second half that begins with ```slow``` pointer and then **second** set the first half ends with ```null``` so that it really separates into two. 

```
ListNode oneHalf = head;
ListNode otherHalf = slow.getLinkNext();
slow.setLinkNext(null);
```

So how it works can be represented in a tree:

```
                    5-4-10
              ________|_________
             |                  |
            5-4                 10
         ____|____              
        |         |
        5         4 
```

So here, splitting split the linkedlist 5-4-10 into 5, 4, 10. Then once we hit the bottom, we merge 5, 4. Then it returns the merged list of 5-4 which will then be merged with 10 and eventually return 5-4-10 IntList.

#### QuickSort
1. Split the collection into two collections by *partitioning* around a "divider" element(pivot). One collection consists of elements smaller than the divider and the other elements 
2. Quicksort the two collections
3. Concatenate the sorted small values with the divider and then with the sorted large values (small sorted values + divider + large sorted values) 

ex) 3, 1, 4, 5, 9, 2, 8, 6
1. Choose 3 as the divider
2. Put 4, 5, 9, 8, 6 into the "large" collection; 1,2 into "small" collection
3. Sort the large collection; sort the small collection; combine the two collections with the divider to get the final result

Concatenation: ```O(1)``` <br \>
Partitioning and Operation: ```O(N)``` <br \>
Height: ```logN``` levels due to splitting <br \>
= Total of ```NlogN``` time

Q. Worst case behavior for a quicksort? If we always decided to make our divider element the first element, which cause quicksort to exhibit O(N^2) behaivor? <br \>
When the array is already **sorted** or **reversely sorted**, then, we will have to make about N recursive calls before it hits its base case, eventually creating O(N^2) operations.

##### Partitioning
Importantly, it should partition an array into 3 parts **in place** without creating extra arrays like in mergesort. In short, following is the algorithm:

* Assume that the pivot is the leftmost element
* Flip all the other cards down
*Open each card from left to right
    * If you find a card that is less than the pivot
        * Swap that card with the card that was first opened(the leftmost card) and close that leftmost card
        * Also take note of the very last card that is closed
    * Otherwise, continue opening the next card 
* Swap the last closed card with the pivot
* Open all cards

##### Picking the divider
Best divider is the **median**. Then it needs sorting, which is at best in ```O(NlogN)```, so it's not very helpful eventually. Another approach is just picking a **random** divider. Since we assumed the pivot is the leftmost element, we just swap the random divider with the leftmost element, then the leftmost element is the random divider of the array.

##### Quicksort performance in practice












