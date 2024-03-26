package com.java.others;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description
 * 设计文件系统
 * 你需要设计一个能提供下面两个函数的文件系统：
 * create(path, value)：创建一个新的路径，
 * 并尽可能将值 value 与路径 path 关联，然后返回 True。如果路径已经存在或者路径的父路径不存在，则返回 False。
 * get(path)：返回与路径关联的值。如果路径不存在，则返回 -1。
 * “路径”是由由一个或多个符合下述格式的字符串连接起来形成的：在 / 后跟着一个小写英文字母。
 * 例如 /leetcode 和 /leetcode/problems 都是有效的路径，但空字符串和 / 不是有效的路径。
 * 好了，接下来就请你来实现这两个函数吧！（请参考示例以获得更多信息）
 * Example 1：
 * ["FileSystem", "create", "get"] [[], ["/a", 1], ["/a"]] Output: [null, true, 1]
 * Explanation:
 * FileSystem filesystem = new FileSystem();
 * filesystem.create("/a", 1); // 返回 true
 * filesystem.get("/a"); // 返回 1
 * Example 2:
 * Input: ["FileSystem", "create", "create", "get", "create", "get"]
 * [[], ["/leet", 1], ["/leet/code", 2], ["/leet/code"], ["/c/d", 1], ["/c"]] Output: [null, true, true, 2, false, -1]
 * Explanation:
 * FileSystem filesystem = new FileSystem();
 * filesystem.create("/leet", 1); // 返回 true
 * filesystem.create("/leet/code", 2); // 返回 true
 * filesystem.get("/leet/code"); // 返回 2
 * filesystem.create("/c/d", 1); // 返回 false 因为父路径 "/c" 不存在。
 * filesystem.get("/c"); // 返回 -1
 * @Author mark.xub
 */
public class FileSystem {

    private final static String SLASH = "/";

    private final ConcurrentHashMap<String, Integer> pathMap = new ConcurrentHashMap<>();


    private boolean isParentExist(String path) {
        int lastIndex = path.lastIndexOf(SLASH);
        if(lastIndex == -1) {
            return false;
        }
        if(lastIndex == 0) {
            return true;
        }
        String parent = path.substring(0, lastIndex);
        return pathMap.containsKey(parent);
    }


    public boolean create(String path, Integer value) {
        if(StringUtils.isBlank(path) || !path.contains("/")) {
            return false;
        }
        if(isParentExist(path)) {
            pathMap.computeIfAbsent(path, key -> value);
            return true;
        }
        return false;
    }

    public Integer get(String path) {
        return pathMap.getOrDefault(path, -1);
    }

    public static void main(String[] args) {

        FileSystem fileSystem =  new FileSystem();
//        System.out.println(fileSystem.isParentExist("/cc"));
//        System.out.println(fileSystem.isParentExist("/cc/bb"));
//        System.out.println(fileSystem.isParentExist("cc"));
//        System.out.println(fileSystem.isParentExist("/"));
//        System.out.println(fileSystem.create("/c/d", 1));
        System.out.println(fileSystem.create("/c/d", 1));
        System.out.println(fileSystem.get("/c"));
    }

}
