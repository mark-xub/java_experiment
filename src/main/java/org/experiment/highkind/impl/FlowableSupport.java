package org.experiment.highkind.impl;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.google.common.collect.Lists;
import io.reactivex.Flowable;
import org.experiment.highkind.AsyncSupport;
import org.experiment.highkind.Type.App;
import org.experiment.highkind.Type.Constructor;
import org.experiment.highkind.impl.FlowableConstructor.Is;

import static java.util.stream.Collectors.toList;

/**
 * @author 许斌 ,mark.xub@alibaba-inc.com
 * @date 2018/08/01
 */
public class FlowableSupport<TC extends Constructor> implements AsyncSupport<TC> {
  private final FlowableConstructor.Is<TC> tyConstrKnowledge;

  public FlowableSupport(Is<TC> tyConstrKnowledge) {
    this.tyConstrKnowledge = tyConstrKnowledge;
  }

  @Override
  public <T> App<TC, T> success(T value) {
    return tyConstrKnowledge.convertToTypeApp(Flowable.just(value));
  }

  @Override
  public <T, R> App<TC, R> map(App<TC, T> self, Function<T, R> f) {
    return tyConstrKnowledge.convertToTypeApp(
      tyConstrKnowledge.convertToFlowable(self).map(value -> f.apply(value))
    );
  }

  @Override
  public <T, R> App<TC, R> flatMap(App<TC, T> self, Function<T, App<TC, R>> f) {
    return tyConstrKnowledge.convertToTypeApp(
      tyConstrKnowledge.convertToFlowable(self).flatMap(value -> tyConstrKnowledge.convertToFlowable(f.apply(value)))
    );
  }

  @Override
  public <T> App<TC, List<T>> sequence(Iterable<App<TC, T>> self) {
    List<Flowable<T>> flowableList =
      Lists.newArrayList(self)
        .stream()
        .map(one -> tyConstrKnowledge.convertToFlowable(one)).collect(toList());
    Flowable<List<T>> listFlowable = Flowable.zip(flowableList,
      objects -> Arrays.stream(objects).map(obj -> (T)obj).collect(toList()));
    return tyConstrKnowledge.convertToTypeApp(listFlowable);
  }
}
