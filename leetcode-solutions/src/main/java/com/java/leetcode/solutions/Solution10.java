package com.java.leetcode.solutions;

/**
 * @author: mark
 * @date: 2021/4/13
 */
public class Solution10 {
  public boolean isMatch(String s, String p) {
    if(s == null && p == null) {
      return true;
    }
    if(s != null && p == null) {
      return false;
    }
    if(s == null) {
      s = "a";
    } else {
      s = "a"+s;
    }
    p = "a"+p;
    int m = s.length();
    int n = p.length();
    boolean [][] dp = new boolean[m][n];
    dp[0][0] = true;

    for(int i = 0; i < m; i++) {
      for(int j = 1; j < n; j++) {
        if(p.charAt(j) == '*') {
          if(j-2>=0 && dp[i][j-2]) {
            dp[i][j] = true;
            continue;
          }
          if(i-1>=0 && s.charAt(i-1) == s.charAt(i)) {
            dp[i][j] = true;
            continue;
          }
          if(j-1>=0 && i-1>=0 && p.charAt(j-1) == '.') {
            dp[i][j] = true;
            continue;
          }
        }
        else if(p.charAt(j) == '.') {
          if(i-1>=0 && dp[i-1][j-1]) {
            dp[i][j] = true;
            continue;
          }
        }
        else {
          if(i-1>=0 && j-1>=0
              && dp[i-1][j-1]
              && s.charAt(i) == p.charAt(j)) {
            dp[i][j] = true;
            continue;
          }
        }
      }
    }
    return dp[m-1][n-1];
  }

  public static void main(String[] args) {
//    System.out.println(new Solution10().isMatch(null, "c*a*b*"));
//    System.out.println(new Solution10().isMatch("ab", ".*"));
//    System.out.println(new Solution10().isMatch("aab", "c*a*b"));
    System.out.println(new Solution10().isMatch("mississippi", "mis*is*p*."));


  }
}
