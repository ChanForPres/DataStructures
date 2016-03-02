Lab 24: Regex
===

Regular expressions are a special kind of syntax that **allow easy searching of long documents** for Strings that match special patterns. 

#### Email example
For example, suppose we got an email that contains number of email addresses and we want to get the list of all the email addresses that are written in the email. We can solve this problem using a single line of regex.

1. The simplest way we can use is to check **if a String matches a pattern**. 


### Character Classes
We can not only ask if a String matches a sequence of characters but also *character classes*. 

```
String pattern = "w[aeiouy]g";
String w = "wug";
System.out.println(w.matches(pattern)); // prints true
w = "wog";
System.out.println(w.matches(pattern)); // also true!
w = "waeg";
System.out.println(w.matches(pattern)); // false
```

Here, square brackets are a special symbol in regex. A string will match a regex pattern if it matches any one character inside the brackets. (=big OR symbol) This pattern is saying: first match exactly one ```w```, then match exactly one of ```a```, ```e```, ```i```, ```o```, ```u``` or ```y```. Then match exactly one ```g```.

What if I wanted to match all the alphabets? Then we could do: ```String pattern = "[a-z]ug";``` It also works with ```[0-9]``` to match all the digits.

#### Not
A letter ```^``` at the beginning of a character class matches any character that's **not** in the class. For example, ```w[^aeiouy]g``` will match ```whg```, ```wvg``` but not ```wug```, ```wig```.


### Special Characters
Naturally, regex supports some kind of notion of **repetition**. 

1. ```+``` sign: matches **one or more repetitions** of the character (as shown above). 
    * ```a+b``` **matches** ```ab``` and ```aaab``` but **not** ```b``` and ```baa```
    * ```l(ol)+``` **matches** ```lolol``` but not ```loooool```  

2. ```?``` sign: matches **1 or 0** <br \>
    * ```colou?r``` **matches** both ```colour``` and ```color``` but **not** ```colouer```
    * ``` Nov(ember)?``` **matches** both ```Nov``` and ```November```

3. ```*``` sign: matches **0 or more** <br \>
    * ```a*b``` **matches** ```b```, ```ab``` and ```aaab``` but **not** ```baa```
    * ```.*cat``` **matches** ```cat```, ```9393cat```, ```the old cat```

4. ```{n}``` sign: the preceding elements or subexpression must occur **exactly n** times

5. ```{n, m}``` sign: the preceding elements or subexpression must occur between **n and m times, inclusive**.

#### Special Characters and Escaping

1. ```.```sign: matches any character, except a line terminator
    * ```.art``` **matches** ```dart```, ```tart``` but **not** ```art```, ```hurt```

If we wanted to **specifically** match a String that includes a ```+```, we have to *escape* the character by putting a ```\``` in front of it. ```\``` itself is a sepcial character.    

e.g. ```\+``` will match exactly the String "+". ```\\``` will match exactly the string "\"

In **Java**, ```\``` becomes a bit crazy. If you try ```System.out.println("\n");```, you won't see a ```\``` appear. Therefore, if you want to match the string ```"+"```, you will need the pattern ```"\\+"```.

#### Inside ```[]```
The only special characters inside ```[]``` are ```^```, ```-```, ```\```. The ```\``` is still used for escaping, allowing you to 

#### Matching the beginning or end of a line
1. If a caret (^) is at the beginning of the entire regular expression, it matches the beginning of a line. <br \>
```^(the cat).+``` matches **the cat runs** but not **see the cat run**

2. If a dollar sign ($) is at the end of the entire regular expression, it matches the end of a line. <br \>
```.+(the cat)$``` matches **watch the cat** but not **the cat eats**.

#### Special Sequences with ```\```

1. ```\d``` : matches any digit, ```\D``` matches any non-digit
2. ```\n``` : matches a new line (Windows: ```\r```)
3. ```\s``` : matches any whitespace character (space, newline, tab), ```\S``` matches any non-whitespace
4. ```\w``` : matches any *word* character (a-z, A-Z, 0-9), ```\W``` matches any non-word character

### Java's Regex Objects

#### Java's ```Pattern``` and ```Matcher``` Objects
String's ```matches``` is good and all. But we should expect **objects** to get involved somehow. In order to do more complicated things with regex, we'll need to use 2 classes: ```Pattern``` and ```Matcher```, imported from ```java.util.regex```.

Instead of Strings ```String laugh = "lo+l"```, we use a ```Pattern``` object, ```Pattern laughP = Pattern.compile("lo+l");```. 

To match the pattern, we have to create a ```Matcher``` object for each one. 
```
Matcher matcher1 = laughP.matcher("loool");
System.out.println(matcher1.matches()); // true
Matcher matcher2 = laughingPattern.matcher("loooooooool");
System.out.println(matchter2.matches()); // true
```

These implementations come with some **extra functionality**.

#### Flags
You can pass special *flags* into the constructor for ```Pattern``` to subtly change its behavior. e.g. Ignore case
```
Pattern laughingPattern = Pattern.compile("lo+l", Pattern.CASE_INSENSTIVE);
Matcher matcher2 = laughingPattern.matcher("LOoOoOOL");
System.out.println(matchter2.matches()); // true
```

#### Finding
```Matcher``` has a ```find``` method that will check if any *substring* matches the pattern.
```
Matcher matcher3 = laughingPattern.matcher("loooooolz");
System.out.println(matcher3.matches()); // false
Matcher matcher4 = laughingPattern.matcher("looooooolz");
System.out.println(matcher4.find()); // true, because some substring does match the pattern
```

```Matcher``` objects **cannot be reused normally**. In order to reuse, we'd need to call ```.reset()``` method.

#### Finding multiple things
If we want to check if **multiple substrings match the pattern**, we can try to use the ```find``` method multipl times to see if there are additional matches.
```
public static int countLaughs(String text) {
    Pattern lolPattern = Pattern.compile("lo+l");
    Matcher lolMatcher = lolPattern.matcher(text);
    int count = 0;
    while (lolMatcher.find()) {
        count++;
    }
    return count
}
```


### Capturing
One of the most useful thing: *capturing*. **Capturing allows us to extract the part of a String that matches a pattern**. For example, suppose I have a document that includes dates formatted like (YYYY-MM-DD). Then I can return a list of all the years mentioned in the document.
```
Pattern yearCapture = Pattern.compile("([0-9]{4})-[0-9]{2}-[0-9]{2}");
String document = "Please meet me on 2016-03-14. That's right. Let's wait a year. I didn't mean 2015-03-14.";
Matcher yearMatcher = yearCapture.matcher(document);
while (yearMatcher.find()) {
    System.out.println(yearMatcher.group(1)); // print 2016, then 2015
    System.out.println(yearMatcher.group(2)); // print 03, then 03
    System.out.println(yearMatcher.group(3)); // print 14, then 14
}
```

1. **Parentheses** are special characters in regex that indicate a **capturing group**, which means they are a **part of the pattern that we wish to extract**. 

2. **Why is ```.group(1)```?** We can include **multiple capturing groups** in an expression and reference them by number.

3. **Why do we start counting at 1 instead of 0?** 0 contains the whole matching expression, ignoring the ```()```. The first ```dateMatcher.group(0)``` would contain ```2016-03-14```, then next it would contain ```2015-03-04```.

#### Greedy vs. Reluctant
Suppose we have a following code:

```
Pattern weirdLaughingPattern = Pattern.compile("l.+l");
Matcher weirdMatcher = weirdLaughingPattern.matcher("looool you are the funniest person I have ever met lolol"); 
while (weirdMatcher.find()) { 
    System.out.println(weirdMatcher.group(0)); 
}
```
We might expect it to print out ```looool``` and ```lol```. However, it actually prints out the whole string. This is because ```+``` operate is **greedy**, which means it will try to match as many characters as possible. 

```?``` and ```*``` are also greedy. To make them **reluctant**, meaning they will match as few characters as possible, include a ```?``` after them. So, the correct pattern is ```l.+?l```.

#### Find and Replace
The ```Matcher``` class has two methods, ```replaceAll``` and ```replaceFirst```. The following code makes a quote gender-neutral: 

```
Pattern malePattern = Pattern.compile("men");
Matcher neutralizer = malePattern .matcher("We hold these truths to be self-evident, that all men are created equal...");
System.out.println(neutralizer.replaceAll("people"));
```

However, if we have a quote like "We hold these truth to be self-evident, that all men are equal mentally...", then it prints "We hold these truth to be self-evident, that all people are equal peopletally...". So it replaced the ```men``` in ```mentally```, too. To fix this problem, we need to know the concept of **boundary**.

### Boundaries
Boundaries help us specify that we only want to **match patterns within certain boundary conditions**. 
* ```^``` matches the beginning of a line
* ```$``` matches the end of a line
* ```\b``` matches a **word boundary**. Word boundaries occur at the ends of sequence of word characters. (find a match at the beginning or end of a word.)
* ```\B``` matches a non-word boundary (string not at the beginning or end of a word)

Hence, since we only want to match the word ```men```, we should demarcate it with word boundaries, such as ```Pattern malePattern = Pattern.compile("\\bmen\\b");```. Again, we want to treat the ```\``` as a normal character, so we must escape it.

### Regex Examples
1. Kill if 'subject' contains the reg. exp. "(cash|money)"
    * This kills articles with 'cash' or 'money' in the subject. This should be a case-insensitive match.

2. Kill if 'subject' contains the reg. exp. "^\[?F.?S.?"
    * This kills 'For Sale' articles, which have a subject line that starts ('^') with either FS, F.S., [FS] or [F.S.]. Here the '[' needs to be escaped to '\[', and the '?' means 'match zero or one instance of'.

3. Kill if 'subject' contains the reg. exp. "[[$%|_\*!][[$%|_\*!][[$%|_\*!]"
    * This is a nifty one that kills those posts with subjects like "$$$blah blah" or "_______this..." which are almost surely not worth reading. The regular expression reads like this. It repeats the range of characters [[$%|_\*!] three times, meaning that any of the characters in the [] will be matched. ([ is normally interpreted as starting a group like this unless it is the first character after a [, hence its position here.) This grouping is then repeated three times, to match subjects like $_* or *** or !_!. You could prepend a ^ to force the match a the beginning of the line.

4. Kill if 'Xref' contains the reg. exp. "[^ ]+ [^ ]+ [^ ]+ [^ ]+"
    * This kills articles which have been cross-posted to four or more groups, and works by looking for runs of non-space characters (the [^ ]) separated by spaces.

5. Hilite if 'subject' contains the reg. exp. "News ?Watcher (ignore case)"
    * This will match "newswatcher", "NewsWatcher", "News Watcher", "news Watcher" and so on. The '?' means match zero or one space.

6. Hilite if 'subject' contains the reg. exp. "Kaleid[aeio]scope"
    * This will match "Kaleidoscope", as well as all the misspellings that are common, the [] meaning match any of the alternatives within the square brackets.

7. Hilite if 'subject' contains the reg. exp. "^\[?A[Nn][Nn]"
    * This is useful for catching announcement posts, where the subject line starts with [ANN] or Ann or [Ann. The first "^" forces a match at the beginning of the line. Then it looks for zero or one (the meaning of the "?") "[" characters, but since this is a reserved character, it has to be escaped to "\[". Then we look for a "A", followed by either "N" or "n" and then one or more "N" or "n" characters.







