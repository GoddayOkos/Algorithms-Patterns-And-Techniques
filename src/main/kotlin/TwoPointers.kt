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

}