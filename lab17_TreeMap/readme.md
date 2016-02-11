Lab 17: TreeMaps
===
1. Algorithm for deleting a node
2. Analyzing some properties of the inorder successor of a node
3. Finding the kth-largest value quickly
4. Second way to implement a map: instead of using **hashing**, using **binary search trees**, resulting a ```TreeMap```.

### Delete a key from a BST
- To minimize restructring and unbalancing of the tree after deletion
1. Find the node to be removed, called ```remNode```. If it's a leaf, remove immediately by returning ```null```. If it has one child, remove it by returning the other child node.
2. Otherwise, remove the **inorder successor** of ```remNode```, copy its ```myItem``` into ```remNode``` and return ```remoNode```.

### Inorder Successor vs. Inorder Predecessor
**Inorder successor** is the node X that comes right **after** the ```remNode``` if I were to do an inorder traversal of the Binary Tree. Consider the following tree.
```
		20
	   /  \
	  8   22
	 / \
	4  12
	  /  \
	 10  14
```

The whole inorder traversal goes like this: 4-8-10-12-14-20-22. Therefore inorder successor of 8 is 10. Inorder successor of 10 is 12. Inorder successor of 14 is 20.

On the other hand, **inorder predecessor** is the node X that comes just **before** the ```remNode``` in inorder traversal. The following algorithm is to find the inorder successor and predecessor of a given int key: <br \> 

http://www.geeksforgeeks.org/inorder-predecessor-successor-given-key-bst/ 

```
1. If root is NULL
      then return
2. if key is found then
    a. If its left subtree is not null,
        Then predecessor will be the right most 
        child of left subtree or left child itself.
    b. If its left subtree is null, 
    	Then predecessor will be the parent of the key
    c. If its right subtree is not null,
        Then successor will be the left most child 
        of right subtree or right child itself.
    d. If its right subtree is null,
    	Then successor will be the key itself.
    return
3. If key is smaller then root node
        successor = root
        search recursively into left subtree
    else
        predcessor = root
        search recursively into right subtree
```

### Comparable kthLargest (int k)
Consider the problem of finding the ```k```th largest key in a BST. 

1. Obvious solution) **inorder enumeration** : call ```nextElement``` until we get the desired key/ but if ```k``` is near the number of keys ```N``` in the tree, its running time is proportional to ```N```.

2. Better solution) Storing in each node the size of the tree rooted at that node: allows an algorithm's running time proportional to ```d```, depth of the tree.

* My own solution: 

    * Trading Memory for Speed 
    * Here storing in each node the size of the tree rooted at that node and the rank of the tree allows the design of an algorithm runs in time proportional to ```d```, the depth of the tree.

3. TreeMap Phonebook
```TreeMap``` implements ```Comparable``` interface to order the map based on keys




