Lab 11: LinkedList3
===

* There was still the problem of insufficient information holding. In the previous implementation of linked list, programmer might accidentally leave a value in an invalid state since any ListNode object is accessible.
* So by inventing the second class, **List**, that contains a reference to the first ListNode in the sequence, I can have more improved security.
* Moreover, by initiating the instance variables that keeps track of *size* and *tail*, I can more easily delete and add with improved efficiency.
