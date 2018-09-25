package com.java.experiment.flowable;

import java.util.concurrent.TimeUnit;

import io.netty.util.concurrent.ImmediateExecutor;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * @author 许斌 ,mark.xub@alibaba-inc.com
 * @date 2018/08/27
 */
public class FlowableSchedulers {
  public static void main(String[] args) throws InterruptedException {
    Scheduler nettyIm = Schedulers.from(ImmediateExecutor.INSTANCE);
    Flowable
      .create(emitter -> {
        System.out.println("t1=" + Thread.currentThread());
        int i = 0;
        while (i++ < 10) {
          emitter.onNext("dddd:" + i);
          Thread.sleep(100);
        }
        emitter.onComplete();
      }, BackpressureStrategy.BUFFER)
      //.subscribeOn(Schedulers.computation())
      .observeOn(Schedulers.io())
      .observeOn(nettyIm)
      //.buffer(1, TimeUnit.SECONDS, Schedulers.trampoline())
      //.buffer(1, TimeUnit.SECONDS, nettyIm)
      .subscribe(list -> {
        System.out.println("t2=" + Thread.currentThread());
        //System.out.println("BlockedBufferRxTest.main:" + list.toString());
      });

    Thread.sleep(100);
  }
}
