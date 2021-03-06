package com.hubspot.jinjava.lib.exptest;

import com.hubspot.jinjava.interpret.JinjavaInterpreter;

public class IsSequenceExpTest implements ExpTest {

  @Override
  public String getName() {
    return "sequence";
  }

  @Override
  public boolean evaluate(Object var, JinjavaInterpreter interpreter, Object... args) {
    return var != null && (var.getClass().isArray() || Iterable.class.isAssignableFrom(var.getClass()));
  }

}
