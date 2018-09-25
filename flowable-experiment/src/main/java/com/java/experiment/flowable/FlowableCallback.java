package com.java.experiment.flowable;

/**
 * @author 许斌 ,mark.xub@alibaba-inc.com
 * @date 2018/08/30
 */
public interface FlowableCallback<T> {
  void onEvent(T e);
  void onFail(Throwable e);
  void onComplete();
}
