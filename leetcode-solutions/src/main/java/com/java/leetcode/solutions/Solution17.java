package com.java.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: mark
 * @date: 2021/4/30
 */
public class Solution17 {

  private String[] phone = new String[] {"", "", "abc", "def",
      "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

  public List<String> letterCombinations(String digits) {
    int len = digits.length();
    if(len == 0) {
      return new ArrayList();
    }
    int total = 1;
    for(int i = 0; i < len; i++) {
      total *= phone[digits.charAt(i)-'0'].length();
    }
    List<String> ans = new ArrayList(total);
    for(int i = 0; i < total; i++) {
      int cur = i;
      StringBuilder sb = new StringBuilder();
      // 进制转换，除n取模当前进制数字表达
      for(int j = 0; j < len; j++) {
        int n = phone[digits.charAt(j) - '0'].length();
        int w = cur % n;
        String curStr = phone[digits.charAt(j) - '0'];
        sb.append(curStr.charAt(w));
        cur = cur / n;
      }
      ans.add(sb.toString());
    }
    return ans;
  }

  public List<String> letterCombinations2(String digits) {
    int len = digits.length();
    if(len == 0) {
      return new ArrayList();
    }
    List<String> ans = new ArrayList();
    dfs(ans, digits, new StringBuilder(), 0);
    return ans;
  }

  /**
   * 回溯，将原来的状态进行还原
   *
   * @param ans
   * @param digits
   * @param sb
   * @param index
   */
  private void dfs(List<String> ans, String digits, StringBuilder sb, int index) {
    if(index >= digits.length()) {
      ans.add(sb.toString());
      return;
    }
    int num = digits.charAt(index)- '0';
    String cur =  phone[num];
    for(int i = 0; i < cur.length(); i++) {
      sb.append(cur.charAt(i));
      dfs(ans, digits, sb, index+1);
      sb.deleteCharAt(index);
    }
  }

  /**
   * 取模，而且必须是低位取模，代表了这一位的数字
   * 除n，当前位置的数字代表了之前所有位子的进位，所有是除法
   * @param args
   */
  public static void main(String[] args) {
    int [] radix = {2, 3, 4};
    int value = 7;
    for(int j = 0; j < value; j++) {
      int cur = j;
      StringBuilder sb = new StringBuilder();
      for(int i = 0; i < radix.length; i++) {
        int r = cur % radix[i];
        cur = cur / radix[i];
        sb.append(r);
      }
      System.out.println(sb.toString());
    }
  }
}
