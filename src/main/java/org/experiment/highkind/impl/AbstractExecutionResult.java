package org.experiment.highkind.impl;

import java.util.List;

import org.experiment.highkind.ExecutionResult;

/**
 * @author 许斌 ,mark.xub@alibaba-inc.com
 * @date 2018/08/01
 */
public class AbstractExecutionResult implements ExecutionResult {
  private final String name;
  private final Object data;
  private final List<Object> errors;

  public AbstractExecutionResult(
    final String name,
    final Object data,
    final List<Object> errors) {
    this.name = name;
    this.data = data;
    this.errors = errors;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Object getData() {
    return data;
  }

  @Override
  public List<Object> getErrors() {
    return errors;
  }

  @Override
  public String toString() {
    return "AbstractExecutionResult{" +
      "name='" + name + '\'' +
      ", data=" + data +
      ", errors=" + errors +
      '}';
  }
}