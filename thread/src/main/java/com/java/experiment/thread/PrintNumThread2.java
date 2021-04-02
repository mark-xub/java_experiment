package com.java.experiment.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author: mark
 * @Date: 2020/8/9
 */
public class PrintNumThread2 {

  public static final int threadNum = 3;
  public static final int endPoint = 10000;
  private static String[] numArray = new String[endPoint];
  private static CountDownLatch allStop = new CountDownLatch(threadNum);

  static final class PrintThread implements Runnable {

    private int seq;
    private char name;

    public PrintThread(int seq) {
      this.seq = seq;
      name = (char) ('A' + seq);
    }

    @Override
    public void run() {
      for (int i = seq; i < endPoint; i += threadNum) {
        numArray[i] = this.name + "=" + (i + 1)+'\n';
      }
      allStop.countDown();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    long start = System.currentTimeMillis();
    for (int i = 0; i < threadNum-1; i++) {
      new Thread(new PrintThread(i)).start();
    }
    StringBuilder sb = new StringBuilder(endPoint);
    new PrintThread(threadNum-1).run();
    allStop.await();
    for (int i = 0; i < endPoint; i++) {
      sb.append(numArray[i]);
    }
    System.out.printf(sb.toString());
    long end = System.currentTimeMillis() - start;
    System.out.println("done, cost=" + end);
  }

}
