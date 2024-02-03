package com.java.math;

import java.util.Scanner;

/**
 * @Description
 * @Author mark.xub
 */
public class Calculator {

    private int  add(int a, int b) {
        return a+b;
    }

    private int times(int a, int b) {
        return a*b;
    }

    private int device(int a, int b) {
        return a/b;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        for(;;) {
            // 提示用户输入信息
            System.out.println("请输入第一个数：");
            // 读取用户在命令行输入的一行内容
            String userInput = scanner.nextLine();
            if("end".equals(userInput)) {
                break;
            }
            int a = Integer.valueOf(userInput);
            System.out.println("请输入第二个数：");
            String userInput2 = scanner.nextLine();
            int b = Integer.valueOf(userInput2);
            System.out.println(userInput + "+"+userInput2 +"=" + calculator.add(a, b));
            System.out.println(userInput + "*"+userInput2 +"=" + calculator.times(a, b));
            System.out.println(userInput + "/"+userInput2 +"=" + calculator.device(a, b));
        }


        // 关闭扫描器以释放资源
        scanner.close();
    }

}
