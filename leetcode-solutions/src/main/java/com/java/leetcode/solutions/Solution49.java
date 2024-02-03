package com.java.leetcode.solutions;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author mark.xub
 */
public class Solution49 {

    public List<List<String>> groupAnagrams(String[] strs) {
        return Arrays.stream(strs)
                .collect(Collectors.groupingBy(this::getOrderStr, Collectors.toList()))
                .values().stream()
                .collect(Collectors.toList());
    }

    private String getOrderStr(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> strMap = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> items = strMap.getOrDefault(key, new ArrayList<>());
            items.add(strs[i]);
            strMap.put(key, items);
        }
        return new ArrayList<>(strMap.values());
    }

//    /**
//     *  1. 每个字符串按照字典序重排，重排的str作为key，最后输出
//     */
//    public List<List<String>> groupAnagrams(String[] strs) {
//
//        Map<String, List<String>> strMap = new HashMap();
//        int size = strs.length;
//
//        for(int i = 0; i < size; i++) {
//            String key = getOrderStr(strs[i]);
//            System.out.println("key=" + key);
//            if(strMap.containsKey(key)){
//                strMap.get(key).add(strs[i]);
//            } else {
//                List<String> item = new ArrayList();
//                item.add(strs[i]);
//                strMap.put(key, item);
//            }
//        }
//
//        return strMap.entrySet().stream()
//                .map(entry -> entry.getValue())
//                .collect(Collectors.toList());
//    }
//
//    private String getOrderStr(String str) {
//        int length = str.length();
//        if(length == 1 ) {
//            return str;
//        }
//        char[] strArray = str.toCharArray();
//        for(int i = 1; i < length; i++) {
//            for(int j = i-1; j >= 0; j--) {
//                if(strArray[j+1] < strArray[j]) {
//                    char temp = strArray[j+1];
//                    strArray[j+1] = strArray[j];
//                    strArray[j] = temp;
//                }
//            }
//        }
//
//        return new String(strArray);
//}

    public static void main(String[] args) {
        List<String> stringList =
                Lists.newArrayList("eat", "tea", "tan", "ate", "nat", "bat");
        List<String> stringList2 =
                Lists.newArrayList("");
        List<List<String>> listStr =
                new Solution49().groupAnagrams2(stringList.toArray(new String[0]));
        System.out.println(listStr);
    }
}
