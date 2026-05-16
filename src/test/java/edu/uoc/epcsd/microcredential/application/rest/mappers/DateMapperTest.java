package edu.uoc.epcsd.microcredential.application.rest.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.ZoneId;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

/** Unit tests for {@link DateMapper} default implementation. */
class DateMapperTest {

  /** The Date mapper. */
  DateMapper dateMapper = new DateMapper() {};

  /** Test to api when local date is null then return null. */
  @Test
  void testToApi_whenLocalDateIsNull_thenReturnNull() {
    assertNull(this.dateMapper.toApi(null));
  }

  /**
   * Test to api when local date provided then return offset date time at start of day system zone.
   */
  @Test
  void testToApi_whenLocalDateProvided_thenReturnOffsetDateTimeAtStartOfDaySystemZone() {

    // Given
    final var localDate = Instancio.create(LocalDate.class);
    final var offsetDateTime = localDate.atStartOfDay(ZoneId.systemDefault()).toOffsetDateTime();

    // When
    final var result = this.dateMapper.toApi(localDate);

    // Then
    assertNotNull(result);
    assertEquals(offsetDateTime, result);
  }

  /** Test to domain when offset date time is null then return null. */
  @Test
  void testToDomain_whenOffsetDateTimeIsNull_thenReturnNull() {
    assertNull(this.dateMapper.toDomain(null));
  }

  /** Test to domain when offset date time provided then return local date. */
  @Test
  void testToDomain_whenOffsetDateTimeProvided_thenReturnLocalDate() {

    // Given
    final var localDate = Instancio.create(LocalDate.class);
    final var offsetDateTime = localDate.atStartOfDay(ZoneId.systemDefault()).toOffsetDateTime();

    // When
    final var result = this.dateMapper.toDomain(offsetDateTime);

    // Then
    assertNotNull(result);
    assertEquals(localDate, result);
  }
}
