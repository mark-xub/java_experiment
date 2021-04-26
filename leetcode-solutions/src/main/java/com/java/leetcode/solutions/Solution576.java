package com.java.leetcode.solutions;

/**
 * @author: mark
 * @date: 2021/4/26
 */
public class Solution576 {
  int[][][] cache;
  int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

  public int findPaths(int m, int n, int N, int i, int j) {
    cache = new int[m][n][N + 1];
    return backTrack(m, n, N, i, j);
  }

  public int backTrack(int m, int n, int N, int i, int j) {

    // 到外面了,步数+1
    if (i < 0 || i >= m || j < 0 || j >= n) return 1;
    else if (cache[i][j][N] != 0) return cache[i][j][N];
    // 没到,是否还能动
    // 对于直线都离不开的直接返回.即中间过远部分的直接返回
    if (i >= N && j >= N && m - i > N && n - j > N) {
      return 0;
    }
    // 能动,都走一遍
    int res = 0;
    for (int[] dir : dirs) {
      res = (res + backTrack(m, n, N - 1, i + dir[0], j + dir[1])) % 1000000007;
    }
    cache[i][j][N] = res;
    return res;
  }
}
