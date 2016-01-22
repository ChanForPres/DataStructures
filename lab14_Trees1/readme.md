Lab14: Trees1
===

### Understanding trees
* Node: Single element in a tree
* Edge: A connection between two nodes
* Path: A series of one or more edges that connects two nodes
* Leaf: A node with no children
* Root: The topmost node, which does not have a parent

#### Relationships
* Child: Below the current node
* Parent: Singular node connected direclty above the current node
* Sibling: Nodes with the same parent
* Ancester: **All nodes** on the path from the selected node up to (including the root)
* Descendant: **All the children**, children's children...
* Subtree: The tree consisting of the node and all of its descendants
* **Height**: The number of edges on the **longest path** from the node to the leaf.
* **Depth**: The number of edges from the node to the tree's root node.

#### Definition
No node can be a direct child of multiple nodes. There can be no cycles in the nodes. All nodes must be a descendant of the root.

### Amoeba Family Tree ```AmoebaFamily.java```
* Void methods
    * ```addChild(String parentName, String childName)```: Void recursive method to add a new Amoeba to the ```AmoebaFamily``` tree
    * ```public void makeNamesLowercase()``` : Void recursive method t make all names lower case
    * ```public void replaceName(String currentName, String newName)``` : Replace the name of an Amoeba
    * ```public void printFlat()``` : Print the names of all Amoebas in the Family
    * ```public void prettyPrint()``` : Pretty print the names of all Amoebas in the Family using helper function

* Non-void methods
    * ```public int longestNameLength()``` : Returns the length of the longest name in the Amoeba family
    * ```public String longestName()``` : return the name of the longest name in the tree
    * (OPTIONAL) ```public String busiestHelper(HashMap<Amoeba, Integer> amoebaHashMap, Amoeba previousA)``` : Returns the name of the amoeba with the most children. If there're multiple amoebas with the same max # of children, return any of them. I'm using ```HashMap<Amoeba, Integer>``` for efficiency and helper method ```busiestHelper(HashMap<Amoeba, Integer> amoebaHashMap, Amoeba previousA)```.  
    * ```private int height()``` : Computes the height of a node in a tree (the maximum length of a downward path from that node to a leaf)

### Binary Trees
A binary tree, one in which each node has at most 2 children. Instead of storing the children in an ```ArrayList``` as was done in ```Amoeba``` nodes, one just has 2 separate variables ```myLeft``` and ```myRight```.
* **preorder** : process the **root**, the **left subtree**, the **right subtree**
* **postorder** : process the **left subtree**, the **right subtree**, and the **root**
* **inorder** : process the **left subtree**, the **root**, the **right subtree**

#### ```BinaryTree.java``` 
* ```private int height()```: The height of an empty tree is 0/ The height of a one-node tree is 1/ Anything else is 1+greater of the heights of the two children
* ```private boolean isCompletelyBalanced()``` : Tree is completely balanced if and only if **the height of its left child is equal to the height of its right child**, and **its left and right children are also completely balanced.**