package org.ibankapp.base.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ISOLocalDateSerializer extends StdSerializer<LocalDate> {
  public ISOLocalDateSerializer() {
    super(LocalDate.class);
  }

  @Override
  public void serialize(LocalDate value, JsonGenerator generator, SerializerProvider provider) throws IOException {
    generator.writeString(value.format(DateTimeFormatter.ISO_DATE));
  }
}