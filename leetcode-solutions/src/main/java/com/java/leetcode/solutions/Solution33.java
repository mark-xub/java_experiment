package com.java.leetcode.solutions;

/**
 * @author: mark
 * @date: 2021/4/20
 */
public class Solution33 {
  public int search(int[] nums, int target) {
    int ans = findTarget(target, nums, 0, nums.length);
    return ans;
  }

  private int findTarget(int target, int[] nums, int low, int upper) {
    if(low >= upper) {
      return -1;
    }
    int mid = (low + upper)/2;
    if(target == nums[mid]) {
      return mid;
    }
    else if(target > nums[mid]) {
      if(target > nums[upper-1] && nums[upper-1] >= nums[mid]) {
        return findTarget(target, nums, low, mid);
      } else {
        return findTarget(target, nums, mid+1, upper);
      }
    }
    else {
      if(target < nums[low] && nums[low] <= nums[mid]) {
        return findTarget(target, nums, mid+1, upper);
      } else {
        return findTarget(target, nums, low, mid);
      }
    }
  }

  public static void main(String[] args) {
//    int[] nums = {4,5,6,7,0,1,2};
//    System.out.println(new Solution33().search(nums, 3));
    int[] nums1 = {3,1};
    System.out.println(new Solution33().search(nums1, 3));
//    int[] nums2 = {7,8,1,2,3,4,5,6};
//    System.out.println(new Solution33().search(nums2, 2));
  }
}
