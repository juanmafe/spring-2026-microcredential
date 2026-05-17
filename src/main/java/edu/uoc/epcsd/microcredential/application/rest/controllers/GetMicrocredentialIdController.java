package edu.uoc.epcsd.microcredential.application.rest.controllers;

import edu.uoc.epcsd.microcredential.application.rest.generated.GetMicrocredentialsIdApi;
import edu.uoc.epcsd.microcredential.application.rest.generated.model.GetMicrocredentialIdResponseDto;
import edu.uoc.epcsd.microcredential.application.rest.mappers.MicrocredentialMapper;
import edu.uoc.epcsd.microcredential.domain.ports.in.MicrocredentialPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/** The type Get microcredential id controller. */
@RestController
@RequiredArgsConstructor
public class GetMicrocredentialIdController implements GetMicrocredentialsIdApi {

  private final MicrocredentialPort microcredentialPort;
  private final MicrocredentialMapper microcredentialMapper;

  @Override
  public ResponseEntity<GetMicrocredentialIdResponseDto> getMicrocredentialsId(
      final Long microcredentialId) {

    final var microcredential = this.microcredentialPort.getById(microcredentialId);

    if (microcredential.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    final var microcredentialDto = this.microcredentialMapper.toApi(microcredential.get());
    final var getMicrocredentialIdResponseDto = new GetMicrocredentialIdResponseDto();
    getMicrocredentialIdResponseDto.setMicrocredential(microcredentialDto);

    return ResponseEntity.ok(getMicrocredentialIdResponseDto);
  }
}
