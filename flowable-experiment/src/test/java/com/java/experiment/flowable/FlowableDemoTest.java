package com.java.experiment.flowable;

import java.util.concurrent.CompletableFuture;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Emitter;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.internal.schedulers.ImmediateThinScheduler;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;


/**
 * @author 许斌 ,mark.xub@alibaba-inc.com
 * @date 2018/08/28
 */
public class FlowableDemoTest {

  @Test
  public void mapTest() {
    Flowable.just(1)
            .map(i -> Flowable.just(i).observeOn(Schedulers.io()).subscribe(one -> System.out.println(i + Thread.currentThread().getName())))
            .subscribe(one -> System.out.println(one + Thread.currentThread().getName()));

    Flowable.just(2)
            .flatMap(i -> Flowable.just(i).observeOn(ImmediateThinScheduler.INSTANCE))
            .subscribe(one -> System.out.println(one + Thread.currentThread().getName()));

  }


  @Test
  public void boundaryBuffer() throws InterruptedException {
    PublishProcessor<Integer> boundaryBuffer = PublishProcessor.create();
    PublishProcessor<Integer> source = PublishProcessor.create();
    source.buffer(2).subscribe(s -> {
      System.out.println("-------"+s);
      boundaryBuffer.onNext(999);
    });
    source.buffer(boundaryBuffer).subscribe(l -> System.out.println(l));

    source.onNext(1);
    source.onNext(2);
    source.onNext(3);
    //boundaryBuffer.onNext(1);
    source.onComplete();
    Thread.sleep(200);
  }

  @Test
  public void multithread() {
    FlowableCallback flowCallback;
    Flowable.create(new FlowableOnSubscribe<Integer>() {
      @Override
      public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
        System.out.println("-------" + Thread.currentThread().getName());
        FlowableCallback<Integer> callback = new FlowableCallback<Integer>() {
          @Override
          public void onEvent(Integer e) {
            emitter.onNext(e);
          }

          @Override
          public void onFail(Throwable e) {
            emitter.onError(e);
          }

          @Override
          public void onComplete() {
            emitter.onComplete();
          }
        };
      }
    }, BackpressureStrategy.BUFFER)
    .subscribeOn(Schedulers.single())
    .observeOn(Schedulers.io())
    .subscribe(value -> {
      System.out.println("++++++++"+ Thread.currentThread().getName());
    });

  }

}