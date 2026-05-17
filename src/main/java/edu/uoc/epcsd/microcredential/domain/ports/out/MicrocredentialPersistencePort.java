package edu.uoc.epcsd.microcredential.domain.ports.out;

import edu.uoc.epcsd.microcredential.domain.Microcredential;
import java.util.List;
import java.util.Optional;

/** The interface Microcredential spring data. */
public interface MicrocredentialPersistencePort {

  /**
   * Gets by id.
   *
   * @param microcredentialId the microcredential id
   * @return the by id
   */
  Optional<Microcredential> getById(Long microcredentialId);

  /**
   * Save microcredential.
   *
   * @param microcredential the microcredential
   * @return the saved microcredential
   */
  Microcredential save(Microcredential microcredential);

  /**
   * Update status of a microcredential.
   *
   * @param microcredentialId the microcredential id
   * @param approved whether to approve (true) or reject (false)
   * @return the updated microcredential
   */
  Microcredential updateStatus(Long microcredentialId, boolean approved);

  /**
   * Find pending microcredentials.
   *
   * @return the list of pending microcredentials
   */
  List<Microcredential> findPending();
}
