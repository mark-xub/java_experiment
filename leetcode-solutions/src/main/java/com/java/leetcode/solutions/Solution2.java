package com.java.leetcode.solutions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @Author: mark
 * @Date: 2021/3/30
 */
public class Solution2 {

  public String removeDuplicateLetters(String s) {
    //1. 计算字母出现的次数
    int[] charNums = new int[26];
    Arrays.fill(charNums,0);
    for (int i = 0; i < s.length(); i++) {
      Character c = s.charAt(i);
      charNums[c-97]++;
    }
    Set<Character> alreadyIn = new HashSet<>();
    //2. 遍历字符串，判断与前面的字符如果后面还有并且大于当前字符，则删除，字符数-1，否则入栈
    Stack<Character> charStack = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      Character c = s.charAt(i);
      if(!alreadyIn.contains(c)) {
        while (!charStack.isEmpty() && charStack.peek() > c && charNums[charStack.peek()-97] > 0) {
          alreadyIn.remove(charStack.peek());
          charStack.pop();
        }
        charStack.push(c);
        alreadyIn.add(c);
      }
      charNums[c-97]--;
    }
    // 3. 返回字符串
    StringBuilder sb = new StringBuilder(charStack.size());
    for(Character c : charStack) {
      sb.append(c);
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    String res = new Solution2().removeDuplicateLetters("cdadabcc");
    System.out.println(res);
  }
}
