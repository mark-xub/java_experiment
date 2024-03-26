package com.java.leetcode.solutions;

import java.util.*;

/**
 * @Description
 * @Author mark.xub
 */
public class Solution3Another {
    /**
     * 1. 通过map<char, int > 记录当前字母的位子
     * 2. 判断如何没有长度+1，如果有的话，
     判断当前长度,更新maxLength,
     start 从 当前字母位置往后挪
     * 3. 记录新的字母位置，记录当前长度
     * 4. 算法复杂度为O(n)
     */
    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        Map<Character, Integer> charMap = new HashMap();
        int maxLength = 0;
        int curLength = 0;
        int start = 0;
        for(int i = 0; i < length; i++) {
            if(!charMap.containsKey(s.charAt(i)) || charMap.get(s.charAt(i)) < start) {
                curLength++;
                charMap.put(s.charAt(i), i);
                maxLength = curLength > maxLength ? curLength : maxLength;
            } else {
                maxLength = curLength > maxLength ? curLength : maxLength;
                start = charMap.get(s.charAt(i)) + 1;
                curLength = i - start + 1;
                charMap.put(s.charAt(i), i);
            }
        }

        return maxLength;
    }
}
