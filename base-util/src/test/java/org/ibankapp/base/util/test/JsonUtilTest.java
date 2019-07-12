package org.ibankapp.base.util.test;

import org.ibankapp.base.util.JsonUtil;
import org.junit.Assert;
import org.junit.Test;

public class JsonUtilTest {

  @Test
  public void testToJson() {
    SutObject sutObject = new SutObject("abcd一二三四");
    String json = JsonUtil.toJson(sutObject);
    Assert.assertEquals("{\"string\":\"abcd一二三四\"}", json);
  }

  @Test
  public void testToPrettyJson() {
    SutObject sutObject = new SutObject("abcd一二三四");
    String json = JsonUtil.toPrettyJson(sutObject);
    Assert.assertEquals("{\n  \"string\" : \"abcd一二三四\"\n}", json);
  }

  private static class SutObject {

    private String string;

    SutObject(String s) {
      this.string = s;
    }

    public String getString() {
      return string;
    }
  }
}
