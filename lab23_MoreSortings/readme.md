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










