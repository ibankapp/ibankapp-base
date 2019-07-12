package org.ibankapp.base.util.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.ibankapp.base.util.ISOLocalDateSerializer;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class ISOLocalDateSerializerTest {

  @Test
  public void testISOLcoalDateSerializer() throws JsonProcessingException {
    SutObject sut = new SutObject(LocalDate.of(2019, 7, 12));
    String sutJson = new ObjectMapper().writeValueAsString(sut);
    Assert.assertEquals("{\"date\":\"2019-07-12\"}", sutJson);
  }

  private static class SutObject {
    @JsonSerialize(using = ISOLocalDateSerializer.class)
    private LocalDate date;

    SutObject(LocalDate date) {
      this.date = date;
    }

  }
}
