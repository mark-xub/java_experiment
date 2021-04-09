package com.java.leetcode.solutions;

import com.java.leetcode.domain.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: mark
 * @date: 2021/4/9
 */
public class Solution95 {
  public List<TreeNode> generateTrees(int n) {
    if (n == 0) {
      return new ArrayList();
    }
    return gen(1, n);
  }

  public List<TreeNode> gen(int start, int end) {
    List<TreeNode> all = new ArrayList();
    if (start > end) {
      all.add(null);
      return all;
    }
    for (int i = start; i <= end; i++) {
      List<TreeNode> left = gen(start, i - 1);
      List<TreeNode> right = gen(i + 1, end);
      for (TreeNode leftNode : left) {
        for (TreeNode rightNode : right) {
          TreeNode item = new TreeNode(i);
          item.left = leftNode;
          item.right = rightNode;
          all.add(item);
        }
      }
    }
    return all;
  }
}
