fun main() { 
    testCase1(::subsets)
    testCase2(::subsets)
    testCase3(::subsets)
    testCase4(::subsets)
    testCase5(::subsets)
}

fun subsets(inputSet: IntArray): List<List<Int>> { 
    //if the input set is empty, returns an empty list
    if(inputSet.isEmpty()) return listOf(listOf())

    //adds the first element of the input set into the subsets collection
    val subsets = mutableListOf<List<Int>>(listOf())

    //loop through the input set to create new subsets
    for(setIterator in 0..inputSet.lastIndex) {
        val currentSetNumber = inputSet[setIterator]
        //saves the current subset's size before adding the new ones in this iteration
        val resultSize = subsets.size
        var subsetsIterator = 0

        //create new subsets containing the current number with all other subsets in the answer
        while(subsetsIterator < resultSize) {
            //clone the subset from the answer to create a new subset
            val currentSubset: MutableList<Int> = subsets[subsetsIterator].toMutableList()
            currentSubset.add(currentSetNumber)
            subsets.add(currentSubset)
            subsetsIterator++
        }  
    }

    return subsets.toList()
}

//A test case containing only one element as input
fun testCase1(subsetLabda: (IntArray) -> List<List<Int>>) {
    val input = intArrayOf(1)
    val solutionOutput = subsetLabda.invoke(input)
    val expectedOutput = listOf(listOf(), listOf(1))

    assertTestOutput("Test 1", solutionOutput, expectedOutput)
}

//A test case with a empty set as input
fun testCase2(subsetLabda: (IntArray) -> List<List<Int>>) {
    val input = intArrayOf()
    val solutionOutput = subsetLabda.invoke(input)
    val expectedOutput = listOf(listOf<Int>())

    assertTestOutput("Test 2", solutionOutput, expectedOutput) 
}

//A test case with only two elements
fun testCase3(subsetLabda: (IntArray) -> List<List<Int>>) {
    val input = intArrayOf(1,2)
    val solutionOutput = subsetLabda.invoke(input)
    val expectedOutput = listOf(
        listOf(),
        listOf(1), 
        listOf(2),
        listOf(1,2)
        )
    assertTestOutput("Test 3", solutionOutput, expectedOutput) 
}

//A test case with multiple elements
fun testCase4(subsetLabda: (IntArray) -> List<List<Int>>) {
    val input = intArrayOf(1,2,3,4)
    val solutionOutput = subsetLabda.invoke(input)
    val expectedOutput = listOf(
        listOf(),
        listOf(1), 
        listOf(2), 
        listOf(3), 
        listOf(1,2,3),
        listOf(1,3,4),
        listOf(1,2), 
        listOf(2,3),
        listOf(1,3),
        listOf(1,4), 
        listOf(1,2,4),
        listOf(2,4),
        listOf(1,2,3,4),
        listOf(2,3,4),
        listOf(3, 4), 
        listOf(4), 
    )
    assertTestOutput("Test 4", solutionOutput, expectedOutput) 
}


//A test case with multiple elements without order
fun testCase5(subsetLabda: (IntArray) -> List<List<Int>>) {
    val input = intArrayOf(-1,500000,-299 ,4323)
    val solutionOutput = subsetLabda.invoke(input)
    val expectedOutput = listOf(
        listOf(),
        listOf(-1), 
        listOf(-1,500000),
        listOf(500000), 
        listOf(-1,-299), 
        listOf(-1,500000,-299),
        listOf(500000,-299),
        listOf(-299), 
        listOf(-1,4323), 
        listOf(-1,500000,4323),
        listOf(500000, 4323), 
        listOf(-1,-299,4323),
        listOf(-1,500000,-299,4323),
        listOf(500000, -299, 4323),
        listOf(-299,4323),
        listOf(4323), 
    )
    assertTestOutput("Test 5", solutionOutput, expectedOutput) 
}

//A test case that breaks the code with out of memory exception. This is not being used in the Main. It's only here as documentation.
fun testCase6(subsetLabda: (IntArray) -> List<List<Int>>) {
    val input = mutableListOf<Int>()

    for(i in 1..25) { 
        input.add(i)
    }
    subsetLabda.invoke(input.toIntArray())
}


fun assertTestOutput(testName: String, solutionOutput: List<List<Int>>, expectedOutput: List<List<Int>> ) { 
    var isEqual = true

    if(solutionOutput.size != expectedOutput.size) {
        isEqual = false
    }
    else {
        for(i in 0..expectedOutput.lastIndex) {
            isEqual = expectedOutput.contains(solutionOutput[i])
            if(!isEqual) break 
        }
    }
    
    printTestMessage(testName, isEqual)
}

fun printTestMessage(testName: String, isPassing: Boolean) { 
    if(isPassing) println("$testName: is passing")
    else println("$testName: is not passing")
}