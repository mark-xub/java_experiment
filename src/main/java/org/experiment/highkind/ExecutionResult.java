package org.experiment.highkind;

import java.util.List;

/**
 * @author 许斌 ,mark.xub@alibaba-inc.com
 * @date 2018/08/01
 */
public interface ExecutionResult {
  String getName();

  Object getData();

  List<Object> getErrors();
}
