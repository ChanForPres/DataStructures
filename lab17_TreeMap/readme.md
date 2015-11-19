Lab 17: TreeMap
===

### Inorder Successor vs. Inorder Predecessor
**Inorder successor** of a node X is the node Y that comes just **after** the node X in inorder traversal of the Binary Tree. Consider the following tree.
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

On the other hand, **inorder predecessor** of a node X is the node Y that comes just **before** node X in inorder traversal.

```
1. If root is NULL
      then return
2. if key is found then
    a. If its left subtree is not null
        Then predecessor will be the right most 
        child of left subtree or left child itself.
    b. If its right subtree is not null
        The successor will be the left most child 
        of right subtree or right child itself.
    return
3. If key is smaller then root node
        set the successor as root
        search recursively into left subtree
    else
        set the predecessor as root
        search recursively into right subtree
```
