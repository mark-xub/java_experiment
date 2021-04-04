package com.java.leetcode.solutions;

import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: mark
 * @date: 2021/4/4
 */
public class Solution22 {
  public List<String> generateParenthesis(int n) {
    int charNum = n * 2;
    int total = 1 << (charNum);
    List<String> totalAns = new ArrayList();
    for (int i = 0; i < total; i++) {
      char[] item = new char[charNum];
      for (int j = 0; j < charNum; j++) {
        if (((i >> j) & 1) == 1) {
          item[j] = '(';
        } else {
          item[j] = ')';
        }
      }
      totalAns.add(new String(item));
    }
    // 判断是否匹配
    List<String> ans = new ArrayList<>();
    for (int i = 0; i < total; i++) {
      if (isMatch(totalAns.get(i))) {
        ans.add(totalAns.get(i));
      }
    }
    return ans;
  }

  public boolean isMatch(String str) {
    Stack<Character> stack = new Stack();
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == '(') {
        stack.push('(');
      } else {
        if (stack.isEmpty() || stack.peek() != '(') {
          return false;
        } else {
          stack.pop();
        }
      }
    }
    return stack.isEmpty();
  }

  public List<String> generateParenthesis2(int n) {
    List<String> res = new ArrayList<>();
    dfs(n, n, "", res);
    return res;
  }

  private void dfs(int left, int right, String curStr, List<String> res) {
    if (left == 0 && right == 0) { // 左右括号都不剩余了，递归终止
      res.add(curStr);
      return;
    }
    if (left > 0) { // 如果左括号还剩余的话，可以拼接左括号
      dfs(left - 1, right, curStr + "(", res);
    }
    if (right > left) { // 如果右括号剩余多于左括号剩余的话，可以拼接右括号
      dfs(left, right - 1, curStr + ")", res);
    }
  }

  public static void main(String[] args) {
    List<String> ans = new Solution22().generateParenthesis2(2);
    System.out.println(JSON.toJSONString(ans));
  }
}
