object TwoPointers {

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
}