package edu.uoc.epcsd.microcredential.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;
import lombok.Builder;

/** The type Microcredential. */
@Builder
public record Microcredential(
    @NotNull Long id,
    @NotNull Date submitDate,
    @NotNull Date assignmentDate,
    @NotNull MicrocredentialStatus status,
    @NotBlank String content,
    @NotNull Long enrollment) {
  /**
   * Instantiates a new Microcredential.
   *
   * @param id the id
   * @param submitDate the submit date
   * @param assignmentDate the assignment date
   * @param status the status
   * @param content the content
   * @param enrollment the enrollment
   */
  public Microcredential {
    status = Objects.nonNull(status) ? status : MicrocredentialStatus.REQUESTED;
  }
}
