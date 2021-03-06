package com.hubspot.jinjava.lib.filter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.hubspot.jinjava.interpret.JinjavaInterpreter;


public class CapitalizeFilterTest {

  JinjavaInterpreter interpreter;
  
  @Test
  public void testCapitalize() {
    assertThat(new CapitalizeFilter().filter("foo", interpreter)).isEqualTo("Foo");
  }
  
}
