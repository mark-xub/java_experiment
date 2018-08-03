package org.experiment.highkind;

import java.util.Objects;

/**
 * @author 许斌 ,mark.xub@alibaba-inc.com
 * @date 2018/08/01
 */
public class Type {
  private Type() {
  }

  public static class Constructor {
    private Constructor() {
      throw new IllegalStateException("Shouldn't be instantiated");
    }
  }

  public static class App<TC extends Constructor, T> {
    private final Object value;

    private <FT> App(FT value) {this.value = value;}
  }

  public static class ConstructorIs<TC extends Constructor> {
    public static final Support<?> SUPPORT = new Support<>();

    private final Support<TC> support;

    public ConstructorIs(Support<TC> support) {
      Objects.requireNonNull(support);
      this.support = support;
    }

    protected <FT, T> Type.App<TC, T> unsafeConvertToTypeApp(FT value) {
      return support.unsafeConvertToTypeApp(value);
    }

    protected <FT, T> FT unsafeConvertToValue(Type.App<TC, T> value) {
      return support.unsafeConvertToValue(value);
    }

    public static class Support<TC extends Constructor> {
      private Support() {
      }

      private <FT, T> Type.App<TC, T> unsafeConvertToTypeApp(FT value) {
        return new Type.App<>(value);
      }

      @SuppressWarnings("unchecked")
      private <FT, T> FT unsafeConvertToValue(Type.App<TC, T> value) {
        return (FT)value.value;
      }
    }
  }
}
