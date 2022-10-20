## The Problem

Given a set (i.e., no duplicate) of integer numbers, e.g. [1, 3, 2, 4], write a function
that returns all unique subsets of the given set.
- Set A is a subset of a set B if all elements of A are also elements of B.
- It is possible for A and B to be equal.
- It is possible for A and B to be empty.
- By definition, order of elements in a set is not important, e.g., [1, 2] and [2, 1] are
considered the same set. The output of the function should contain all unique
subsets of the input set, in any order the candidate chooses.
Please include the test cases you used for your solution.

## How to run:

You will need to perform a Kotlin installation if you haven't already. You can check this [guide](https://kotlinlang.org/docs/command-line.html).
Next you will need to build the code:
```
kotlinc .\SubsetsTests.kt -include-runtime -d subsets.jar
```
Now you can run with this command:
```
java -jar subsets.jar
```
## Solution Explanation:

For this solution we will need two data structures: 
- An array of integers for the input 
- A list of subsets (every subset is represented as a list of integers). This list we will use as the answer and will always start as empty in the algorithm.

As a first step we check if the IS is empty, if it is, we will return an empty list of subsets as the answer. 

In cases where we do not have an empty input list, we will start listing the subsets by creating new ones from the number we are iterating with the subsets we already have from the last numbers we iterated. To understand better consider the input as `[4,9,3]` as a use case for the next steps.
So this means if the input set is bigger than one element, we will iterate through every number in the input array. With this number in hand we will iterate through every known subset in the list, clone it and add the current number into this new subset and add it to the subset list. Our subset list always have 2

Using our example of the input `[4,9,3]`. In the beginning of the algorithm our first element is `4` and the subset list is empty (`[[]]`). So we will create a new subset combining the empty one with the number and our subset list will be: `[[],[4]]` 

In the next iteration of the loop, we have the number `9` as the next element from the input set. So we will combine with the subsets we discover from the number 4 and add the new ones to the subset list: `[[],[4],[4,9],[9]]`

Now our last number in the input set is the number `3`. So we will iterate through all the elements from the subsets list we created with the numbers 4 and 9 and create their variations with the number `3`. And our subslist should be: `[[],[4],[4,9],[9],[4,3],[4,9,3],[9,3],[3]]`

And it's the final answer.

With this solution we have a complexity of O(2^n) for the worst scenario. Because of this if we have an input set bigger than 24 elements, our algorithm breaks with “out of memory exception” when we clone a subset, because for 25 elements we will need an array with 33,554,432 elements.

