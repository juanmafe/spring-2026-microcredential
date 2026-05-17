package edu.uoc.epcsd.microcredential.infrastructure.kafka.messages;

import lombok.Builder;

/** Kafka message carrying microcredential event data. */
@Builder
public record MicrocredentialMessage(
    Long microcredentialId, String userEmail, Long courseId, Long enrollment) {}
