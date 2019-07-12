package org.ibankapp.base.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ISOLocalTimeSerializer extends StdSerializer<LocalTime> {

  public ISOLocalTimeSerializer() {
    super(LocalTime.class);
  }

  @Override
  public void serialize(LocalTime value, JsonGenerator generator, SerializerProvider provider) throws IOException {
    generator.writeString(value.format(DateTimeFormatter.ISO_TIME));
  }
}
