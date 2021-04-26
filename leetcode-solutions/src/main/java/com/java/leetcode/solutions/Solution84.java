package com.java.leetcode.solutions;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author: mark
 * @date: 2021/4/24
 */
public class Solution84 {
  public int largestRectangleArea(int[] heights) {
    int n = heights.length;
    if(n == 0) {
      return 0;
    }
    int[] left = new int[n];
    int[] right = new int[n];
    Arrays.fill(right, n);
    Stack<Integer> stack = new Stack();
    for(int i = 0; i < n; i++) {
      while(!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
        right[stack.peek()] = i;
        stack.pop();
      }
      left[i] = stack.isEmpty() ? -1 : stack.peek();
      stack.push(i);
    }
    int res = 0;
    for(int i = 0; i < n; i++) {
      res = Math.max(res, (right[i] - left[i] - 1) * heights[i]);
    }
    return res;
  }
}
