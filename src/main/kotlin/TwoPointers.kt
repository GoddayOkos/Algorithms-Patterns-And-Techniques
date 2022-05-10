import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.*


/**
 * All the functions in this singleton are related to using two pointers in
 * solving algorithms. In two pointers technique, the positions of the pointers
 * isn't fixed. You placed your pointers in any positions that makes sense to
 * the problem you're trying to solve.
 */
object TwoPointers {

    // Time complexity is O(N) and space complexity is O(1)
    fun pairWithTargetSum(arr: IntArray, targetSum: Int): IntArray {
        var start = 0  // first pointer
        var end = arr.size - 1  // second pointer

        while (start < end) {
            val currentSum = arr[start] + arr[end]
            if (currentSum == targetSum) return intArrayOf(start, end)  // We have found our pair

            if (currentSum > targetSum) end-- else start++  // increase or decrease counter
        }
        return intArrayOf(-1, -1)  // No pair matching the target
    }

    // Time complexity is O(N) and space complexity is O(N)
    fun pairWithTargetSumUsingHashMap(arr: IntArray, targetSum: Int): IntArray {
        val numMap = HashMap<Int, Int>()

        for (i in arr.indices) {
            if (numMap.containsKey(targetSum - arr[i])) {
                return intArrayOf(numMap[targetSum - arr[i]]!!, i)
            } else {
                numMap[arr[i]] = i
            }
        }
        return intArrayOf(-1, -1)
    }

    // Remove duplicates from a sorted array and return the size of the resulting subArray
    fun removeDuplicates(arr: IntArray): Int {
        if (arr.isEmpty()) return -1

        var nextNonDuplicate = 1  // first pointer

        for (i in 1..arr.lastIndex) {  // second pointer
            if (arr[nextNonDuplicate - 1] != arr[i]) {
                arr[nextNonDuplicate] = arr[i]
                nextNonDuplicate++
            }
        }
        return nextNonDuplicate
    }

    // Remove elements which == key from the arr and return the size of the resulting arr.
    fun removeElement(arr: IntArray, key: Int): Int {
        var nextElement = 0
        for (i in arr.indices) {
            if (arr[i] != key) {
                arr[nextElement] = arr[i]
                nextElement++
            }
        }
        return nextElement
    }

    fun sortedArraySquare(arr: IntArray): IntArray {
        val arrSize = arr.size
        val result = IntArray(arrSize)
        var arrLastIndex = arrSize - 1
        var left = 0
        var right = arrSize - 1

        while (left <= right) {
            val leftSquare = arr[left] * arr[left]
            val rightSquare = arr[right] * arr[right]

            if (leftSquare > rightSquare) {
                result[arrLastIndex--] = leftSquare
                left++
            } else {
                result[arrLastIndex--] = rightSquare
                right--
            }
        }
        return result
    }

    fun tripleSumToZero(arr: IntArray): List<List<Int>> {
        arr.sort()  // sort the given array
        val triplet: MutableList<List<Int>> = mutableListOf()

        for (i in 0 until arr.size - 2) {
            if (i > 0 && arr[i] == arr[i - 1]) continue  // skip repeated elements
            // search for pair whose sum == -arr[i]
            searchPair(arr, -arr[i], i + 1, triplet)
        }
        return triplet
    }

    private fun searchPair(arr: IntArray, targetSum: Int, _left: Int, triplet: MutableList<List<Int>>) {
        var left = _left
        var right = arr.size - 1
        while (left < right) {
            val currentSum = arr[left] + arr[right]
            if (currentSum == targetSum) { // found the triple
               triplet.add(listOf(-targetSum, arr[left], arr[right]))
                left++
                right--
                // skip repeated elements
                while (left < right && arr[left] == arr[left - 1]) left++
                while (left < right && arr[right] == arr[right + 1]) right--
            } else if (targetSum > currentSum) left++ else right--
        }
    }

    fun tripleSumCloseToTarget(arr: IntArray, target: Int): Int {
        if (arr.size < 3) throw IllegalArgumentException("Input array size must be greater or equal to 3")

        arr.sort()   // sorting is O(N * logN) operation
        var smallestDiff = Int.MAX_VALUE
        for (i in 0 until arr.size - 2) {
           var left = i + 1
           var right = arr.size - 1

           while (left < right) {
               // comparing the sum of three numbers to the target can cause overflow
               // so, we will try to find a target difference
               val targetDiff = target - arr[i] - arr[left] - arr[right]
               if (targetDiff == 0) {  //  we've found a triplet with an exact sum
                   return target - targetDiff  // So we return the sum of the 3 numbers
               }

               // the second part of the above 'if' is to handle the smallest sum when we have more than one solution
                if (abs(targetDiff) < abs(smallestDiff) || abs(targetDiff)
                    == abs(smallestDiff) && targetDiff > smallestDiff) {
                    smallestDiff = targetDiff  // save the closest and the biggest difference
                }

               if (targetDiff > 0) {
                   left++ // we need a triplet with a bigger sum
               } else {
                   right-- // we need a triplet with a smaller sum
               }
           }
        }

        return target - smallestDiff
    }

    fun tripletWithSmallerSum(arr: IntArray, targetSum: Int): Int {
        arr.sort()
        var count = 0

        for (i in 0 until arr.size - 2) {
            count+= searchPairLessThanTarget(arr, targetSum - arr[i], i)
        }
        return count
    }

    private fun searchPairLessThanTarget(arr: IntArray, targetSum: Int, first: Int): Int {
        var count = 0
        var left = first + 1
        var right = arr.lastIndex

        while (left < right) {
            if (arr[left] + arr[right] < targetSum) {  // found the triplet
                // since arr[right] >= arr[left], therefore, we can replace arr[right] by any number between
                // left and right to get a sum less than the target sum
                count += right - left
                left++
            } else {
                right--    // we need a pair with a smaller sum
            }
        }
        return count
    }

    fun subarrayProductLessThanK(arr: IntArray, target: Int): List<List<Int>> {
        val result: MutableList<List<Int>> = arrayListOf()
        var product = 1.0
        var left = 0

        for (i in arr.indices) {
            product *= arr[i]
            while (product >= target && left < arr.size) product /= arr[left++]
            // since the product of all numbers from left to right is less than the target therefore,
            // all subarrays from left to right will have a product less than the target too; to avoid
            // duplicates, we will start with a subarray containing only arr[right] and then extend it
            val tempList: MutableList<Int> = LinkedList()
            for (j in i downTo left) {
                tempList.add(0, arr[j])
                result.add(ArrayList(tempList))
            }
        }
        return result
    }

    fun dutchFlag(arr: IntArray): IntArray {
        var left = 0
        var right = arr.lastIndex
        var i = 0
        while (i <= right) {
            when (arr[i]) {
                 0 -> { swap(arr, i, left); i++; left++ }
                 1 -> i++
                else -> { swap(arr, i, right); right-- }
            }
        }
        return arr
    }

    private fun swap(arr: IntArray, i: Int, j: Int) {
        val temp = arr[i]
        arr[i] = arr[j]
        arr[j] = temp
    }

    fun quadrupleSumToTarget(arr: IntArray, target: Int): List<List<Int>> {
        if (arr.size < 4) throw IllegalArgumentException("Array size must be greater or equal to 4")
        arr.sort()

        val result: MutableList<List<Int>> = mutableListOf()

        for (i in 0..arr.size - 3) {
            if (i > 0 && arr[i] == arr[i - 1]) continue  // Skip repeated number to avoid duplicate quadruples
            for (j in (i + 1)..arr.size - 2) {
                if (j > i + 1 && arr[j] == arr[j - 1]) continue  // Skip repeated number to avoid duplicate quadruples
                searchQuad(arr, target, i, j, result)
            }
        }
        return result
    }

    private fun searchQuad(arr: IntArray, target: Int, first: Int, second: Int, result: MutableList<List<Int>>) {
        var left = second + 1
        var right = arr.lastIndex

        while (left < right) {
            val currentSum = arr[first] + arr[second] + arr[left] + arr[right]
            if (currentSum == target) {   // Found our quadruple
                result.add(listOf(arr[first], arr[second], arr[left], arr[right]))
                left++
                right--
                // Skip repeated elements
                while (left < right && arr[left] == arr[left - 1]) left++
                while (left < right && arr[right] == arr[right + 1]) right--
            } else if (currentSum > target) right-- else left++
        }
    }

    fun backSpaceCompare(str1: String, str2: String): Boolean {
        // use two pointer approach to compare the strings
        var index1 = str1.lastIndex
        var index2 = str2.lastIndex

        while (index1 >= 0 || index2 >= 0) {
            val i1 = getNextValidCharIndex(str1, index1)
            val i2 = getNextValidCharIndex(str2, index2)

            if (i1 < 0 && i2 < 0) return true   // Reached the end of both strings
            if (i1 < 0 || i2 < 0) return false  // Reached the end of one of the strings
            if (str1[i1] != str2[i2]) return false  // Check if the characters are the same

            index1 = i1 - 1
            index2 = i2 - 1
        }
        return true
    }

    private fun getNextValidCharIndex(str: String, index: Int): Int {
        var backSpaceCount = 0
        var i = index

        while (i >= 0) {
            if (str[i] == '#') {
                backSpaceCount++   // Found a backspace
            } else if (backSpaceCount > 0) {  // a non-backspace character
                backSpaceCount--
            } else break

            i--  // skip a backspace or a valid character
        }
        return i
    }

    fun shortestWindowSort(arr: IntArray): Int {
        var low = 0
        var high = arr.lastIndex

        // find the first number out of sorting order from the beginning
        while (low < arr.lastIndex && arr[low] <= arr[low + 1]) low++
        if (low == arr.lastIndex) return 0   // if the array is sorted already

        // find the first number out of sorting order from the end
        while (high > 0 && arr[high] >= arr[high - 1]) high--

        // Our subarray = [low, ..., high]
        // Find the minimum and maximum of our subarray
        var subarrayMax = Int.MIN_VALUE
        var subarrayMin = Int.MAX_VALUE
        for (i in low..high) {
            subarrayMax = subarrayMax.coerceAtLeast(arr[i])
            subarrayMin = subarrayMin.coerceAtMost(arr[i])
        }

        // extend the subarray to include any number which is bigger than the minimum of the subarray
        while (low > 0 && arr[low - 1] > subarrayMin) low--
        // extend the subarray to include any number which is smaller than the maximum of the subarray
        while (high < arr.lastIndex && arr[high + 1] < subarrayMax) high++

        return high - low + 1   // Return the size of our subarray
    }
}