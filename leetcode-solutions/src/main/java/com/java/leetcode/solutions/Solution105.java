package com.java.leetcode.solutions;

import com.java.leetcode.domain.TreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: mark
 * @date: 2021/4/16
 */
public class Solution105 {
  int indexTraver = -1;
  Map<Integer, Integer> indexMap = new HashMap();

  public TreeNode buildTree(int[] preorder, int[] inorder) {
    for (int i = 0; i < inorder.length; i++) {
      indexMap.put(inorder[i], i);
    }
    return doBuildTree(preorder, inorder, 0, inorder.length);
  }

  private TreeNode doBuildTree(int[] preorder, int[] inorder, int low, int upper) {
    if (low >= upper) {
      return null;
    }
    indexTraver++;
    if (indexTraver >= preorder.length) {
      return null;
    }
    TreeNode node = new TreeNode(preorder[indexTraver]);
    int index = indexMap.get(preorder[indexTraver]);
    node.left = doBuildTree(preorder, inorder, low, index);
    node.right = doBuildTree(preorder, inorder, index + 1, upper);
    return node;
  }
}
