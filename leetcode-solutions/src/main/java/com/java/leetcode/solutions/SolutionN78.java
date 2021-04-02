package com.java.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: mark
 * @date: 2021/4/2
 */
public class SolutionN78 {

  /**
   * 位运算,枚举
   * @param nums
   * @return
   */
  public List<List<Integer>> subsets(int[] nums) {
    int len = nums.length;
    int total = (1 << len);
    List<List<Integer>> res = new ArrayList();
    for (int i = 0; i < total; i++) {
      List<Integer> item = new ArrayList();
      for (int j = 0; j < len; j++) {
        // 位运算加括号
        if (((i >> j) & 1) == 1) {
          item.add(nums[j]);
        }
      }
      res.add(item);
    }
    return res;
  }

  private static List<List<Integer>> res = new ArrayList<>();
  private static List<Integer> item = new ArrayList<>();
  /**
   * 递归
   * @param nums
   * @return
   */
  public List<List<Integer>> subsetsDFS(int[] nums) {
    dfs(0, nums);
    return res;

  }

  private void dfs(int cur, int[] nums) {
    if(cur == nums.length) {
      res.add(new ArrayList<>(item));
      return;
    }
    item.add(nums[cur]);
    dfs(cur+1, nums);
    item.remove(item.size()-1);
    dfs(cur+1,nums);
  }

  public static void main(String[] args) {
    int[] nums = {1, 2, 3};
    List<List<Integer>> res = new SolutionN78().subsetsDFS(nums);
    System.out.println(res);
  }
}
