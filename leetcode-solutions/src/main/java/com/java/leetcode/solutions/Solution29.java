package com.java.leetcode.solutions;

/**
 * @author: mark
 * @date: 2021/4/18
 */
public class Solution29 {
  public int divide(int dividend, int divisor) {
    long aDivisor = divisor;
    long aDividend = dividend;
    boolean positive = true;
    if(divisor < 0 ) {
      positive = !positive;
      aDivisor = - aDivisor;
    }
    if(dividend < 0) {
      positive = !positive;
      aDividend = -aDividend;
    }
    if(aDivisor > aDividend) {
      return 0;
    }
    long ans = 1;
    long multiDivisor = aDivisor;
    while(aDividend > multiDivisor + multiDivisor) {
      ans += ans;
      multiDivisor += multiDivisor;
    }

    ans += divide((int)(aDividend - multiDivisor), (int)aDivisor);
    if(!positive) {
      ans = - ans;
    }
    if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE) {
      return Integer.MAX_VALUE;
    }
    return (int) ans;
  }

  public static void main(String[] args) {
    int ans = new Solution29().divide(    -2147483648
        , -1);
    System.out.println(ans);
  }
}
