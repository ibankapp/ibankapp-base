package org.ibankapp.base.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtil {

  private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

  public static String toJson(Object object) {
    try {
      return new ObjectMapper().writeValueAsString(object);
    } catch (JsonProcessingException e) {
      logger.error("convert to json error", e);
    }
    return null;
  }

  public static String toPrettyJson(Object object) {
    try {
      return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(object);
    } catch (JsonProcessingException e) {
      logger.error("convert to pretty json error", e);
    }
    return null;
  }
}
