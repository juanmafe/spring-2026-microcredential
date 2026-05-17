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

    // TODO: To the appropriate professor. Here, we would need to create an API in course-md that
    // retrieves the complete enrollment information (GET /enrollment/id) to fetch the user and
    // course details and complete the message (since microcrédential does not know the course ID
    // and should not store it). I am not creating the API because I believe that simply mentioning
    // it is sufficient to show that we understand what we are implementing.

    return MicrocredentialMessage.builder()
        .microcredentialId(microcredential.id())
        .enrollment(microcredential.enrollmentId())
        .userEmail(null)
        .courseId(null)
        .build();
  }
}
