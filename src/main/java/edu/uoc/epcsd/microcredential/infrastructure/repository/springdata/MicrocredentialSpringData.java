package edu.uoc.epcsd.microcredential.infrastructure.repository.springdata;

import edu.uoc.epcsd.microcredential.domain.MicrocredentialStatus;
import edu.uoc.epcsd.microcredential.infrastructure.repository.entities.MicrocredentialEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** The interface Microcredential spring data. */
@Repository
public interface MicrocredentialSpringData extends JpaRepository<MicrocredentialEntity, Long> {

  /**
   * Gets microcredential by id.
   *
   * @param id the id
   * @return the microcredential by id
   */
  Optional<MicrocredentialEntity> getMicrocredentialById(Long id);

  /**
   * Find microcredential entities by status.
   *
   * @param status the status
   * @return the list of microcredential entities with the given status
   */
  List<MicrocredentialEntity> findByStatus(MicrocredentialStatus status);
}
