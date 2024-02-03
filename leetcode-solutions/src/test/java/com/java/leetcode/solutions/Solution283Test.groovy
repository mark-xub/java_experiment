package com.java.leetcode.solutions

import spock.lang.*

/**
 * @Description
 * @Author mark.xub
 */
class Solution283Test extends Specification {

    @Unroll
    def "test move Zeroes"() {
        given:
        def solution283 = new Solution283()
        int[] nums = initialNums

        when:
        solution283.moveZeroes(nums)

        then:
        nums == expectedNums

        where:
        initialNums        || expectedNums
        [0, 1, 0, 3, 12]   || [1, 3, 12, 0, 0]
        [0, 0, 1, 2, 0, 3] || [1, 2, 3, 0, 0, 0]
        [1, 2, 0, 3, 0, 4] || [1, 2, 3, 4, 0, 0]
    }


}