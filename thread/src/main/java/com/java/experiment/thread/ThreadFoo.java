package com.java.experiment.thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ThreadFoo {

  public static volatile Integer num = 1;

  public static final int threadNum = 3;

  public static final int endPoint = 20;

  public static void main(String[] args) throws Exception {
    List<Object> lockLists = generateLocks(threadNum);
    List<Supplier<Integer>> consumerList = generateConsumers(threadNum);
    CompletableFuture<Void>[] cfs = new CompletableFuture[threadNum];
    for (int i = 0; i < threadNum; i++) {
      cfs[i] = CompletableFuture
          .runAsync(new PrintNumThread(i, lockLists, endPoint, consumerList.get(i)));
    }
    Thread.sleep(300);
    synchronized (lockLists.get(0)) {
      lockLists.get(0).notifyAll();
    }
    CompletableFuture.allOf(cfs).join();
    System.out.printf("done");
  }

  public static List<Object> generateLocks(int size) {
    List<Object> locks = new LinkedList<>();
    for (int i = 0; i < size; i++) {
      locks.add(new Object());
    }
    return locks;
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
