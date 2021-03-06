package com.hubspot.jinjava.lib.exptest;

import com.hubspot.jinjava.interpret.InterpretException;
import com.hubspot.jinjava.interpret.JinjavaInterpreter;

public class IsDivisibleByExpTest implements ExpTest {

  @Override
  public String getName() {
    return "divisibleby";
  }

  @Override
  public boolean evaluate(Object var, JinjavaInterpreter interpreter, Object... args) {
    if(var == null) {
      return false;
    }
    if(!Number.class.isAssignableFrom(var.getClass())) {
      return false;
    }
    
    if(args.length == 0 || args[0] == null || !Number.class.isAssignableFrom(args[0].getClass())) {
      throw new InterpretException(getName() + " test requires a numeric argument");
    }
    
    return ((Number) var).intValue() % ((Number) args[0]).intValue() == 0;
  }

}
