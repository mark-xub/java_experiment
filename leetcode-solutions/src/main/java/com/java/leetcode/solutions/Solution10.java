package com.java.leetcode.solutions;

/**
 * @author: mark
 * @date: 2021/4/13
 */
public class Solution10 {
  public boolean isMatch(String s, String p) {
    int m = s.length();
    int n = p.length();
    boolean [][] dp = new boolean[m+1][n+1];
    dp[0][0] = true;
    for(int i = 2; i <= n; i+=2) {
      if(p.charAt(i-1) == '*') {
        dp[0][i] = dp[0][i-2];
      }
    }
    for(int i = 1; i <= m; i++) {
      for(int j = 1; j <= n; j++) {
        char sc = s.charAt(i-1);
        char pc = p.charAt(j-1);
        if(sc == pc || pc == '.') {
          dp[i][j] = dp[i-1][j-1];
        }
        else if(pc == '*') {
          if(dp[i][j-2]) {
            dp[i][j] = true;
          }
          else if(sc == p.charAt(j-2) || p.charAt(j-2) == '.'){
            dp[i][j] = dp[i-1][j];
          }
        }
      }
    }
    return dp[m][n];
  }



  public static void main(String[] args) {
//    System.out.println(new Solution10().isMatch(null, "c*a*b*"));
//    System.out.println(new Solution10().isMatch("ab", ".*"));
//    System.out.println(new Solution10().isMatch("aab", "c*a*b"));
    System.out.println(new Solution10().isMatch("mississippi", "mis*is*p*."));
    System.out.println(new Solution10().isMatch("aaa",   "ab*a*c*a"));
  }
}
