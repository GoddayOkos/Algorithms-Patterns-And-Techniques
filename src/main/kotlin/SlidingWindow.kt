
object SlidingWindow {

    fun averageOfSubArrayOfSizeK(k: Int, arr: IntArray): DoubleArray {
        val result = DoubleArray(arr.size - k + 1)
        var windowStart = 0
        var windowSum = 0.0

        for (windowEnd in arr.indices) {
            windowSum += arr[windowEnd]   // add the next element
            // slide the window, we don't need to slide if we've not hit the required window size of 'k'
            if (windowEnd >= k - 1) {
                result[windowStart] = windowSum / k    // calculate the average
                windowSum -= arr[windowStart]     // subtract the element going out
                windowStart++   // slide the window ahead
            }
        }
        return result
    }

    fun maxSumSubArrayOfSizeK(k: Int, arr: IntArray): Int {
        var windowStart = 0
        var windowSum = 0
        var maxSum = 0
        for (windowEnd in arr.indices) {
            windowSum += arr[windowEnd]
            if (windowEnd >= k - 1) {
                maxSum = maxSum.coerceAtLeast(windowSum)
               windowSum -= arr[windowStart]
               windowStart++
            }
        }
        return maxSum
    }

    fun minSizeSubArraySum(k: Int, arr: IntArray): Int {
        var minLength = arr.size
        var windowSum = 0
        var windowStart = 0
        for (windowEnd in arr.indices) {
            windowSum += arr[windowEnd]

            while (windowSum >= k) {
                minLength = minLength.coerceAtMost(windowEnd - windowStart + 1)
                windowSum -= arr[windowStart]
                windowStart++
            }
        }
        return if (minLength == arr.size) 0 else minLength
    }

    fun longestSubstringKDistinct(str: String, k: Int): Int {
        if (str.trim().isEmpty() || str.length < k) {
            throw IllegalArgumentException("You must enter a valid string")
        }

        var windowStart = 0
        var maxLength = 0
        val charFrequencyMap = HashMap<Char, Int>()
        // in the following loop we'll try to extend the range [windowStart, windowEnd]
        for (windowEnd in str.indices) {
            val rightChar = str[windowEnd]
            charFrequencyMap[rightChar] = charFrequencyMap.getOrDefault(rightChar, 0) + 1
            // shrink the sliding window, until we are left with 'k' distinct characters in the frequency map
            while (charFrequencyMap.size > k) {
                val leftChar = str[windowStart]
                charFrequencyMap[leftChar]?.minus(1)?.let { charFrequencyMap.put(leftChar, it) }
                if (charFrequencyMap[leftChar] == 0) {
                    charFrequencyMap.remove(leftChar)
                }
                windowStart++      // shrink the window
            }
            maxLength = maxLength.coerceAtLeast(windowEnd - windowStart + 1)     // remember the maximum length so far
        }
        return maxLength
    }

    fun longestUniqueSubString(str: String): Int {
        var windowStart = 0
        var maxLength = 0
        val charFrequencyMap = HashMap<Char, Int>()
        for (windowEnd in str.indices) {
            if (charFrequencyMap.contains(str[windowEnd])) {
                windowStart = windowStart.coerceAtLeast(charFrequencyMap[str[windowEnd]]!! + 1)
            }

            charFrequencyMap[str[windowEnd]] = windowEnd
            maxLength = maxLength.coerceAtLeast(windowEnd - windowStart + 1)
        }

        return maxLength
    }

    fun maxFruitCountOf2Types(arr: CharArray): Int {
        var windowStart = 0
        var maxLength = 0
        val fruitBasket = HashMap<Char, Int>()
        for (windowEnd in arr.indices) {
            fruitBasket[arr[windowEnd]] = fruitBasket.getOrDefault(arr[windowEnd], 0) + 1

            while (fruitBasket.size > 2) {
                fruitBasket[arr[windowStart]] = fruitBasket[arr[windowStart]]!! - 1
                if (fruitBasket[arr[windowStart]] == 0) {
                    fruitBasket.remove(arr[windowStart])
                }
                windowStart++
            }

            maxLength = maxLength.coerceAtLeast(windowEnd - windowStart + 1)
        }
        return maxLength
    }

    fun longestSubstringWithSameLetterAfterReplacement(str: String, k: Int): Int {
        var windowStart = 0
        var maxLength = 0
        var maxRepeatCount = 0
        val letterFreqMap = HashMap<Char, Int>()
        for (windowEnd in str.indices) {
            val rightChar = str[windowEnd]
            letterFreqMap[rightChar] = letterFreqMap.getOrDefault(rightChar, 0) + 1
            maxRepeatCount = maxRepeatCount.coerceAtLeast(letterFreqMap[rightChar]!!)

            if (windowEnd - windowStart + 1 - maxRepeatCount > k) {
                val leftChar = str[windowStart]
                letterFreqMap[leftChar] = letterFreqMap[leftChar]!! - 1
                windowStart++
            }

            maxLength = maxLength.coerceAtLeast(windowEnd - windowStart + 1)
        }
        return maxLength
    }

    fun longestSubArrayWithOnes(arr: IntArray, k: Int): Int {
        var windowStart = 0
        var maxLength = 0
        var maxRepeatCount = 0
        for (windowEnd in arr.indices) {
            if (arr[windowEnd] == 1) {
                maxRepeatCount++
            }
            if (windowEnd - windowStart + 1 - maxRepeatCount > k) {
                if (arr[windowStart] == 1) {
                    maxRepeatCount--
                }
                windowStart++
            }
            maxLength = maxLength.coerceAtLeast(windowEnd - windowStart + 1)
        }
        return maxLength
    }

    fun stringPermutation(str: String, pattern: String): Boolean {
        var windowStart = 0
        var matched = 0
        val patternFreqMap = HashMap<Char, Int>()

        for (s in pattern) patternFreqMap[s] = patternFreqMap.getOrDefault(s, 0) + 1

        for (windowEnd in str.indices) {
            val rightChar = str[windowEnd]
            if (patternFreqMap.contains(rightChar)) {
                patternFreqMap[rightChar] = patternFreqMap[rightChar]!! - 1
                if (patternFreqMap[rightChar] == 0) matched++
            }

            if (matched == patternFreqMap.size) return true

            if (windowEnd >= pattern.length - 1) {
                val leftChar = str[windowStart++]
                if (patternFreqMap.contains(leftChar)) {
                    if (patternFreqMap[leftChar] == 0) matched--
                    patternFreqMap[leftChar] = patternFreqMap[leftChar]!! + 1
                }
            }
        }
        return false
    }
}