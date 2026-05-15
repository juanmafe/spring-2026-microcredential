package edu.uoc.epcsd.microcredential.domain.exception;

import java.io.Serial;

/** The type Domain exception. */
public class DomainException extends RuntimeException {

  @Serial private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new Domain exception.
   *
   * @param message the message
   */
  public DomainException(final String message) {
    super(message);
  }
}
