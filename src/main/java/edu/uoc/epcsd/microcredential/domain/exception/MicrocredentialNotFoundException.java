package edu.uoc.epcsd.microcredential.domain.exception;

import java.io.Serial;

/** The type Microcredential not found exception. */
public class MicrocredentialNotFoundException extends DomainException {

  @Serial private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new Microcredential not found exception.
   *
   * @param microcredentialId the microcredential id
   */
  public MicrocredentialNotFoundException(final Long microcredentialId) {
    super("Microcredential with id '" + microcredentialId + "' not found");
  }
}

