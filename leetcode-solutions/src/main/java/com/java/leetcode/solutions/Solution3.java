package com.java.leetcode.solutions;

import com.alibaba.fastjson.JSON;
import java.util.Stack;

/**
 * @Author: mark
 * @Date: 2021/3/31
 */

class Solution3 {

  public int[] maxNumber(int[] nums1, int[] nums2, int k) {
    int len1 = nums1.length;
    int len2 = nums2.length;
    int[] maxMergeNums = null;
    for (int i = 0; i <= k; i++) {
      if (i > len1 || k - i > len2) {
        continue;
      }
      int[] maxPick1 = maxPick(nums1, i);
      int[] maxPick2 = maxPick(nums2, k - i);
      int[] mergeNums = merge(maxPick1, maxPick2, k);
      maxMergeNums = biggerNums(maxMergeNums, mergeNums, k);
    }
    return maxMergeNums;
  }

  public int[] maxPick(int[] nums, int pick) {
    if (pick == 0) {
      return null;
    }
    if (pick >= nums.length) {
      return nums;
    }
    Stack<Integer> stack = new Stack();
    int remove = nums.length - pick;
    for (int i = 0; i < nums.length; i++) {
      while (!stack.isEmpty() && stack.peek() < nums[i] && remove > 0) {
        stack.pop();
        remove--;
      }
      stack.push(nums[i]);
    }
    int[] maxpick = new int[pick];
    for (int i = 0; i < pick; i++) {
      maxpick[i] = stack.get(i);
    }
    return maxpick;
  }

  public int[] merge(int[] nums1, int[] nums2, int k) {
    if (nums1 == null) {
      return nums2;
    }
    if (nums2 == null) {
      return nums1;
    }
    int[] maxMergeNums = new int[k];
    int ni = 0;
    int nj = 0;
    for(int i = 0; i < k; i++) {
      if(compare(nums1,ni, nums2, nj, k) > 0) {
        maxMergeNums[i] = nums1[ni++];
      } else {
        maxMergeNums[i] =  nums2[nj++];
      }
    }

    return maxMergeNums;
  }

  public int[] biggerNums(int[] left, int[] right, int k) {
    if (left == null) {
      return right;
    }
    if (right == null) {
      return left;
    }
    for (int i = 0; i < k; i++) {
      if (left[i] == right[i]) {
        continue;
      } else {
        return left[i] > right[i] ? left : right;
      }
    }
    return right;
  }

  public int compare(int[] left, int ni, int[] right, int nj, int k) {
    if (ni >= left.length) {
      return -1;
    }
    if (nj >= right.length) {
      return 1;
    }
    while(ni < left.length && nj < right.length) {
      int diff = left[ni] - right[nj];
      if(diff != 0){
        return diff;
      }
      ni++;
      nj++;
    }
    return (ni-left.length) - (nj-right.length);
  }

  public static void main(String[] args) {
    int[] num1 = {8, 1, 8, 8, 6};
    int[] num2 = {4};
    int[] maxNum = new Solution3().maxNumber(num1, num2, 2);
    System.out.println(JSON.toJSONString(maxNum));
  }
}