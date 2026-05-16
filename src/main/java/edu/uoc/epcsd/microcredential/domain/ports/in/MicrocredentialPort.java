package edu.uoc.epcsd.microcredential.domain.ports.in;

import edu.uoc.epcsd.microcredential.domain.Microcredential;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/** The interface Microcredential port. */
public interface MicrocredentialPort {

  /**
   * Gets microcredential by id.
   *
   * @param microcredentialId the microcredential id
   * @return the microcredential by id
   */
  Optional<Microcredential> getMicrocredentialById(@NotNull Long microcredentialId);

  /**
   * Request microcredential.
   *
   * @param microcredential the microcredential
   * @return the created microcredential
   */
  Microcredential requestMicrocredential(@NotNull Microcredential microcredential);

  /**
   * Approve pending microcredential.
   *
   * @param microcredentialId the microcredential id
   * @return the approved microcredential
   */
  Microcredential approvePendingMicrocredential(@NotNull Long microcredentialId);

  /**
   * Reject pending microcredential.
   *
   * @param microcredentialId the microcredential id
   * @return the rejected microcredential
   */
  Microcredential rejectPendingMicrocredential(@NotNull Long microcredentialId);

  /**
   * Get microcredentials for a course.
   *
   * @param courseId the course id
   * @return the list of microcredentials for the course
   */
  List<Microcredential> getCourseMicrocredentials(@NotNull Long courseId);

  /**
   * Get pending microcredentials.
   *
   * @return the list of pending microcredentials
   */
  List<Microcredential> getPendingMicrocredentials();
}

