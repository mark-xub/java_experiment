package com.java.leetcode.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Author mark.xub
 */
public class Solution438 {
    /**
     * 1. 遍历s，判断后续 n（p的长度）是否是异位词
     * 2. 如果是异位词，加入起始位子，如果不是，向后挪
     * 3. 如何判断异位词，hashmap中size为0
     */
    public List<Integer> findAnagrams(String s, String p) {

        List<Integer> ans = new ArrayList();
        int length = s.length();
        int pLen = p.length();
        if(length < pLen) {
            return ans;
        }

        int[] pCount = new int[26];
        for(int i = 0; i < pLen; ++i) {
            pCount[p.charAt(i)-'a']++;
        }
        for(int i = 0; i < length; i++) {
            if(length -i < pLen) {
                break;
            }
            boolean isAnagrams =  isAnagrams(i, s, pCount, pLen);
            if(isAnagrams) {
                ans.add(i);
            }
        }

        return ans;
    }

    private boolean isAnagrams(int start, String s, int[] pCount, int pLen) {
        int[] sCount = new int[26];

        for(int i = 0; i < pLen; i++) {
            char c =  s.charAt(start + i);
            sCount[c-'a']++;
        }

        return Arrays.equals(sCount, pCount);
    }
}
