package com.java.leetcode.solutions;

import com.java.leetcode.domain.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: mark
 * @date: 2021/4/14
 */
public class Solution107 {
  public List<List<Integer>> levelOrderBottom(TreeNode root) {
    List<List<Integer>> ans = new ArrayList();
    dfs(ans, 0, root);
    Collections.reverse(ans);
    return  ans;
  }
  public void dfs(List<List<Integer>> ans, int depth, TreeNode node) {
    if(node == null) {
      return;
    }
    if(depth >= ans.size()){
      ans.add(new ArrayList());
    }
    ans.get(depth).add(node.val);
    dfs(ans, depth+1, node.left);
    dfs(ans, depth+1, node.right);
  }
}
