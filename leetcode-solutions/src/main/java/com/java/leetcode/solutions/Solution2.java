package com.java.leetcode.solutions;

import com.java.leetcode.domain.ListNode;

/**
 * 链表通常有头和尾来表示，这样迭代思路比较清晰
 * 进位的数学英语表达是carry
 * @author: mark
 * @date: 2021/5/9
 */
public class Solution2 {
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode temp1 = l1;
    ListNode temp2 = l2;
    ListNode fooHead = new ListNode();
    ListNode tail = fooHead;
    int carry = 0;
    for(;;) {
      ListNode cur = new ListNode();
      int l1value = 0;
      int l2value = 0;
      if(temp1 != null) {
        l1value = temp1.val;
        temp1= temp1.next;
      }
      if(temp2 != null) {
        l2value = temp2.val;
        temp2= temp2.next;
      }
      cur.val = (l1value + l2value + carry)%10;
      carry = (l1value + l2value + carry)/10;
      tail.next = cur;
      tail = tail.next;
      if(temp1 == null && temp2 == null) {
        if(carry > 0) {
          cur.next = new ListNode(carry);
        }
        break;
      }
    }
    return fooHead.next;
  }

  public static void main(String[] args) {
    int[] l1array = new int[] {2, 4, 3};
    ListNode l1 = ListNode.genListNode(l1array);
    int[] l2array = new int[] {5,6,4};
    ListNode l2 = ListNode.genListNode(l2array);
    ListNode ans = new Solution2().addTwoNumbers(l1, l2);
    System.out.println(ans.listValues());

  }
}
