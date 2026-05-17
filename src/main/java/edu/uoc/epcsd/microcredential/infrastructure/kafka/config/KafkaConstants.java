package edu.uoc.epcsd.microcredential.infrastructure.kafka.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/** The type Kafka constants. */
@NoArgsConstructor(access = AccessLevel.NONE)
public final class KafkaConstants {

  /** The constant SEPARATOR. */
  public static final String SEPARATOR = ".";

  /** The constant MICROCREDENTIAL_TOPIC. */
  public static final String MICROCREDENTIAL_TOPIC = "microcredential";

  /** The constant APPROVED. */
  public static final String APPROVED = "microcredential_approved";

  /** The constant REJECTED. */
  public static final String REJECTED = "microcredential_rejected";

  /** The constant PENDING. */
  public static final String PENDING = "microcredential_pending";
}
