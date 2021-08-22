
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

    fun longestUniqueSubString(str: String): Int {
        var windowStart = 0
        var maxLength = 0
        val charFrequencyMap = HashMap<Char, Int>()
        // try to extend the range [windowStart, windowEnd]
        for (windowEnd in str.indices) {
            // if the map already contains str[windowEnd], shrink the window from the beginning so that
            // we have only one occurrence of str[windowEnd]
            if (charFrequencyMap.contains(str[windowEnd])) {
                // this is tricky; in the current window, we will not have any str[windowEnd] after its previous index
                // and if 'windowStart' is already ahead of the last index of str[windowEnd], we'll keep 'windowStart'
                windowStart = windowStart.coerceAtLeast(charFrequencyMap[str[windowEnd]]!! + 1)
            }

            charFrequencyMap[str[windowEnd]] = windowEnd
            maxLength = maxLength.coerceAtLeast(windowEnd - windowStart + 1)
        }

        return maxLength
    }

    fun longestSubstringWithSameLetterAfterReplacement(str: String, k: Int): Int {
        var windowStart = 0
        var maxLength = 0
        var maxRepeatCount = 0
        val letterFreqMap = HashMap<Char, Int>()
        // try to extend the range [windowStart, windowEnd]
        for (windowEnd in str.indices) {
            val rightChar = str[windowEnd]
            letterFreqMap[rightChar] = letterFreqMap.getOrDefault(rightChar, 0) + 1
            maxRepeatCount = maxRepeatCount.coerceAtLeast(letterFreqMap[rightChar]!!)

            // current window size is from windowStart to windowEnd, overall we have a letter which is
            // repeating 'maxRepeatLetterCount' times, this means we can have a window which has one letter
            // repeating 'maxRepeatLetterCount' times and the remaining letters we should replace.
            // if the remaining letters are more than 'k', it is the time to shrink the window as we
            // are not allowed to replace more than 'k' letters
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
        // try to extend the range [windowStart, windowEnd]
        for (windowEnd in arr.indices) {
            if (arr[windowEnd] == 1) {
                maxRepeatCount++
            }

            // current window size is from windowStart to windowEnd, overall we have a maximum of 1s
            // repeating a maximum of 'maxOnesCount' times, this means that we can have a window with
            // 'maxOnesCount' 1s and the remaining are 0s which should replace with 1s.
            // now, if the remaining 0s are more than 'k', it is the time to shrink the window as we
            // are not allowed to replace more than 'k' Os
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

        // our goal is to match all the characters from the 'charFrequencyMap' with the current window
        // try to extend the range [windowStart, windowEnd]
        for (windowEnd in str.indices) {
            val rightChar = str[windowEnd]
            if (patternFreqMap.contains(rightChar)) {
                // decrement the frequency of the matched character
                patternFreqMap[rightChar] = patternFreqMap[rightChar]!! - 1
                if (patternFreqMap[rightChar] == 0) matched++   // character is completely matched
            }

            if (matched == patternFreqMap.size) return true

            if (windowEnd >= pattern.length - 1) {
                // shrink the window by one character
                val leftChar = str[windowStart++]
                if (patternFreqMap.contains(leftChar)) {
                    // before putting the character back, decrement the matched count
                    if (patternFreqMap[leftChar] == 0) matched--
                    // put the character back for matching
                    patternFreqMap[leftChar] = patternFreqMap[leftChar]!! + 1
                }
            }
        }
        return false
    }

    fun stringAnagrams(str: String, pattern: String): List<Int> {
        var windowStart = 0
        var matched = 0
        val patternFreqMap = HashMap<Char, Int>()
        val resultIndices = mutableListOf<Int>()

        for (ch in pattern) patternFreqMap[ch] = patternFreqMap.getOrDefault(ch, 0) + 1

        for (windowEnd in str.indices) {
            val rightChar = str[windowEnd]

            if (patternFreqMap.contains(rightChar)) {
                patternFreqMap[rightChar] = patternFreqMap[rightChar]!! - 1
                if (patternFreqMap[rightChar] == 0) matched++
            }

            if (matched == patternFreqMap.size) resultIndices.add(windowStart)

            if (windowEnd >= pattern.length - 1) {
                val leftChar = str[windowStart++]
                if (patternFreqMap.contains(leftChar)) {
                    if (patternFreqMap[leftChar] == 0) matched--

                    patternFreqMap[leftChar] = patternFreqMap[leftChar]!! + 1
                }
            }
        }
        return resultIndices
    }

    fun smallestSubstringWithGivenPattern(str: String, pattern: String): String {
        var windowStart = 0
        var matched = 0
        var minLength = str.length + 1
        var substringStart = 0
        val patternFreqMap = HashMap<Char, Int>()

        for (ch in pattern) patternFreqMap[ch] = patternFreqMap.getOrDefault(ch, 0) + 1

        // try to extend the range [windowStart, windowEnd]
        for (windowEnd in str.indices) {
            val rightChar = str[windowEnd]

            if (patternFreqMap.contains(rightChar)) {
                patternFreqMap[rightChar] = patternFreqMap[rightChar]!! - 1
                if (patternFreqMap[rightChar]!! >= 0) matched++
            }

            // shrink the window if we can, finish as soon as we remove a matched character
            while (matched == pattern.length) {
                if (minLength > windowEnd - windowStart + 1) {
                    minLength = windowEnd - windowStart + 1
                    substringStart = windowStart
                }

                val leftChar = str[windowStart++]
                if (patternFreqMap.contains(leftChar)) {
                    // note that we could have redundant matching characters, therefore we'll decrement the
                    // matched count only when a useful occurrence of a matched character is going out of the window
                    if (patternFreqMap[leftChar] == 0) matched--
                    patternFreqMap[leftChar] = patternFreqMap[leftChar]!! + 1
                }
            }
        }
        return if (minLength > str.length) "" else str.substring(substringStart, substringStart + minLength)
    }

    fun wordConcatenation(str: String, words: Array<String>): List<Int> {
        val wordFreq = HashMap<String, Int>()
        val wordsCount = words.size
        val wordLength = words[0].length
        val resultIndices = mutableListOf<Int>()

        for (word in words) wordFreq[word] = wordFreq.getOrDefault(word, 0) + 1

        for (i in 0..(str.length - wordsCount * wordLength)) {
            val seenWords = HashMap<String, Int>()

            for (j in 0 until wordsCount) {
                val nextWordIndex = i + j * wordLength
                // get the next word from the string
                val word = str.substring(nextWordIndex, nextWordIndex + wordLength)

                // break if we don't need this word
                if (!wordFreq.containsKey(word)) break

                // add the word to the 'wordsSeen' map
                seenWords[word] = seenWords.getOrDefault(word, 0) + 1

                // no need to process further if the word has higher frequency than required
                if (seenWords[word]!! > wordFreq.getOrDefault(word, 0)) break

                if (j + 1 == wordsCount) resultIndices.add(i)
            }
        }
        return resultIndices
    }
}