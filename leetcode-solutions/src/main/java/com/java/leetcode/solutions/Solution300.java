package com.java.leetcode.solutions;

/**
 * @author: mark
 * @date: 2021/4/5
 */
public class Solution300 {
  public int lengthOfLIS(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    int[] dp = new int[nums.length];
    dp[0] = 1;
    int maxans = 1;
    for (int i = 1; i < nums.length; i++) {
      dp[i] = 1;
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
      maxans = Math.max(maxans, dp[i]);
    }
    return maxans;
  }

  /**
   * dp[i] 代表 长度为i的最小数
   * @param nums
   * @return
   */
  public int lengthOfLIS2(int[] nums) {
    if(nums == null || nums.length == 0) {
      return 0;
    }
    int[] tails = new int[nums.length];
    int res = 0;
    for(int num : nums) {
      int i = 0, j = res;
      while(i < j) {
        int m = (i + j) / 2;
        if(tails[m] < num) i = m + 1;
        else j = m;
      }
      tails[i] = num;
      if(res == j) res++;
    }
    return res;
  }
}
