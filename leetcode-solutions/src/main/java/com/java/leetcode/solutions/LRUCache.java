package com.java.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

/**
 * 空构造函数
 * @author: mark
 * @date: 2021/4/22
 */
class LRUCache {
  private int capacity;

  private int size;

  private Node head;

  private Node tail;

  private Map<Integer, Node> indexMap;

  public LRUCache(int capacity) {
    this.head = new Node();
    this.tail = new Node();
    head.next = tail;
    tail.pre = head;
    this.size = 0;
    this.capacity = capacity;
    this.indexMap = new HashMap(capacity);
  }

  // 存在则放在头部
  public int get(int key) {
    if(!indexMap.containsKey(key)) {
      return -1;
    }
    Node value = indexMap.get(key);
    removeNode(value);
    addToHead(value);
    return value.val;
  }
  // 放在头部，更新索引
  // 容量处理
  public void put(int key, int value) {
    Node current;
    if(indexMap.containsKey(key)) {
      current = indexMap.get(key);
      current.val = value;
      removeNode(current);
      size--;
    }
    else {
      current = new Node(key, value);
      if(size == capacity) {
        Node last = removeLast();
        indexMap.remove(last.key);
        size--;
      }
      indexMap.put(key, current);
    }
    addToHead(current);
    size++;
  }

  private void addToHead(Node node) {
    Node preNext = head.next;
    head.next = node;
    preNext.pre = node;
    node.pre = head;
    node.next = preNext;
  }

  private void removeNode(Node node) {
    node.pre.next = node.next;
    node.next.pre = node.pre;
  }

  private Node removeLast() {
    Node last = tail.pre;
    removeNode(last);
    return last;
  }

  class Node {
    Node pre;
    Node next;
    Integer key;
    Integer val;
    public Node(){}
    public Node(int key, int val) {
      this.key = key;
      this.val = val;
    }
  }

  public static void main(String[] args) {
    LRUCache cache = new LRUCache( 1 /* 缓存容量 */ );

    cache.put(2, 1);
    System.out.println(cache.get(1));
         // 返回  1
    cache.put(3, 3);    // 该操作会使得密钥 2 作废
    cache.get(2);       // 返回 -1 (未找到)
    cache.put(4, 4);    // 该操作会使得密钥 1 作废
    cache.get(1);       // 返回 -1 (未找到)
    cache.get(3);       // 返回  3
    cache.get(4);       // 返回  4
  }
}
