package com.java.leetcode.solutions;

import com.alibaba.fastjson.JSON;

/**
 * @author: mark
 * @date: 2021/4/19
 */
public class Solution167 {
  public int[] twoSum(int[] numbers, int target) {
    int len  = numbers.length;
    for(int i = 0; i < len; i++) {
      Integer index =  findIndexNullable(target - numbers[i], numbers, i+1, len);
      if(index != null) {
        int[] ans = {i+1, index+1};
        return ans;
      }
    }
    return null;
  }

  private Integer findIndexNullable(int target, int[]numbers, int low, int upper){
    if(low >= upper) {
      return null;
    }
    int mid = (low+upper)/2;
    if(numbers[mid] == target) {
      return mid;
    }
    else if (numbers[mid] > target) {
      return findIndexNullable(target, numbers, low, mid);
    }
    else {
      return findIndexNullable(target, numbers, mid+1, upper);
    }
  }

  public static void main(String[] args) {
    int[] numbers = {3,24,50,79,88,150,345};
    System.out.println(JSON.toJSONString(new Solution167().twoSum(numbers, 200)));
  }
}
