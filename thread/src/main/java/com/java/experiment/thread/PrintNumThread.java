package com.java.experiment.thread;

import java.util.List;
import java.util.function.Supplier;

/**
 * @Author: mark
 * @Date: 2020/8/1
 */
public class PrintNumThread implements Runnable {

  private Object lock;
  private Object nextLock;
  private final int endPoint;
  private Supplier<Integer> consumer;

  public PrintNumThread(int num, List<Object> locks, int endPoint, Supplier<Integer> consumer) {
    this.lock = locks.get(num);
    // 循环第一个
    if (num + 1 == locks.size()) {
      this.nextLock = locks.get(0);
    } else {
      this.nextLock = locks.get(num + 1);
    }
    this.endPoint = endPoint;
    this.consumer = consumer;
  }

  @Override
  public void run() {
    int num = 0;
    synchronized (lock) {
      do {
        try {
          lock.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        num = consumer.get();
        synchronized (nextLock) {
          nextLock.notifyAll();
        }
      } while (num < endPoint);
    }
  }
}
