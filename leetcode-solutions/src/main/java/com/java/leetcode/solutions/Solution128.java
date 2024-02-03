package com.java.leetcode.solutions;


import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author mark.xub
 */
public class Solution128 {

    public int longestConsecutive(int[] nums) {
        int max = 0;
        int length = nums.length;
        Map<Integer, Integer> numMap = new HashMap<>();

        for(int i = 0; i < length; i++) {
            int y = nums[i];
            if(numMap.containsKey(y)) {
                continue;
            }
            int left = numMap.getOrDefault(y-1, 0) ;
            int right =  numMap.getOrDefault(y+1, 0) ;
            int k = left + right + 1;
            max =  k > max ? k : max;

            numMap.put(y, k);
            numMap.put(y-left, k);
            numMap.put(y+right, k);
        }

        return max;
    }
}
