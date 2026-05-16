package edu.uoc.epcsd.microcredential.application.rest.mappers;

import edu.uoc.epcsd.microcredential.application.rest.generated.model.CreateMicrocredentialRequestDto;
import edu.uoc.epcsd.microcredential.application.rest.generated.model.MicrocredentialDto;
import edu.uoc.epcsd.microcredential.domain.Microcredential;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/** The interface Microcredential mapper. */
@Mapper(
    componentModel = "spring",
    uses = DateMapper.class,
    implementationName = "MicrocredentialMapperFromRest")
public interface MicrocredentialMapper {

  /**
   * To api microcredential dto.
   *
   * @param microcredential the microcredential
   * @return the microcredential dto
   */
  @Mapping(
      target = "status",
      expression =
          "java(MicrocredentialDto.StatusEnum.valueOf(microcredential.status().name()))")
  MicrocredentialDto toApi(Microcredential microcredential);

  /**
   * To api list.
   *
   * @param microcredentials the microcredentials
   * @return the list
   */
  List<MicrocredentialDto> toApi(List<Microcredential> microcredentials);

  /**
   * To domain microcredential.
   *
   * @param createMicrocredentialRequestDto the create microcredential request dto
   * @return the microcredential
   */
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "assignmentDate", ignore = true)
  @Mapping(target = "status", ignore = true)
  Microcredential toDomain(CreateMicrocredentialRequestDto createMicrocredentialRequestDto);
}

