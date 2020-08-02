package com.java.experiment.thread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: mark
 * @Date: 2020/8/1
 */
public class ThreadDemo2 {
  public static volatile Integer num = 1;

  public static final int threadNum = 3;

  public static final int endPoint = 4;

  public static void main(String[] args) throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    List<Object> lockLists = generateLocks(threadNum);

    executorService
        .submit(new PrintNumThread(0, lockLists, endPoint, () -> {
          if(num <= endPoint) {
            System.out.println("A:" + num);
          }
          return num++;
        }));
    executorService
        .submit(new PrintNumThread( 1,lockLists, endPoint, () ->{
          if(num <= endPoint) {
            System.out.println("B:" + num);
          }
          return num++;
        }));
    executorService
        .submit(new PrintNumThread(2, lockLists, endPoint, () -> {
          if(num <= endPoint) {
            System.out.println("C:" + num);
          }
          return num++;
        }));
//    Thread.sleep(1000);
    synchronized (lockLists.get(0)) {
      lockLists.get(0).notifyAll();
    }
    executorService.shutdown();
    System.out.println("done");
  }

  public static List<Object> generateLocks(int size) {
    List<Object> locks = new LinkedList<>();
    for (int i = 0; i < size; i++) {
      locks.add(new Object());
    }
    return locks;
  }
}
