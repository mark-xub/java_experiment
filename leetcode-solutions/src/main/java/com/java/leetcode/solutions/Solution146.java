package com.java.leetcode.solutions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author: mark
 * @date: 2021/4/11
 */
public class Solution146 {
  private int capacity;
  private LinkedList<Integer> linkedList;
  private Map<Integer,Integer> hashMap;

  public Solution146(int capacity) {
    this.capacity = capacity;
    linkedList = new LinkedList<>();
    hashMap = new HashMap<>(capacity);
  }

  public int get(int key) {
    if(!hashMap.containsKey(key)) {
      return -1;
    }
    // 性能差在这里，使用双向链表就快一点
    linkedList.remove(Integer.valueOf(key));
    linkedList.addFirst(key);
    return hashMap.get(key);
  }

  public void put(int key, int value) {
    // 如果是更新值，只要将key保持最新就可以
    if(hashMap.containsKey(key)) {
      hashMap.put(key, value);
      get(key);
    }
    //如果之前没有，需要对比是否超过了容量
    //将最后一个元素删除
    else {
      hashMap.put(key, value);
      linkedList.addFirst(key);
      if(hashMap.size() > capacity) {
        Integer last = linkedList.getLast();
        hashMap.remove(last);
        linkedList.removeLast();
      }
    }
  }

  public static void main(String[] args) {
    Solution146 solution146 =  new Solution146(2);
    solution146.put(1, 1);
    solution146.put(2, 2);
    System.out.println(solution146.get(1));
    solution146.put(3, 3);
    solution146.get(2);
    solution146.put(4, 4);
    System.out.println(solution146.get(2));
    System.out.println(solution146.get(3));
    System.out.println(solution146.get(4));
  }
}
