package org.experiment.highkind.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import com.google.common.collect.Lists;
import org.experiment.highkind.AsyncSupport;
import org.experiment.highkind.Type.App;
import org.experiment.highkind.Type.Constructor;
import org.experiment.highkind.impl.CompletableFutureConstructor.Is;

import static java.util.stream.Collectors.toList;

/**
 * @author 许斌 ,mark.xub@alibaba-inc.com
 * @date 2018/08/01
 */
public class CompletableFutureSupport<TC extends Constructor> implements AsyncSupport<TC> {

  private final CompletableFutureConstructor.Is<TC> tyConstrKnowledge;

  public CompletableFutureSupport(Is<TC> tyConstrKnowledge) {
    this.tyConstrKnowledge = tyConstrKnowledge;
  }

  @Override
  public <T> App<TC, T> success(T value) {
    return tyConstrKnowledge.convertToTypeApp(CompletableFuture.completedFuture(value));
  }

  @Override
  public <T, R> App<TC, R> map(App<TC, T> self, Function<T, R> f) {
    return tyConstrKnowledge.convertToTypeApp(tyConstrKnowledge.convertToCompletableFuture(self).thenApply(f));
  }

  @Override
  public <T, R> App<TC, R> flatMap(App<TC, T> self, Function<T, App<TC, R>> f) {
    return tyConstrKnowledge.convertToTypeApp(
      tyConstrKnowledge.convertToCompletableFuture(self)
        .thenCompose(value -> tyConstrKnowledge.convertToCompletableFuture(f.apply(value)))
    );
  }

  @Override
  public <T> App<TC, List<T>> sequence(Iterable<App<TC, T>> self) {
    List<CompletableFuture<T>> completableFutureList =
      Lists.newArrayList(self)
        .stream()
        .map(one -> tyConstrKnowledge.convertToCompletableFuture(one)).collect(toList());
    CompletableFuture<List<T>> listCompletableFuture = CompletableFuture.allOf(
      completableFutureList.toArray(new CompletableFuture[completableFutureList.size()]))
      .thenApply(v -> completableFutureList.stream()
        .map(CompletableFuture::join).collect(toList()));
    return tyConstrKnowledge.convertToTypeApp(listCompletableFuture);
  }
}
