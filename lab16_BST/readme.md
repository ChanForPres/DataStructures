Lab 16: Binary Search Tree
===

1) This **Binary Tree** uses generic types T. So we can have a **BinaryTree** of ```Strings``` or ```Comparables```, and so on. <br \>

2) The variable *myRoot*, the nested class **TreeNode** and the *print* method are protected rather than private so that subclasses of BinaryTree an make use of them.<br \>

3) **BinaryTree** now implements ```Iterable``` interface and includes a nested class that implements the ```Iterator``` interface.<br \>

* ```private boolean contains (TreeNode t, T key)``` method 
* ```Comparable``` object: <br \>
	Since the operators < and > don't work with objects, we have to use method calls for comparisons = have the values stored in the tree be objects that implement the ```Comparable``` interface.
* ```public BinaryTree(ArrayList<T> in, ArrayList<T> pre)``` constructor: 
	* This is a constructor for the ```BinaryTree``` class that, given two ```ArrayLists```, constructs the corresponding binary tree. The **first** ArrayList contains the objects in the *preorder* traversal and the **second** ArrayList contains the objects in the *inorder* traversal.
	* 



