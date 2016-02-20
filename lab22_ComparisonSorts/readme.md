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
Implement an insertion sort algorithm on a doubly linked list instead of an array

#### Tree Sort (Insertion sort variation)