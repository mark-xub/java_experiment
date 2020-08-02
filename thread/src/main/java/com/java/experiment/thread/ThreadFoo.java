package com.java.experiment.thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

public class ThreadFoo {

  public static volatile Integer num = 1;

  public static final int threadNum = 3;

  public static final int endPoint = 20;

  public static void main(String[] args) throws Exception {
    Lock lock = new ReentrantLock();
    List<Condition> conditionList = generateConditions(lock, threadNum);
    List<Supplier<Integer>> consumerList = generateConsumers(threadNum);
    CompletableFuture<Void>[] cfs = new CompletableFuture[threadNum];
    for (int i = 0; i < threadNum; i++) {
      cfs[i] = CompletableFuture
          .runAsync(new PrintNumThread(i, lock, conditionList, endPoint, consumerList.get(i)));
    }
    Thread.sleep(500);
    lock.lock();
    try {
      conditionList.get(0).signalAll();
    } catch (Exception e) {
    } finally {
      lock.unlock();
    }

    CompletableFuture.allOf(cfs).join();
    System.out.printf("done");
  }

  public static List<Condition> generateConditions(Lock lock, int size) {
    List<Condition> conditionList = new LinkedList<>();
    for (int i = 0; i < size; i++) {
      conditionList.add(lock.newCondition());
    }
    conditionList.add(conditionList.get(0));
 //   System.out.println("condition obj" + JSON.toJSONString(conditionList));
    return conditionList;
  }

  public static List<Supplier<Integer>> generateConsumers(int size) {
    List<Supplier<Integer>> consumerList = new ArrayList<>(size);

    Supplier<Integer> consumerA = () -> {
      if(num <= endPoint) {
        System.out.println("A:" + num);
      }
      return num++;
    };
    Supplier<Integer> consumerB = () -> {
      if(num <= endPoint) {
        System.out.println("B:" + num);
      }
      return num++;
    };
    Supplier<Integer> consumerC = () -> {
      if(num <= endPoint) {
        System.out.println("C:" + num);
      }
      return num++;
    };
    consumerList.add(consumerA);
    consumerList.add(consumerB);
    consumerList.add(consumerC);
    return consumerList;
  }
}
