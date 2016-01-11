Lab 2: Loops and Conditionals
===

### Review of if/else/while

### DateConverter.java 
* Given dayOfYear(1-666) on the command line ```args[0]```, this program will convert the ```dayOfYear``` to ```month + dateInMonth``` format.
* This program also handles such case like 367. It will convert to 1/1.

### TriangleDrawer.java
* This program will print the triangle using asterisks. Asterisks increment up to 10 and each line has one more asterisk than the one above.

### CheckDigit.java
* This program determines if the identification number in the variable ```id``` is legal by storing ```true``` or ```false``` value in the variable ```isLegal```. An ID is legal if the rightmost digit is the last digit of the sum of all the other digits in the number. For example, 123456786 is ```legal``` since 6 is the last digit of 1+2+3+4+5+6+7+8=36. But 123456782 would not be legal.

### AddingMachine.java
* This machine consists of three parts: ```user input```, ```subtotal```, ```total```. For a nonzero value, it should be added into a subtotal, a zero value should print the subtotal and reset the subtotal to zero, two consecutive zeroes should print the total of all values and terminate.
* It uses the single while loop and to exit the ```main``` method, it executes a ```return;```. 
* Introduced ```Scanner``` class to read user input. Initiated by writing ``` Scanner scanner = new Scanner(System.in);``` Then to read the input, following codes are written. <br \>
``` int k; ``` <br \>
``` k = scanner.nextInt();```

Q. Comparison: Single loop vs. Two nested loops <br \>
- Which version did you implement for the exercise? I prefer the single loop version.
- Describe in words how the other version would work. For nested loop version, I'd use another while loop to add up non-zero values. I also thought a while loop would be used to check termination.
- Which version do you prefer? The one-loop version. The first reason is that the single loop is easier to read. The single loop version more clearly separates three cases that need to be handled. Another reason is efficiency. I think another while loop unnecessarily takes up memory and time like recursion.