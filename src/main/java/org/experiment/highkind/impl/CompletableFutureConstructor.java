package org.experiment.highkind.impl;

import java.util.concurrent.CompletableFuture;

import org.experiment.highkind.Type;

/**
 * @author 许斌 ,mark.xub@alibaba-inc.com
 * @date 2018/08/01
 */
public class CompletableFutureConstructor {
  public static final Is<?> GET = new Is<>(Type.ConstructorIs.SUPPORT);

  public static class Is<TC extends Type.Constructor> extends Type.ConstructorIs<TC> {
    private Is(Type.ConstructorIs.Support<TC> support) {
      super(support);
    }

    public <T> Type.App<TC, T> convertToTypeApp(CompletableFuture<T> value) {
      return this.unsafeConvertToTypeApp(value);
    }

    public <T> CompletableFuture<T> convertToCompletableFuture(Type.App<TC, T> value) {
      return this.unsafeConvertToValue(value);
    }
  }
}
