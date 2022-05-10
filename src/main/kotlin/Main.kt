
import java.text.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.*
import kotlin.system.*


/**
 * N interns labeled 1 to N
 * 50 days internship from 0 to 49
 *
 * Day 1 password for kth intern is 5000 * k
 *
 * For day 2 onwards, password is previousDayPassword + 5000 + day
 */
fun findInternLabel(numberOfInterns: Int, passwordUsed: Int): Int? {
    val days = IntArray(50) { it }   // Array with elements from 0 to 49
    for (i in 1..numberOfInterns) { // Loop through the interns
        for (d in days) { // For each intern, check if the given password matches with theirs for any of the 50days
            if (passwordUsed < passwordGenerator(i, d)) {
                // If at any point the password for an intern in any of the days is greater
                // than the given password, break the loop and move on to the next intern
                break
            } else if (passwordUsed == passwordGenerator(i, d)) return i  // If the password matches, return the intern's number
        }
    }
    return null // return null if no match is found.
}

/**
 * Helper function for generating intern's password
 */
private fun passwordGenerator(internNumber: Int, numberOfDay:Int): Int {
    if (numberOfDay == 0) return 5000 * internNumber  // Base case for recursion
    return passwordGenerator(internNumber, numberOfDay - 1) + 5000 + numberOfDay
}
// Tests

const val MILLIS_PER_DAY = 24 * 60 * 60 * 1000L
//fun main(args: Array<String>) {
//
////    println(isMoreThanOneDay("2021-12-04"))
////    println(getDateString())
////    println(expiresAt())
//
//   // println(TwoPointers.quadrupleSumToTarget(intArrayOf(-2,-1,-1,1,1,2,2), 0))
//
//}

fun merge2(nums1: IntArray, m: Int, nums2: IntArray, n: Int) {
    var k = m - 1
    var j = n - 1
    var r = n + m - 1
    //begin from end
    while (k >= 0 && j >= 0) {
        if (nums1[k] > nums2[j]) { //put it in last free pos from end
            nums1[r--] = nums1[k--]
        } else {
            nums1[r--] = nums2[j--]
        }
    }
    while (r >= 0 && j >= 0) {
        nums1[r--] = nums2[j--]
    }
}

private fun expiresAt() = Date(System.currentTimeMillis() + 3_600_000 * 24) // 24 hours


fun isMoreThanOneDay(string: String): Boolean {
    val millisPerDay = 24 * 60 * 60 * 1000L
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = Calendar.getInstance().time
    val currentDate = sdf.format(date)
    val today = sdf.parse(currentDate)
    val otherDay = sdf.parse(string)
    return abs(otherDay.time - today.time) >= millisPerDay
}

fun getDateString(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    val date = Calendar.getInstance().time
    return sdf.format(date)
}


//fun main() {
//    println(findInternLabel(2, 5000))
//    println(findInternLabel(10, 25003))
//}

//    println(SlidingWindow.stringPermutation("oidbcaf", "abc"))
//    println(SlidingWindow.stringPermutation("odicf", "dc"))
//    println(SlidingWindow.stringPermutation("bcdxabcdy", "bcdyabcdx"))
//    println(SlidingWindow.stringPermutation("aaacb", "abc"))

//    println(SlidingWindow.smallestSubstringWithGivenPattern("aabdec", "abc"))
//    println(SlidingWindow.smallestSubstringWithGivenPattern("abdbca", "abc"))
//    println(SlidingWindow.smallestSubstringWithGivenPattern("adcad", "abc"))

 //   println(SlidingWindow.wordConcatenation("catfoxcat", arrayOf("cat", "fox")))
  //  println(SlidingWindow.wordConcatenation("catcatfoxfox", arrayOf("cat", "fox")))
//    println(isDivisible(2))

//    println(TwoPointers.pairWithTargetSum(intArrayOf(1, 2, 3, 4, 6), 50).toList())
//    println(TwoPointers.pairWithTargetSum(intArrayOf(2, 5, 9, 11), 11).toList())
//    println(TwoPointers.pairWithTargetSumUsingHashMap(intArrayOf(1, 2, 3, 4, 6), 50).toList())
//    println(TwoPointers.pairWithTargetSumUsingHashMap(intArrayOf(2, 5, 9, 11), 11).toList())

//    println(TwoPointers.removeDuplicates(intArrayOf(2, 3, 3, 3, 6, 9, 9)))
//    println(TwoPointers.removeDuplicates(intArrayOf(2, 2, 2, 11)))
//    println(TwoPointers.removeElement(intArrayOf(3, 2, 3, 6, 3, 10, 9, 3), 3))
//    println(TwoPointers.removeElement(intArrayOf(2, 11, 2, 2, 1), 2))
//    println(TwoPointers.sortedArraySquare(intArrayOf(-2, -1, 0, 2, 3)).toList())
//    println(TwoPointers.sortedArraySquare(intArrayOf(-3, -1, 0, 1, 2)).toList())
//    println(TwoPointers.shortestWindowSort(intArrayOf(1, 2, 5, 3, 7, 10, 9, 12)))
//    println(TwoPointers.shortestWindowSort(intArrayOf(1, 3, 2, 0, -1, 7, 10)))
//    println(TwoPointers.shortestWindowSort(intArrayOf(1, 2, 3)))
//    println(TwoPointers.shortestWindowSort(intArrayOf(3, 2, 1)))

//    val arr = arrayListOf<Int>()
//    for (i in 1..100_000) arr.add(0)
//    println(SlidingWindow.solution(arr.toIntArray()))

//}


//fun isDivisible(num: Int): Boolean{
//
//    fun isEven() = (num % 2) == 0
//    fun divisibleBy5() = (num % 5) == 0
//
//
//    return when {
//           isEven() -> true
//           divisibleBy5() -> false
//        else -> false
//    }
//}

fun main() {
//    println(domino("6-3"))
//    println(domino("1-2,1-2"))
//    println(domino("1-1,3-5,5-2,2-3,2-4"))
//    println("Time is ${measureTimeMillis { solution2(intArrayOf(5, 19, 8, 1)) }} ms")
//    val arr = IntArray(30_000) { 70_000 }
//    println(arr.sum())
//
//    println("Time is ${measureTimeMillis { println(solution3(arr)) }} ms")
//    println("Time is ${measureTimeMillis { println(solution3(intArrayOf(5, 19, 8, 1))) }} ms")
////
//    println("Time is ${measureTimeMillis { println(solution(arr)) }} ms")
//    println("Time is ${measureTimeMillis { println(solution(intArrayOf(5, 19, 8, 1))) }} ms")
    
//    println(solution(intArrayOf(5, 19, 8, 1)))
//    println(solution(intArrayOf(5, 0, 3)))
//    println(solution(intArrayOf(10, 10)))
//    println(solution(intArrayOf()))

//    println(measureTimeMillis {  println(buyCoffee(intArrayOf(1,2,1,5,10), intArrayOf(1,2,10,8,5,4,20)))})
//    println(measureTimeMillis {  println(buyCoffee(intArrayOf(10,20,30), intArrayOf(1,2,3,4,5)))})
//    numberOfSubstring("abcab", "a b")
//    numberOfSubstring("abcde", "f g h i j k l")
 //   numberOfSubstring("aaaaaaaaaa", "a")
//    println(solve("Codility We test coders", 14))
//    println(solve("Why not", 100))
//    println(solve("The quick brown fox jumps over the lazy dog", 39))

//    println(solution(intArrayOf(1,4,1), intArrayOf(1,5,1)))
//    println(solution(intArrayOf(4,4,2,4), intArrayOf(5,5,2,5)))
//    println(solution(intArrayOf(2,3,4,2), intArrayOf(2,5,7,2)))
//    println(numberOfCarryOperation(55, 29))
//    println(numberOfCarryOperation(5, 6))
//    println(numberOfCarryOperation(786, 457))
//    println(numberOfCarryOperation(65, 55))
//    println(numberOfCarryOperation(123, 456))
//    println(numberOfCarryOperation(555, 555))
//    println(numberOfCarryOperation(900, 11))
//    println(numberOfCarryOperation(145, 55))
//    println(numberOfCarryOperation(0, 0))
//    println(numberOfCarryOperation(1, 99999))
//    println(numberOfCarryOperation(999045, 1055))
//    println(numberOfCarryOperation(101, 809))
//    println(numberOfCarryOperation(189, 209))
//    mineSweeper(listOf("XOO", "OOO", "XXO"))
//    println()
//    mineSweeper(listOf("XOOXXXOO", "OOOOXOXX", "XXOXXOOO", "OXOOOXXX", "OOXXXXOX", "XOXXXOXO", "OOOXOXOX", "XOXXOXOX"))
//    println()
//    mineSweeper(listOf("OOOXXXOXX", "XXXXXXOXX", "XOOXXXXXX", "OOXXOXOXX", "XXXXXXXXX"))
    printNumbersRec(10)
}

fun printNumbersRec(num: Int) {
    if (num < 1) return
    printNumbersRec(num - 1)
    println(num)
}

// X 1 1 X X X 3 2
// 3 3 3 5 X 5 X X
// X X 3 X X 5 5 4
// 3 X 5 5 6 X X X
// 2 4 X X X X 6 X
// X 3 X X X 5 X 3
// 2 4 5 X 6 X 5 X
// X 2 X X 4 X 4 X

// 2 3 4 X X X 4 X X
// X X X X X X 7 X X
// X 5 6 X X X X X X
// 3 5 X X 8 X 8 X X
// X X X X X X X X X

fun solution(A: IntArray): Int {
    var sum = A.sum()
    val target = sum / 2
    var count = 0

    while (sum > target) {
        var max = A[0]
        var maxIndex = 0

        for (i in 1..A.lastIndex) {
            val element = A[i]
            if (element > max) {
                max = element
                maxIndex = i
            }
        }
        A[maxIndex] = max / 2
        count++
        sum = sum - max + (max / 2)
    }
    return count
}

fun solution2(A: IntArray): Int {
    val target = A.sum() / 2
    val copy = A.toMutableList()
    var sum = copy.sum()
    var count = 0
    while (sum > target) {
        val max = copy.maxOrNull()!!
        val maxIndex = copy.indexOf(max)
        copy[maxIndex] = max / 2
        count++
        sum = copy.sum()
    }
    return count
}

fun buyCoffee(coffee: IntArray, amount: IntArray): List<Int> {
    val result = mutableListOf<Int>()
    amount.forEach { amt ->
        val count: Int = coffee.filter { it <= amt }.size
        result.add(count)
    }
    return result
}

fun numberOfSubstring(str: String, char: String) {
    val availableCharacters = char.replace(" ", "").toCharArray().toMutableList()
    var count = 0
    val str1 = str.toMutableList()
    for (j in availableCharacters.indices) {
        var i = 0
        while(str1.isNotEmpty()) {
            val letter = str[i]
            if (availableCharacters.contains(letter)) {
            //    val substring = str.substring(0, i + 1)
                count++
            }
            if (i > 0 && i < str1.size) {
                str1.remove(str1[i])
            } else {
                str1.remove(str1[0])
            }
        }
        if (j > 0 && j <= availableCharacters.size) {
            availableCharacters.remove(availableCharacters[j - 1])
        } else {
            availableCharacters.remove(availableCharacters[0])
        }

    }
    println(count)
}

fun solve(s: String, K: Int): String {
    val stringLength = s.length
    val truncatedString = s.take(K)
    var spaceCount = 0

    if (K >= stringLength) return s

    if (s[K] == ' ') return truncatedString

    for (i in truncatedString.lastIndex downTo 0) {
        if (truncatedString[i] == ' ') {
            spaceCount = i
            break
        }
    }

    return if (spaceCount == 0) "" else truncatedString.take(spaceCount)
}

fun solution(P : IntArray, S: IntArray): Int{
    var totalPeople = P.sum()
    S.sortDescending()
    var currentIndex = 0

    while (totalPeople > 0){
        totalPeople -= S[currentIndex]
        currentIndex++
    }
    return currentIndex
}

fun solution3(A : IntArray): Int{

    var noOfFiltersRequired = 0
    var totalPollutionSum = A.sum()
    val expectedPollutionDecrease = totalPollutionSum / 2
    while (expectedPollutionDecrease < totalPollutionSum){
        noOfFiltersRequired++
        A.sortDescending()
        A[0] = A[0] / 2
        totalPollutionSum = A.sum()
    }

    return noOfFiltersRequired
}

fun domino(str: String): Int {
    if (str.isBlank() || str.length < 3) return 0

    var matchingDomino = 1
    val dominoStr = str.split(",")

    for (i in 0 until dominoStr.lastIndex) {
        val firstDomino = dominoStr[i]
        val secondDomino = dominoStr[i + 1]
        if (firstDomino[2] == secondDomino[0]) matchingDomino++
    }
    return matchingDomino
}

fun numberOfCarryOperation(a: Int, b: Int): Int {
    if (a == 0 && b == 0) return 0
    var firstNumber = a
    var secondNumber = b
    var carryCount = 0
    var carry = 0

    while (firstNumber != 0 || secondNumber != 0) {
        val value = firstNumber % 10 + secondNumber % 10 + carry
        carry = if (value > 9) {
            carryCount++
            1
        } else {
            0
        }
        firstNumber /= 10
        secondNumber /= 10
    }
    return carryCount
}

// X O O      X 1 0
// O O O  ->  3 3 1
// X X O      X X 1

fun mineSweeper(arr: List<String>) {
    var count = 0
    for (i in arr.indices) {
        for (j in arr[i].indices) {
            if (arr[i][j] == 'X') print("X ")
            if (arr[i][j] == 'O') {
                if (j > 0 && arr[i][j - 1] == 'X') count++
                if (j < arr[i].lastIndex && arr[i][j + 1] == 'X') count++
                if (i > 0 && j > 0 && arr[i - 1][j - 1] == 'X') count++
                if (i > 0 && arr[i - 1][j] == 'X') count++
                if (i > 0 && j < arr[i].lastIndex && arr[i - 1][j + 1] == 'X') count++
                if (i < arr.lastIndex && j > 0 && arr[i + 1][j - 1] == 'X') count++
                if (i < arr.lastIndex && arr[i + 1][j] == 'X') count++
                if (i < arr.lastIndex && j < arr[i].lastIndex && arr[i + 1][j + 1] == 'X') count++
                print("$count ")
            }
            count = 0

        }
        println()
    }
}

//fun solution(A: IntArray): Int {
//    val n = A.size
//    val present = BooleanArray(n + 1)
//
//    for (i in 0 until n) {
//        if (A[i] in 1..n) present[A[i]] = true
//    }
//
//    for (i in 1..n) {
//        if (!present[i]) return i
//    }
//
//    return n + 1
//}

//fun solution1(A: IntArray): Int {
//
//
//    // To mark the occurrence of elements
//    val n = A.size
//    val present = BooleanArray(n + 1)
//
//    // Mark the occurrences
//    for (i in 0 until n) {
//
//        // Only mark the required elements
//        // All non-positive elements and
//        // the elements greater n + 1 will never
//        // be the answer
//        // For example, the array will be {1, 2, 3}
//        // in the worst case and the result
//        // will be 4 which is n + 1
//        if (A[i] > 0 && A[i] <= n) present[A[i]] = true
//    }
//
//    // Find the first element which didn't
//    // appear in the original array
//    for (i in 1..n) if (!present[i]) return i
//
//    // If the original array was of the
//    // type {1, 2, 3} in its sorted form
//    return n + 1
//}

// "We test coders. Give us a try?"

//fun soultion1(s: String): Int {
//    val wordList: MutableList<List<String>> = mutableListOf()
//    val result = s.split(".").joinToString("_")
//        .split("?").joinToString("_").split("!")
//        .joinToString("_").split("_")
//
//    println("Result is: ${result}")
//
//    val newResult = result.dropLast(1)
//    println(newResult)
//    for (i in newResult) {
//        if (i.isBlank()) continue
//
//        wordList.add(i.trim().split(" "))
//    }
//
//    println(wordList)
//
//  var res = 0
//
//    for(i in wordList){
//        var count = 0
//
//        for (j in i){
//            if (j.isNotEmpty()){
//                println(j)
//                count++
//            }
//        }
//
//        if(count > res) res = count
//
//    }
//  //  return wordList.maxBy { it.size }!!.size
//
//    return res

//    println(wordList)
//
//    return -1
//}

//function maxWordsCounter(S) {
//    var sentencesList = [];
//    var wordsList = [];
//    var lenList = [];
//    var i, j, wordCounter = 0;
//    var updS = S;
//
//    updS = updS.replace(/\.|\!|\?/g, '|'); // replace '.!?' with '|' (pipe symbol)
//    sentencesList = updS.split('|');
//    for (var i in sentencesList) {
//        wordsList = [];
//        wordsList = sentencesList[i].split(' ');
//        for (var j in wordsList) {
//            if(wordsList[j].trim() !== '') {
//                wordCounter++;
//            }
//        }
//        lenList.push(wordCounter);
//        wordCounter = 0;
//    }
//    return Math.max(...lenList);
//}