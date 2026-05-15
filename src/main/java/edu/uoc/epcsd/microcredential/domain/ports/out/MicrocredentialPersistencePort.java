package edu.uoc.epcsd.microcredential.domain.ports.out;

import edu.uoc.epcsd.microcredential.domain.Microcredential;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/** The interface Microcredential spring data. */
@Repository
public interface MicrocredentialPersistencePort {

  /**
   * Gets by id.
   *
   * @param microcredentialId the microcredential id
   * @return the by id
   */
  Optional<Microcredential> getById(Long microcredentialId);
}
