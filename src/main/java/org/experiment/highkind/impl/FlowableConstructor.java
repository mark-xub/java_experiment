package org.experiment.highkind.impl;

import io.reactivex.Flowable;
import org.experiment.highkind.Type;

/**
 * @author 许斌 ,mark.xub@alibaba-inc.com
 * @date 2018/08/01
 */
public class FlowableConstructor {
  public static final FlowableConstructor.Is<?> GET = new FlowableConstructor.Is<>(Type.ConstructorIs.SUPPORT);

  public static class Is<TC extends Type.Constructor> extends Type.ConstructorIs<TC> {
    private Is(Type.ConstructorIs.Support<TC> support) {
      super(support);
    }

    public <T> Type.App<TC, T> convertToTypeApp(Flowable<T> value) {
      return this.unsafeConvertToTypeApp(value);
    }

    public <T> Flowable<T> convertToFlowable(Type.App<TC, T> value) {
      return this.unsafeConvertToValue(value);
    }
  }
}
