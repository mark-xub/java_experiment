package com.java.leetcode.solutions;

import com.java.leetcode.domain.TreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: mark
 * @date: 2021/4/16
 */
public class Solution106 {
  int indexTraver;
  Map<Integer, Integer> indexMap = new HashMap();

  public TreeNode buildTree(int[] inorder, int[] postorder) {
    for (int i = 0; i < inorder.length; i++) {
      indexMap.put(inorder[i], i);
    }
    indexTraver = postorder.length;
    return doBuildTree(postorder, 0, inorder.length);
  }

  private TreeNode doBuildTree(int[] postorder, int low, int upper) {
    if (low >= upper) {
      return null;
    }
    indexTraver--;
    if (indexTraver < 0) {
      return null;
    }
    TreeNode node = new TreeNode(postorder[indexTraver]);
    int index = indexMap.get(postorder[indexTraver]);
    node.right = doBuildTree(postorder, index + 1, upper);
    node.left = doBuildTree(postorder, low, index);
    return node;
  }
}
