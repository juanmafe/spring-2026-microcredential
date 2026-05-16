package edu.uoc.epcsd.microcredential.application.rest.controllers;

import edu.uoc.epcsd.microcredential.application.rest.generated.GetMicrocredentialsCourseIdApi;
import edu.uoc.epcsd.microcredential.application.rest.generated.model.GetCourseMicrocredentialsResponseDto;
import edu.uoc.epcsd.microcredential.application.rest.mappers.MicrocredentialMapper;
import edu.uoc.epcsd.microcredential.domain.ports.in.MicrocredentialPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/** The type Get microcredentials course id controller. */
@RestController
@RequiredArgsConstructor
public class GetMicrocredentialsCourseIdController implements GetMicrocredentialsCourseIdApi {

  private final MicrocredentialPort microcredentialPort;
  private final MicrocredentialMapper microcredentialMapper;

  @Override
  public ResponseEntity<GetCourseMicrocredentialsResponseDto> getMicrocredentialsCourseId(
      final Long courseId) {

    final var microcredentials =
        this.microcredentialPort.getCourseMicrocredentials(courseId);

    if (microcredentials.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    final var microcredentialsDto = this.microcredentialMapper.toApi(microcredentials);
    final var getCourseMicrocredentialsResponseDto = new GetCourseMicrocredentialsResponseDto();
    getCourseMicrocredentialsResponseDto.setMicrocredentials(microcredentialsDto);

    return ResponseEntity.ok(getCourseMicrocredentialsResponseDto);
  }
}

