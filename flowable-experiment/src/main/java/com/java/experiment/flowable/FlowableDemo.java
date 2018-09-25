package com.java.experiment.flowable;

import java.io.IOException;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author 许斌 ,mark.xub@alibaba-inc.com
 * @date 2018/08/25
 */
public class FlowableDemo {
  public static void main(String[] args) throws IOException {
    Flowable
      .fromCallable(() -> {
        System.out.println("t1=" + Thread.currentThread());
        //此处可能有远程IO操作
        return 1;
      })
      ////此处重量级操作
      .subscribeOn(Schedulers.io())
      .map(c->{
        System.out.println("t2=" + Thread.currentThread());
        return c;
      })
      .observeOn(Schedulers.computation())
      .flatMap(c->{
        //此处还有远程IO操作
        System.out.println("t5=" + Thread.currentThread());
        return Flowable.just(c);
      })
      //.observeOn(Schedulers.computation())
      .subscribe(c -> {
        //此处轻量级操作
        System.out.println("t3=" + Thread.currentThread());
      }, throwable -> {
        throwable.printStackTrace();
        //RxJava2Debug
        //  .getEnhancedStackTrace(throwable)
        //  .printStackTrace();
      });

    System.in.read();

  }
}
