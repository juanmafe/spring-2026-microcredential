package edu.uoc.epcsd.microcredential.application.rest.controllers;

import edu.uoc.epcsd.microcredential.application.rest.generated.PostMicrocredentialsApi;
import edu.uoc.epcsd.microcredential.application.rest.generated.model.CreateMicrocredentialRequestDto;
import edu.uoc.epcsd.microcredential.application.rest.generated.model.CreateMicrocredentialResponseDto;
import edu.uoc.epcsd.microcredential.application.rest.mappers.MicrocredentialMapper;
import edu.uoc.epcsd.microcredential.domain.ports.in.MicrocredentialPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/** The type Post microcredential controller. */
@RestController
@RequiredArgsConstructor
public class PostMicrocredentialController implements PostMicrocredentialsApi {

  private final MicrocredentialPort microcredentialPort;
  private final MicrocredentialMapper microcredentialMapper;

  @Override
  public ResponseEntity<CreateMicrocredentialResponseDto> postMicrocredentials(
      final CreateMicrocredentialRequestDto createMicrocredentialRequestDto) {

    final var microcredential =
        this.microcredentialMapper.toDomain(createMicrocredentialRequestDto);

    final var createdMicrocredential =
        this.microcredentialPort.requestMicrocredential(microcredential);

    final var microcredentialDto = this.microcredentialMapper.toApi(createdMicrocredential);
    final var createMicrocredentialResponseDto = new CreateMicrocredentialResponseDto();
    createMicrocredentialResponseDto.setMicrocredential(microcredentialDto);

    return ResponseEntity.status(HttpStatus.CREATED).body(createMicrocredentialResponseDto);
  }
}

