package com.java.leetcode.solutions;

import com.java.leetcode.domain.ListNode;
import com.java.leetcode.domain.TreeNode;

/**
 * @author: mark
 * @date: 2021/4/28
 */
public class Solution109 {
  private ListNode currentHead;
  public TreeNode sortedListToBST(ListNode head) {
    currentHead = head;
    int len = listLength(head);
    TreeNode root = buildTree(0, len);
    return root;
  }
  private int listLength(ListNode head) {
    int len = 0;
    ListNode temp = head;
    while(temp != null) {
      temp = temp.next;
      len++;
    }
    return len;
  }

  private TreeNode buildTree(int low, int upper) {
    if(low >= upper) {
      return null;
    }
    int mid = (low + upper)/2;
    TreeNode node = new TreeNode();
    node.left = buildTree(low, mid);
    node.val = currentHead.val;
    currentHead = currentHead.next;
    node.right = buildTree(mid+1, upper);
    return node;
  }
}
