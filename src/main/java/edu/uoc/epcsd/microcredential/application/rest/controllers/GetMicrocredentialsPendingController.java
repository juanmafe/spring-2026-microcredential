package edu.uoc.epcsd.microcredential.application.rest.controllers;

import edu.uoc.epcsd.microcredential.application.rest.generated.GetMicrocredentialsPendingApi;
import edu.uoc.epcsd.microcredential.application.rest.generated.model.GetPendingMicrocredentialsResponseDto;
import edu.uoc.epcsd.microcredential.application.rest.mappers.MicrocredentialMapper;
import edu.uoc.epcsd.microcredential.domain.ports.in.MicrocredentialPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/** The type Get microcredentials pending controller. */
@RestController
@RequiredArgsConstructor
public class GetMicrocredentialsPendingController implements GetMicrocredentialsPendingApi {

  private final MicrocredentialPort microcredentialPort;
  private final MicrocredentialMapper microcredentialMapper;

  @Override
  public ResponseEntity<GetPendingMicrocredentialsResponseDto> getMicrocredentialsPending() {

    final var pendingMicrocredentials = this.microcredentialPort.getPendingMicrocredentials();

    if (pendingMicrocredentials.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    final var microcredentialsDto = this.microcredentialMapper.toApi(pendingMicrocredentials);
    final var getPendingMicrocredentialsResponseDto = new GetPendingMicrocredentialsResponseDto();
    getPendingMicrocredentialsResponseDto.setMicrocredentials(microcredentialsDto);

    return ResponseEntity.ok(getPendingMicrocredentialsResponseDto);
  }
}

