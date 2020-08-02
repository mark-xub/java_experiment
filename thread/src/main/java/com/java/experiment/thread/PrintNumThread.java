package com.java.experiment.thread;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.function.Supplier;

/**
 * @Author: mark
 * @Date: 2020/8/1
 */
public class PrintNumThread implements Runnable {

  private int seq;
  private Lock lock;
  private Condition condition;
  private Condition nextCondition;
  private final int endPoint;
  private Supplier<Integer> printAndReturnCurrent;
  private CountDownLatch countDownLatch;

  public PrintNumThread(int seq, Lock lock, List<Condition> conditionList, int endPoint,
      Supplier<Integer> printAndReturnCurrent, CountDownLatch countDownLatch) {
    this.seq = seq;
    this.lock = lock;
    this.condition = conditionList.get(seq);
    this.nextCondition = conditionList.get(seq + 1);
    this.endPoint = endPoint;
    this.printAndReturnCurrent = printAndReturnCurrent;
    this.countDownLatch = countDownLatch;
  }

  @Override
  public void run() {
    this.lock.lock();
    try {
      for (; ; ) {
        //   System.out.println("seq await:" + seq);
        countDownLatch.countDown();
        this.condition.await();
        int num = printAndReturnCurrent.get();
        //   System.out.println("seq signal:" + seq);
        this.nextCondition.signalAll();
        if (num > endPoint) {
          break;
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      this.lock.unlock();
    }

  }
}
