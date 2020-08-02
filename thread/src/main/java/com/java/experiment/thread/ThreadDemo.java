package com.java.experiment.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: mark
 * @Date: 2020/8/1
 */
public class ThreadDemo {

  private static volatile int num = 1;
  private static final int endPoint = 1000;
  private static final Object lock = new Object();
  private static final Object lock2 = new Object();
  private static final Object lock3 = new Object();
  private static final Object lock4 = new Object();

  public static void main(String[] args) throws InterruptedException {

    ExecutorService executorService = Executors.newFixedThreadPool(3);
    executorService.submit(() -> {
      while(num < endPoint) {
        synchronized (lock) {
          try {
            lock.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.println("A:" + num++);
        }
        synchronized (lock2){
          lock2.notifyAll();
        }
      }
    });

    executorService.submit(() -> {
      while(num < endPoint) {
        synchronized (lock2) {
          try {
            lock2.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.println("B:" + num++);
        }
        synchronized (lock3){
          lock3.notifyAll();
        }
      }
    });

    executorService.submit(() -> {
      while(num < endPoint) {
        synchronized (lock3) {
          try {
            lock3.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.println("C:" + num++);
        }
        synchronized (lock){
          lock.notifyAll();
        }
      }
    });

    synchronized(lock) {
      lock.notifyAll();
    }
    executorService.shutdown();
//    CompletableFuture<Void> cf = CompletableFuture
//        .runAsync(() -> System.out.println("A:" + num++))
//        .thenRunAsync(() -> System.out.println("B:" + num++))
//        .thenRunAsync(() -> System.out.println("C:" + num++));
//    cf.join();
    System.out.println("done");
  }



}
