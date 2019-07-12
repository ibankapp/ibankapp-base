package org.ibankapp.base.util.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.ibankapp.base.util.ISOLocalTimeSerializer;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;

public class ISOLocalTimeSerializerTest {

  @Test
  public void testISOLcoalTimeSerializer() throws JsonProcessingException {
    SutObject sut = new SutObject(LocalTime.of(15, 31, 34));
    String sutJson = new ObjectMapper().writeValueAsString(sut);
    Assert.assertEquals("{\"time\":\"15:31:34\"}", sutJson);
  }

  private static class SutObject {
    @JsonSerialize(using = ISOLocalTimeSerializer.class)
    private LocalTime time;

    SutObject(LocalTime time) {
      this.time = time;
    }
  }
}
