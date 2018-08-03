package org.experiment.highkind;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import io.reactivex.Flowable;
import io.vavr.Tuple2;
import org.experiment.highkind.Type.App;
import org.experiment.highkind.impl.CompletableFutureConstructor;
import org.experiment.highkind.impl.CompletableFutureSupport;
import org.experiment.highkind.impl.DefaultExecutionResult;
import org.experiment.highkind.impl.FlowableConstructor;
import org.experiment.highkind.impl.FlowableSupport;

/**
 * @author 许斌 ,mark.xub@alibaba-inc.com
 * @date 2018/08/01
 */
public class Executor<TC extends Type.Constructor> {
  private AsyncSupport<TC> asyncSupport;

  public Executor(AsyncSupport<TC> asyncSupport) {
    this.asyncSupport = asyncSupport;
  }

  public <T> App<TC, ExecutionResult> successful(T value) {
    ExecutionResult r = DefaultExecutionResult.newBuilder()
      .setData(value)
      .build();
    return asyncSupport.success(r);
  }

  public App<TC, ExecutionResult> reduce(Iterable<App<TC, ExecutionResult>> results) {
    App<TC, List<ExecutionResult>> seqResults = asyncSupport.sequence(results);

    App<TC, ExecutionResult> mapResult = asyncSupport.map(seqResults, list -> {
      Map data = list.stream().map(r -> {
          String name = r.getName() == null ? r.toString() : r.getName();
          return new Tuple2(name, r.getData());
        }
      ).collect(Collectors.toMap(one -> one._1, one -> one._2));
      ExecutionResult r = DefaultExecutionResult.newBuilder()
        .setData(data)
        .build();
      return r;
    });
    return mapResult;
  }

  public static void main(String[] args) {
    Executor executor = new Executor(new CompletableFutureSupport(CompletableFutureConstructor.GET));
    CompletableFuture<ExecutionResult> resultCompletableFuture = CompletableFutureConstructor.GET
      .convertToCompletableFuture(executor.successful(1L));
    resultCompletableFuture.thenAccept(r -> System.out.println(r.getData()));

    Executor executorFlowable = new Executor(new FlowableSupport(FlowableConstructor.GET));
    Flowable<ExecutionResult> resultFlowable = FlowableConstructor.GET.convertToFlowable(
      executorFlowable.successful(1L));
    resultFlowable.subscribe(r -> System.out.println(r.getData()));

    // completable executor
    App field1 = executor.successful(1);
    App field2 = executor.successful("2");
    App field3 = executor.successful(3);
    App result = executor.reduce(Lists.newArrayList(field1, field2, field3));
    System.out.println(CompletableFutureConstructor.GET.convertToCompletableFuture(result).join());

    // flowable executor
    App field1F = executorFlowable.successful(1);
    App field2F = executorFlowable.successful("2");
    App field3F = executorFlowable.successful(3);
    App resultF = executorFlowable.reduce(Lists.newArrayList(field1F, field2F, field3F));
    System.out.println(FlowableConstructor.GET.convertToFlowable(resultF).blockingFirst());

  }
}
