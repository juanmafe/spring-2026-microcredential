package edu.uoc.epcsd.microcredential.infrastructure.repository;

import edu.uoc.epcsd.microcredential.domain.Microcredential;
import edu.uoc.epcsd.microcredential.domain.MicrocredentialStatus;
import edu.uoc.epcsd.microcredential.domain.exception.MicrocredentialNotFoundException;
import edu.uoc.epcsd.microcredential.domain.ports.out.MicrocredentialPersistencePort;
import edu.uoc.epcsd.microcredential.infrastructure.repository.mappers.MicrocredentialMapper;
import edu.uoc.epcsd.microcredential.infrastructure.repository.springdata.MicrocredentialSpringData;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/** The type Microcredential repository. */
@Component
@RequiredArgsConstructor
public class MicrocredentialRepository implements MicrocredentialPersistencePort {

  private final MicrocredentialSpringData microcredentialSpringData;
  private final MicrocredentialMapper microcredentialMapper;

  @Override
  public Optional<Microcredential> getById(@NotNull final Long microcredentialId) {

    return this.microcredentialSpringData
        .getMicrocredentialById(microcredentialId)
        .map(this.microcredentialMapper::toDomain);
  }

  @Override
  public Microcredential save(@NotNull final Microcredential microcredential) {

    final var microcredentialEntityToPersist = this.microcredentialMapper.toEntity(microcredential);
    final var microcredentialEntity =
        this.microcredentialSpringData.save(microcredentialEntityToPersist);
    return this.microcredentialMapper.toDomain(microcredentialEntity);
  }

  @Override
  public Microcredential updateStatus(
      @NotNull final Long microcredentialId, final boolean approved) {

    final var microcredentialEntity =
        this.microcredentialSpringData
            .findById(microcredentialId)
            .orElseThrow(() -> new MicrocredentialNotFoundException(microcredentialId));

    microcredentialEntity.setStatus(
        approved ? MicrocredentialStatus.GRANTED : MicrocredentialStatus.REJECTED);

    if (approved) {
      microcredentialEntity.setAssignmentDate(LocalDate.now());
    }

    final var microcredentialSavedEntity =
        this.microcredentialSpringData.save(microcredentialEntity);
    return this.microcredentialMapper.toDomain(microcredentialSavedEntity);
  }

  @Override
  public List<Microcredential> findPending() {

    return this.microcredentialSpringData.findByStatus(MicrocredentialStatus.REQUESTED).stream()
        .map(this.microcredentialMapper::toDomain)
        .collect(Collectors.toList());
  }
}
