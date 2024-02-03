package com.java.math;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @Description
 * @Author mark.xub
 */
public class DeviceRepack {
    private List<Integer> device(int amount, int num) {
        List<Integer> repackList = Lists.newArrayList();
        Random random = new Random();
        int remain = amount;
        for(int i = 0; i < num - 1; i++) {
            int cur = random.nextInt(remain);
            remain = remain - cur;
            repackList.add(cur);
        }
        repackList.add(remain);
        return  repackList;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DeviceRepack deviceRepack = new DeviceRepack();

        for(;;) {
            // 提示用户输入信息
            System.out.println("请输入金额：");
            // 读取用户在命令行输入的一行内容
            String userInput = scanner.nextLine();
            if("end".equals(userInput)) {
                break;
            }
            int a = Integer.valueOf(userInput);
            System.out.println("请输入红包格式：");
            String userInput2 = scanner.nextLine();
            int b = Integer.valueOf(userInput2);

            List<Integer> redList = deviceRepack.device(a, b);
            System.out.println("redList = " + redList);
        }

    }
}
