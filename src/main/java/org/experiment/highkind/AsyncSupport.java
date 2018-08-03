package org.experiment.highkind;

import java.util.List;
import java.util.function.Function;

/**
 * @author 许斌 ,mark.xub@alibaba-inc.com
 * @date 2018/08/01
 */
public interface AsyncSupport<TC extends Type.Constructor> {

  <T> Type.App<TC, T> success(T value);

  <T, R> Type.App<TC, R> map(Type.App<TC, T> self, Function<T, R> f);

  <T, R> Type.App<TC, R> flatMap(Type.App<TC, T> self, Function<T, Type.App<TC, R>> f);

  <T> Type.App<TC, List<T>> sequence(Iterable<Type.App<TC, T>> self);

}
