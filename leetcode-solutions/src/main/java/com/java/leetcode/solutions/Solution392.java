package com.java.leetcode.solutions;

/**
 * @author: mark
 * @date: 2021/4/19
 */
public class Solution392 {
  public boolean isSubsequence(String s, String t) {
    int m = s.length();
    int n = t.length();
    if (m > n) {
      return false;
    }
    if (m == n) {
      return s.equals(t);
    }
    if (m == 0) {
      return true;
    }
    boolean match = false;
    for (int i = 0, j = 0; i < m && j < n; j++) {
      if (s.charAt(i) == t.charAt(j)) {
        if (i == m - 1) {
          match = true;
        }
        i++;
      }
    }
    return match;
  }
}
