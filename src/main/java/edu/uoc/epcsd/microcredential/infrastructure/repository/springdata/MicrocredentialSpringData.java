package edu.uoc.epcsd.microcredential.infrastructure.repository.springdata;

import edu.uoc.epcsd.microcredential.infrastructure.repository.entities.MicrocredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

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
}
