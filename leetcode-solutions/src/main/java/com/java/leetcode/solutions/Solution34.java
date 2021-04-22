package com.java.leetcode.solutions;

import com.alibaba.fastjson.JSON;

/**
 * @author: mark
 * @date: 2021/4/21
 */
public class Solution34 {
  public int[] searchRange(int[] nums, int target) {
    int start = findStart(target, nums, 0, nums.length);
    int end = findEnd(target, nums, 0, nums.length);
    int [] ans = {start, end};
    return ans;
  }

  private int findStart(int target, int[] nums, int low, int upper) {
    if(low >= upper) {
      return -1;
    }
    int mid = (low + upper) / 2;
    if(nums[mid] == target) {
      if(mid-1 < low) {
        return mid;
      }
      if(nums[mid-1] != target) {
        return mid;
      }
      return findStart(target, nums, low, mid);
    }
    else if(nums[mid] < target) {
      return findStart(target, nums, mid+1, upper);
    }
    else {
      return findStart(target, nums, low, mid);
    }
  }


  private int findEnd(int target, int[] nums, int low, int upper) {
    if(low >= upper) {
      return -1;
    }
    int mid = (low + upper) / 2;
    if(nums[mid] == target) {
      if(mid+1 >= upper) {
        return mid;
      }
      if(nums[mid+1] != target) {
        return mid;
      }
      return findEnd(target, nums, mid+1, upper);
    }
    else if(nums[mid] < target) {
      return findEnd(target, nums, mid+1, upper);
    }
    else {
      return findEnd(target, nums, low, mid);
    }
  }

  public static void main(String[] args) {
    int[] nums = {5,7,7,8,8,10};
    int[] ans =  new Solution34().searchRange(nums,6 );
    System.out.println(JSON.toJSONString(ans));
  }
}
