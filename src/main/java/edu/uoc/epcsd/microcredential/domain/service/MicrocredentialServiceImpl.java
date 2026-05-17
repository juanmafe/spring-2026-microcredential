package edu.uoc.epcsd.microcredential.domain.service;

import edu.uoc.epcsd.microcredential.domain.Microcredential;
import edu.uoc.epcsd.microcredential.domain.ports.in.MicrocredentialPort;
import edu.uoc.epcsd.microcredential.domain.ports.out.MicrocredentialPersistencePort;
import edu.uoc.epcsd.microcredential.infrastructure.kafka.KafkaConstants;
import edu.uoc.epcsd.microcredential.infrastructure.kafka.MicrocredentialMessage;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/** The type Microcredential service impl. */
@Slf4j
@Validated
@Service
@RequiredArgsConstructor
public class MicrocredentialServiceImpl implements MicrocredentialPort {

  private final MicrocredentialPersistencePort microcredentialPersistencePort;
  private final KafkaTemplate<String, MicrocredentialMessage> microcredentialKafkaTemplate;

  @Override
  public Optional<Microcredential> getById(@NotNull final Long microcredentialId) {
    return this.microcredentialPersistencePort.getById(microcredentialId);
  }

  @Override
  public Microcredential createMicrocredential(@NotNull final Microcredential microcredential) {

    final var microcredentialSaved = this.microcredentialPersistencePort.save(microcredential);

    final var microcredentialMessage =
        MicrocredentialMessage.builder()
            .microcredentialId(microcredentialSaved.id())
            .enrollment(microcredentialSaved.enrollmentId())
            .userEmail(null)
            .courseId(null)
            .build();

    this.microcredentialKafkaTemplate.send(
        KafkaConstants.MICROCREDENTIAL_TOPIC + KafkaConstants.SEPARATOR + KafkaConstants.PENDING,
        microcredentialMessage);

    log.info(
        "Microcredential {} requested for enrollment {}",
        microcredentialSaved.id(),
        microcredentialSaved.enrollmentId());

    return microcredentialSaved;
  }

  @Override
  public Microcredential approvePendingMicrocredential(@NotNull final Long microcredentialId) {

    final var microcredentialApproved =
        this.microcredentialPersistencePort.updateStatus(microcredentialId, Boolean.TRUE);

    final var microcredentialMessage =
        MicrocredentialMessage.builder()
            .microcredentialId(microcredentialApproved.id())
            .enrollment(microcredentialApproved.enrollmentId())
            .userEmail(null)
            .courseId(null)
            .build();

    this.microcredentialKafkaTemplate.send(
        KafkaConstants.MICROCREDENTIAL_TOPIC + KafkaConstants.SEPARATOR + KafkaConstants.APPROVED,
        microcredentialMessage);

    log.info(
        "Microcredential {} approved for enrollment {}",
        microcredentialApproved.id(),
        microcredentialApproved.enrollmentId());

    return microcredentialApproved;
  }

  @Override
  public Microcredential rejectPendingMicrocredential(@NotNull final Long microcredentialId) {

    final var microcredentialRejected =
        this.microcredentialPersistencePort.updateStatus(microcredentialId, Boolean.FALSE);

    final var microcredentialMessage =
        MicrocredentialMessage.builder()
            .microcredentialId(microcredentialRejected.id())
            .enrollment(microcredentialRejected.enrollmentId())
            .userEmail(null)
            .courseId(null)
            .build();

    this.microcredentialKafkaTemplate.send(
        KafkaConstants.MICROCREDENTIAL_TOPIC + KafkaConstants.SEPARATOR + KafkaConstants.REJECTED,
        microcredentialMessage);

    log.info(
        "Microcredential {} rejected for enrollment {}",
        microcredentialRejected.id(),
        microcredentialRejected.enrollmentId());

    return microcredentialRejected;
  }

  @Override
  public List<Microcredential> getPendingMicrocredentials() {
    return this.microcredentialPersistencePort.findPending();
  }
}
