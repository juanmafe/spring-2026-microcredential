package edu.uoc.epcsd.microcredential.application.rest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/** The type Microcredential object mapper. */
public class MicrocredentialObjectMapper {

  /**
   * Get object mapper.
   *
   * @return the object mapper
   */
  public static ObjectMapper get() {
    final var objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    return objectMapper;
  }
}

