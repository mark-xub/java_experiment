package com.java.leetcode.solutions;

/**
 * @author: mark
 * @date: 2021/4/13
 */
public class Solution5 {
  public String longestPalindrome(String s) {
    if(s.length() == 1){
      return s;
    }
    int len = s.length();
    int [][] dp = new int[len][len];
    for(int i = 0; i < len; i++) {
      dp[i][i] = 1;
    }
    int max = 1;
    for(int i = 1; i < len; i++) {
      for(int j = 0; j < i ;j++) {
        if(dp[i-1][j] != 0) {
          if(j-1 >= 0 && s.charAt(i) == s.charAt(j-1)) {
            dp[i][j-1] = dp[i-1][j] + 2;
            max = dp[i][j-1] > max ? dp[i][j-1] : max;
          }
          if(i - j == 1 && s.charAt(i) == s.charAt(j)) {
            dp[i][j] = dp[i-1][j] + 1;
            max = dp[i][j] > max ? dp[i][j] : max;
          }
        }
      }
    }
    for(int i = 0; i < len; i++) {
      for(int j = 0; j < len; j++) {
        if(dp[i][j] == max) {
          if(i+1> len){
            return s.substring(j);
          }
          return s.substring(j, i+1);
        }
      }
    }
    return null;
  }

  public static void main(String[] args) {
    System.out.println(new Solution5().longestPalindrome("babad"));
  }
}
