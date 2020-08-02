package com.java.experiment.thread.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: mark
 * @Date: 2020/8/2
 */
public class NumBlockingQueue {

  private int[] nums;

  private Sync[] syncArray;


  private class Sync {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public Sync(Lock lock) {
      this.lock = lock;
    }
  }


  public NumBlockingQueue(int size) {

  }

  public void producer() {
//    this.lock.lock();
//    try {
//      condition.await();
//    } catch (Exception e) {
//
//    } finally {
//      this.lock.unlock();
//    }
  }

  public void consumer() {

  }
}
