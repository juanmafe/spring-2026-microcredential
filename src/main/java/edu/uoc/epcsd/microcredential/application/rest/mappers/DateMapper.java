package edu.uoc.epcsd.microcredential.application.rest.mappers;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Objects;
import org.mapstruct.Mapper;

/** The interface Date mapper. */
@Mapper(componentModel = "spring", implementationName = "DateMapperFromRest")
public interface DateMapper {

  /**
   * To api offset date time.
   *
   * @param localDate the local date
   * @return the offset date time
   */
  default OffsetDateTime toApi(final LocalDate localDate) {
    return Objects.nonNull(localDate)
        ? localDate.atStartOfDay(ZoneId.systemDefault()).toOffsetDateTime()
        : null;
  }

  /**
   * To domain local date.
   *
   * @param offsetDateTime the offset date time
   * @return the local date
   */
  default LocalDate toDomain(final OffsetDateTime offsetDateTime) {
    return Objects.nonNull(offsetDateTime) ? offsetDateTime.toLocalDate() : null;
  }
}

