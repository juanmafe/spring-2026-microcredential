package edu.uoc.epcsd.microcredential.infrastructure.kafka;

import edu.uoc.epcsd.microcredential.domain.Microcredential;
import edu.uoc.epcsd.microcredential.domain.ports.out.MicrocredentialEventPort;
import edu.uoc.epcsd.microcredential.infrastructure.kafka.config.KafkaConstants;
import edu.uoc.epcsd.microcredential.infrastructure.kafka.messages.MicrocredentialMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/** The type Microcredential kafka adapter. */
@Slf4j
@Component
@RequiredArgsConstructor
public class MicrocredentialKafkaAdapter implements MicrocredentialEventPort {

  private final KafkaTemplate<String, MicrocredentialMessage> microcredentialKafkaTemplate;

  @Override
  public void publishPending(final Microcredential microcredential) {

    final var message = buildMessage(microcredential);

    this.microcredentialKafkaTemplate.send(
        KafkaConstants.MICROCREDENTIAL_TOPIC + KafkaConstants.SEPARATOR + KafkaConstants.PENDING,
        message);

    log.info(
        "Microcredential {} requested for enrollment {}",
        microcredential.id(),
        microcredential.enrollmentId());
  }

  @Override
  public void publishApproved(final Microcredential microcredential) {

    final var message = buildMessage(microcredential);

    this.microcredentialKafkaTemplate.send(
        KafkaConstants.MICROCREDENTIAL_TOPIC + KafkaConstants.SEPARATOR + KafkaConstants.APPROVED,
        message);

    log.info(
        "Microcredential {} approved for enrollment {}",
        microcredential.id(),
        microcredential.enrollmentId());
  }

  @Override
  public void publishRejected(final Microcredential microcredential) {

    final var message = buildMessage(microcredential);

    this.microcredentialKafkaTemplate.send(
        KafkaConstants.MICROCREDENTIAL_TOPIC + KafkaConstants.SEPARATOR + KafkaConstants.REJECTED,
        message);

    log.info(
        "Microcredential {} rejected for enrollment {}",
        microcredential.id(),
        microcredential.enrollmentId());
  }

  private MicrocredentialMessage buildMessage(final Microcredential microcredential) {
    return MicrocredentialMessage.builder()
        .microcredentialId(microcredential.id())
        .enrollment(microcredential.enrollmentId())
        .userEmail(null)
        .courseId(null)
        .build();
  }
}
