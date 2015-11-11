Lab 16: Binary Search Tree
===

1) This ```Binary Tree``` uses generic types T. So we can have a ```BinaryTree``` of ```Strings``` or ```Comparables```, and so on. <br \>

2) The variable ```myRoot```, the nested class ```TreeNode``` and the ```print``` method are protected rather than private so that subclasses of BinaryTree an make use of them.<br \>

3) ```BinaryTree``` now implements ```Iterable``` interface and includes a nested class that implements the ```Iterator``` interface.<br \>

* ```private boolean contains (TreeNode t, T key)``` method 
* ```Comparable``` object: <br \>
	Since the operators < and > don't work with objects, we have to use method calls for comparisons = have the values stored in the tree be objects that implement the ```Comparable``` interface.
* ```public BinaryTree(ArrayList<T> in, ArrayList<T> pre)``` constructor: 
	* This is a constructor for the ```BinaryTree``` class that, given two ```ArrayLists```, constructs the corresponding binary tree. The **first** ArrayList contains the objects in the *preorder* traversal and the **second** ArrayList contains the objects in the *inorder* traversal.
	* **How I figured out**: <br \>
	Imagine we have one preorder arraylist ```ArrayLists["A", "B", "C", "D", "E", "F"]``` and inorder arraylist ```ArrayLists["B", "A", "E", "D", "F", "C"]```. Let's call it preList and inList for simplicity. Since preorder starts from the root, it is clear that the first element ```"A"``` in the preList is the root of the whole tree. And since inorder starts from the left subtree to the root and to the right subtree, we can deduce that once we find the same first element ```A``` in the inList, the left element```B``` of that item ```A``` in inList are the whole items of the left subtree and the right elements```"C", "D", "E", "F"``` of that item ```A``` in inList are the whole of the right subtree. Then we recursively operate this process with these left and right subtree items until our nodes have no children. 


