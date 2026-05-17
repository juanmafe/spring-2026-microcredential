package edu.uoc.epcsd.microcredential.domain.service;

import edu.uoc.epcsd.microcredential.domain.Microcredential;
import edu.uoc.epcsd.microcredential.domain.ports.in.MicrocredentialPort;
import edu.uoc.epcsd.microcredential.domain.ports.out.MicrocredentialEventPort;
import edu.uoc.epcsd.microcredential.domain.ports.out.MicrocredentialPersistencePort;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/** The type Microcredential service impl. */
@Validated
@Service
@RequiredArgsConstructor
public class MicrocredentialServiceImpl implements MicrocredentialPort {

  private final MicrocredentialPersistencePort microcredentialPersistencePort;
  private final MicrocredentialEventPort microcredentialEventPort;

  @Override
  public Optional<Microcredential> getById(@NotNull final Long microcredentialId) {
    return this.microcredentialPersistencePort.getById(microcredentialId);
  }

  @Override
  public Microcredential createMicrocredential(@NotNull final Microcredential microcredential) {

    final var microcredentialSaved = this.microcredentialPersistencePort.save(microcredential);
    this.microcredentialEventPort.publishPending(microcredentialSaved);
    return microcredentialSaved;
  }

  @Override
  public Microcredential approvePendingMicrocredential(@NotNull final Long microcredentialId) {

    final var microcredentialApproved =
        this.microcredentialPersistencePort.updateStatus(microcredentialId, Boolean.TRUE);
    this.microcredentialEventPort.publishApproved(microcredentialApproved);
    return microcredentialApproved;
  }

  @Override
  public Microcredential rejectPendingMicrocredential(@NotNull final Long microcredentialId) {

    final var microcredentialRejected =
        this.microcredentialPersistencePort.updateStatus(microcredentialId, Boolean.FALSE);
    this.microcredentialEventPort.publishRejected(microcredentialRejected);
    return microcredentialRejected;
  }

  @Override
  public List<Microcredential> getPendingMicrocredentials() {
    return this.microcredentialPersistencePort.findPending();
  }
}
