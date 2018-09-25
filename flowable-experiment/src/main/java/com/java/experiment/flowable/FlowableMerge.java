package com.java.experiment.flowable;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * @author 许斌 ,mark.xub@alibaba-inc.com
 * @date 2018/09/06
 */
public class FlowableMerge {
  private FlowableProcessor<Integer> resolveOne;
  private FlowableProcessor<List<Integer>> resolveMany;

  private Flowable<Integer>  fusingFlow;

  public FlowableMerge() {
    resolveOne = PublishProcessor.<Integer>create().toSerialized();
    resolveMany = PublishProcessor.<List<Integer>>create().toSerialized();
  }

  private void initFlow() {
    //Flowable<Integer> resolveManyFlow = resolveMany.flatMap(resolveMany ->
    //  Flowa
    //)
  }
}
