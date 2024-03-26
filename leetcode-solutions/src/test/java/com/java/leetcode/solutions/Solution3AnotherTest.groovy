package com.java.leetcode.solutions

import spock.lang.*

/**
 * @Description
 * @Author mark.xub
 */
class Solution3AnotherTest extends Specification {

    @Unroll
    def "test length Of Longest Substring"() {
        given:
        def solution = new Solution3Another();
        when:
        int result = solution.lengthOfLongestSubstring(str)
        then:
        result == except
        where:
        str        || except
        "abcabcbb" || 3
        "bbbbb"    || 1
        "pwwkew"   || 3
        "a"        || 1
        "abba"     || 2
        "tmmzuxt"  || 5


    }
}