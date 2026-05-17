package edu.uoc.epcsd.microcredential.infrastructure.repository.mappers;

import edu.uoc.epcsd.microcredential.domain.Microcredential;
import edu.uoc.epcsd.microcredential.infrastructure.repository.entities.MicrocredentialEntity;
import org.mapstruct.Mapper;

/** The interface Microcredential mapper. */
@Mapper(componentModel = "spring", implementationName = "MicrocredentialMapperFromRepository")
public interface MicrocredentialMapper {

  /**
   * To domain microcredential.
   *
   * @param microcredentialEntity the microcredential entity
   * @return the microcredential
   */
  Microcredential toDomain(MicrocredentialEntity microcredentialEntity);

  /**
   * To entity microcredential entity.
   *
   * @param microcredential the microcredential
   * @return the microcredential entity
   */
  MicrocredentialEntity toEntity(Microcredential microcredential);
}
