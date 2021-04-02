package com.java.leetcode.solutions;

import java.util.Arrays;
import java.util.Stack;

/**
 * @Author: mark
 * @Date: 2021/3/26
 */
public class Solution {

  /**
   * @param str: the string
   * @param k:   the length
   * @return: the substring with  the smallest lexicographic order
   */
  public static String deleteChar(String str, int k) {
    StringBuilder sb = new StringBuilder();
    int len = str.length();
    char[] chrStr = str.toCharArray();
    sb.append(chrStr[0]);
    int remove = len - k;
    for (int i = 1; i < len; i++) {
      if (remove > 0 && chrStr[i] < sb.charAt(sb.length() - 1)) {
        sb.deleteCharAt(sb.length() - 1);
        remove--;
      }
      sb.append(chrStr[i]);
    }
    return sb.toString().substring(0, k);
  }

  public static String deleteChar2(String str, int k) {
    Stack<Character> stack = new Stack<>();
    int len = str.length();
    int remove = len - k;
    for (int i = 0; i < len; i++) {
      while (remove > 0 && !stack.empty() && stack.peek() > str.charAt(i)) {
        stack.pop();
        remove--;
      }
      stack.push(str.charAt(i));
    }
    StringBuilder sb = new StringBuilder();
    for (Character c : stack) {
      sb.append(c);
    }
    return sb.toString().substring(0, k);
  }

  public static void main(String[] args) {
    System.out.println(deleteChar2("fskacsbi", 2));
  }
}
