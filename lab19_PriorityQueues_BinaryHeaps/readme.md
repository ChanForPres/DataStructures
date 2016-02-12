Lab 19: Priority Queues and Binary Heaps
===

### Representing a Tree with an Array

* The root will be at index 1 of the array (nothing at index 0). We can define the position of every other node in the tree recursively.
* The left child of a node at position ```n``` is at index ```2n```.
* The right child of a node at position ```n``` is at index ```2n+1```.
* The parent of a node at postion ```n``` is at index ```n/2```.

### ```ArrayBST.java```

#### The ```getLeft```, ```getRight```,```getParent```,```setLeft```, ```setRight``` method
```insertInSubtree``` was a bit confusing. But it all made sense when I set ```setLeft()``` method to call ```this.setNode(index*2, n);``` and ```setRight()``` method to call ```this.setNode(index*2+1, n);```. <br \>
When you look at the ```insertInSubtree``` method carefully, if the value is smaller than the subroot(it is either real root or the parent node during the recursion), then it should go to the left. And ```getLeftOf``` method finds the appropriate index. If that place is filled, then we should look for somewhere else. That's why we recursively call ```insertInSubtree(left, value);``` with now the ```subroot``` is ```left```.  Then we find the left child of left child. <br \>
If ```left``` index is empty, then we can simply fill that place with the node. (where ```setLeft``` automatically finds the right place by multiplying 2)