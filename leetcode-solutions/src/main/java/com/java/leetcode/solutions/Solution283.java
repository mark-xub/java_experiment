package com.java.leetcode.solutions;

/**
 * @Description
 * @Author mark.xub
 */
public class Solution283 {
    public void moveZeroes(int[] nums) {
        int left = 0;
        int right = 0;
        int len = nums.length;

        while(right < len) {
           if(nums[right] != 0) {
               swap(nums, left, right);
               left++;
           }
           right++;
        }
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
