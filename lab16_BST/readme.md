Lab 16: Binary Search Tree
===

### Search
#### Binary Search with Array
An important operation provided by **collection classes** is finding whether a given item is an element of the collection. In the binary search algorithm, it assumes that the elements of the array are **in order**. Then <br \>

1. Set ```low``` to 0 and ```high``` to the length of the array minus 1. The value we want to find is ```key``` will be somewhere between ```low``` and ```high```.

2. While ```low <= high```, (a) compute ```mid``` and see if that's key. If so return ```mid```. (b) Otherwise, we can cut the range by setting ```high``` to ```mid-1``` or by setting ```low``` to ```mid+1```.

3. If the loop terminates with ```low>high```, we know that the ```key``` is not in the array, so we return some indication of failure.

= The running time is proportional of the ```logN```, where ```N``` is the number of elements in the array.

#### Binary Search with Linked Lists
It's applying the binary serach algorithm to a sorted doubly linked list. Then the variables ```low```, ```high```, and ```mid``` would be the references to nodes in the list. The following is the steps:

1. Set ```low``` to 0 and ```high``` to the length of the array minus 1. The value we want to find is ```key``` will be somewhere between ```low``` and ```high```.

2. While ```low <= high```, (a) Compute the value ```mid``` and see if that's key. If so, return ```mid```. (b) Otherwise, we can cut the range by setting ```high``` to ```mid-1``` or by setting ```low``` to ```mid+1```.

3. If the loop terminates with ```low > high```, it's indication of failure.

- It takes extra time to **access the middle node**.


### Binary Search Tree

* Let the root value be ```k``` (one of the keys to be stored)

* Put all the keys that are **smaller** than ```k``` into a binary search tree, and let that tree be ```k```'s **left subtree**

* Put all the keys that are **larger** than ```k``` into a binary search tree, and let that tree be ```k```'s **right subtree**.

#### Static again
When to use ```static``` methods again? <br \>
**does it make sense to call this method, even if no Obj has been constructed yet?** "static" is often valuable when you know something is not going to change across instanecs (Single Responsibility Principle). <br \>

1. If you are writing utility classes and they are not supposed to be changed.
2. If the method is not using any instance variable.
3. If any operation is not dependent on instance creation.
4. If there is some code that can easily be shared by all the instance methods, extract that code into a static method.
5. If you are sure that the definition of the method will never be changed or overridden. As static methods can not be overridden.

#### How it works
1. Basically, the ```BinaryTree<T>``` implements ```Iterable``` interface, that will enable us to use ```InorderIterator``` that implements ```Iterator``` interface.

2. ```BinaryTree``` class has ```myRoot``` instance variable that is of ```TreeNode``` class. And ```TreeNode``` class contains ```myItem```, ```TreeNode myLeft```, and ```TreeNode myRight```.

3. ```BinarySearchTree``` class extends ```BinaryTree``` class. So ```BinarySearchClass``` is the subclass of ```BinaryTree``` class. This also contains generic type ```T``` that extends ```Comparable``` class.

4. The variable ```myRoot```, the nested class ```TreeNode``` and the method ```print``` are now ```protected``` rather than ```private``` so the subclasses of ```BinaryTree``` can make use of them.

5. The ```TreeNode``` class is no longer ```static```. (Possibly because it has subclasses, ```TreeNode``` might be overriden or changed. If that's the case, keeping it ```static``` is not a good idea.)

6. ```BinaryTree``` now implements ```Iterable``` interface and includes a nested class that implements ```Iterator``` interface.

#### The ```contains``` method
Recursive method that compares if ```key``` is equal to ```t.myItem```. If ```key < t.myItem```, then the ```key``` must be in the left subtree. Otherwise, it must be in the right subtree. This method, in the worst case, creates the number of comparisons proportional to ```d```, the depth of the tree.

#### ```Comparable``` object: <br \>
Since we implement ```Comparable``` interface, we can use its method, ```int compareTo(object)```. When we use ```obj1.compareTo(obj2)```, it's **negative** if ```obj1 < obj2```. It's positive if ```obj1 > obj2``` and zero if two are equal.

#### Insertion into a BST
To minimize restructuring the tree, we choose to insert a new key only as a new **leaf**.

#### ```public BinaryTree(ArrayList<T> in, ArrayList<T> pre)``` constructor: 
* This is a constructor for the ```BinaryTree``` class that, given two ```ArrayLists```, constructs the corresponding binary tree. The **first** ArrayList contains the objects in the **preorder** traversal and the **second** ArrayList contains the objects in the **inorder** traversal.

* Before we move on, **preorder** is root-left-right. **inorder** is left-root-right.

* **How I figured out**: <br \>
Imagine we have one preorder arraylist ```ArrayLists["A", "B", "C", "D", "E", "F"]``` and inorder arraylist ```ArrayLists["B", "A", "E", "D", "F", "C"]```. Let's call it preList and inList for simplicity. Since preorder starts from the root, it is clear that the first element ```"A"``` in the preList is the root of the whole tree. And since inorder starts from the left subtree to the root and to the right subtree, we can deduce that once we find the same first element ```A``` in the inList, the left element```B``` from that item ```A``` in inList are the whole items of the left subtree and the right elements```"C", "D", "E", "F"``` from that item ```A``` in inList are the whole of the right subtree. Then we recursively operate this process with these left and right subtree items until our nodes have no children. 


