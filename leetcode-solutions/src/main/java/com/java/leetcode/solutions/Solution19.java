package com.java.leetcode.solutions;

import com.alibaba.fastjson.JSON;
import com.java.leetcode.domain.ListNode;
import java.util.Arrays;

/**
 * @author: mark
 * @date: 2021/4/3
 */
public class Solution19 {
  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode pre = null;
    ListNode remove = null;
    ListNode temp = head;
    int step = 1;
    while (temp != null) {
      if (step == n) {
        remove = head;
      } else if (step > n) {
        pre = remove;
        remove = remove.next;
      }
      temp = temp.next;
      step++;
    }
    if (pre == null) {
      head = head.next;
    } else {
      pre.next = remove.next;
    }
    return head;
  }

  /**
   * 技巧:使用一个dummy节点，不需要处理head的情况
   *
   * @param head
   * @param n
   * @return
   */
  public ListNode removeNthFromEnd2(ListNode head, int n) {
    ListNode dummy = new ListNode(-1, head);
    ListNode pre = dummy;
    ListNode slow = head;
    ListNode fast = head;
    for (int i = 0; i < n; i++) {
      fast = fast.next;
    }
    while (fast != null) {
      pre = pre.next;
      slow = slow.next;
      fast = fast.next;
    }
    pre.next = slow.next;
    return dummy.next;
  }

  /**
   * <p>递归版本</p>
   *
   */
  private int cur = 0;

  public ListNode removeNthFromEnd3(ListNode head, int n) {
    if (head == null) {
      return null;
    }
    head.next = removeNthFromEnd3(head.next, n);
    cur++;
    if (cur == n) {
      return head.next;
    }
    return head;
  }

  public static void main(String[] args) {
    int[] values = {1, 2, 3, 4, 5};
    ListNode head = ListNode.genListNode(values);
    ListNode ans = new Solution19().removeNthFromEnd3(head, 2);
    System.out.println(JSON.toJSONString(ans.listValues()));
  }
}
