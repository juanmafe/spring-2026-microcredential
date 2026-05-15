package edu.uoc.epcsd.microcredential.infrastructure.repository;

import edu.uoc.epcsd.microcredential.domain.Microcredential;
import edu.uoc.epcsd.microcredential.domain.ports.out.MicrocredentialPersistencePort;
import edu.uoc.epcsd.microcredential.infrastructure.repository.springdata.MicrocredentialSpringData;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/** The type Microcredential repository. */
@Component
@RequiredArgsConstructor
public class MicrocredentialRepository implements MicrocredentialPersistencePort {

  private final MicrocredentialSpringData microcredentialSpringData;

  @Override
  public Optional<Microcredential> getById(Long microcredentialId) {
    // TODO: Complete the implementation
    return null;
  }

  // TODO: createMicrocredential()

  // TODO: updateStatusPendingMicrocredential()

  // TODO: getPendingMicrocredentialRequests()

}
