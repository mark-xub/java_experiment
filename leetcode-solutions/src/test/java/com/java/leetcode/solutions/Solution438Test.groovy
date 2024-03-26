package com.java.leetcode.solutions

import spock.lang.*

/**
 * @Description
 * @Author mark.xub
 */
class Solution438Test extends Specification {

    @Unroll
    def "test find Anagrams"() {
        given:
        def solution = new Solution438();
        when:
        List<Integer> result = solution.findAnagrams(s, p)
        then:
        result == except
        where:
        s            | p     || except
        "cbaebabacd" | "abc" || [0, 6]


    }
}