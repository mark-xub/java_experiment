package com.java.experiment.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: mark
 * @Date: 2020/8/2
 */
public class NumberQueue {

  private int[] nums;

  private Lock lock = new ReentrantLock();

  private Condition condition = lock.newCondition();

  public NumberQueue(int[] nums) {
    this.nums = nums;
  }

  public void offer() {
    this.lock.lock();
    try {

    }finally {
      this.lock.unlock();
    }
  }

  public void take() {

  }




}
