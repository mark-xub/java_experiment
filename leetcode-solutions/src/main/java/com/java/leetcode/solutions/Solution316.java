package com.java.leetcode.solutions;

/**
 * @author: mark
 * @date: 2021/4/4
 */
public class Solution316 {
  public String removeDuplicateLetters(String s) {
    int[] num = new int[26];
    char charA = 'a';
    for(int i=0;i < s.length(); i++){
      num[s.charAt(i)-charA]++;
    }
    boolean[] visited = new boolean[26];
    char [] stack = new char[s.length()];
    int top = -1;
    for(int i=0; i < s.length(); i++) {
      char ch = s.charAt(i);
      if(!visited[ch-charA]) {
        while(top >= 0 // stack有元素
            && stack[top] > ch // 当前字符 < 栈顶元素
            && num[stack[top]-charA] > 0){ // 栈顶元素之后还会出现
          visited[stack[top]-charA] = false;
          top--;
        }
        top++;
        stack[top] = ch;
        visited[ch-charA]=true;
      }
      num[ch-charA]--;
    }
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i <= top; i++) {
      sb.append(stack[i]);
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    String res = new Solution316().removeDuplicateLetters("cdadabcc");
    System.out.println(res);
  }
}
