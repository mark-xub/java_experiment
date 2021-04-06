package com.java.leetcode.solutions;

/**
 * @author: mark
 * @date: 2021/4/6
 */
public class Solution64 {
  public int minPathSum(int[][] grid) {
    if(grid == null) {
      return 0;
    }
    int m = grid.length;
    int n = grid[0].length;
    int[][] ans = new int[m][n];
    ans[0][0] = grid[0][0];
    for(int i = 1; i < m; i++) {
      ans[i][0] = ans[i-1][0]+grid[i][0];
    }
    for(int i = 1; i < n; i++) {
      ans[0][i] = ans[0][i-1]+grid[0][i];
    }
    for(int i = 1; i < m; i++){
      for(int j= 1; j < n; j++) {
        ans[i][j] = Math.min(ans[i-1][j],ans[i][j-1]) + grid[i][j];
      }
    }
    return ans[m-1][n-1];
  }

  public static void main(String[] args) {
    int[][] grid = {
        {1, 3, 1},
        {1, 5, 1},
        {4, 2, 1}
    };
    int ans = new Solution64().minPathSum(grid);
    System.out.println(ans);
  }
}
