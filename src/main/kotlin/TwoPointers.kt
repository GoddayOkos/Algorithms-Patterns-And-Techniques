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
        println(arr.toList())
        return nextElement
    }
}