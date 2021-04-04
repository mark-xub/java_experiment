package com.java.leetcode.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: mark
 * @date: 2021/4/3
 */
public class ListNode {
  public int val;
  public ListNode next;

  public ListNode() {}

  public ListNode(int val) {
    this.val = val;
  }

  public ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }

  public static ListNode genListNode(int[] values) {
    if(values == null) {
      throw new IllegalArgumentException("values must not be null");
    }
    ListNode head = buildNext(0, values);
    return head;
  }

  private static ListNode buildNext(int cur, int[] values) {
    if(cur >= values.length) {
      return null;
    }
    return new ListNode(values[cur], buildNext(cur+1, values));
  }



  public List<Integer> listValues() {
    List<Integer> values = new ArrayList<>();
    values.add(val);
    ListNode node = this;
    while(node.next != null) {
      values.add(node.next.val);
      node = node.next;
    }
    return values;
  }

}
