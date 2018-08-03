package org.experiment.highkind.impl;

import java.util.List;

/**
 * @author 许斌 ,mark.xub@alibaba-inc.com
 * @date 2018/08/01
 */
public class DefaultExecutionResult extends AbstractExecutionResult {
  public DefaultExecutionResult(
    final String name,
    final Object data,
    final List<Object> errors) {
    super(name,
      data,
      errors);
  }

  public static final DefaultExecutionResultBuilder newBuilder() {
    return new DefaultExecutionResultBuilder();
  }

  public static final class DefaultExecutionResultBuilder {
    private String name;
    private Object data;
    private List<Object> errors;

    private DefaultExecutionResultBuilder() {
    }

    public static DefaultExecutionResultBuilder aDefaultExecutionResult() {
      return new DefaultExecutionResultBuilder();
    }

    public DefaultExecutionResultBuilder setName(String name) {
      this.name = name;
      return this;
    }

    public DefaultExecutionResultBuilder setData(Object data) {
      this.data = data;
      return this;
    }

    public DefaultExecutionResultBuilder setErrors(List<Object> errors) {
      this.errors = errors;
      return this;
    }

    public DefaultExecutionResult build() {
      return new DefaultExecutionResult(name,
        data,
        errors);
    }
  }
}