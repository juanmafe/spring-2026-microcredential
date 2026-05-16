package edu.uoc.epcsd.microcredential.application.rest.controllers;

import edu.uoc.epcsd.microcredential.application.rest.generated.PatchMicrocredentialsIdRejectApi;
import edu.uoc.epcsd.microcredential.application.rest.generated.model.RejectMicrocredentialIdResponseDto;
import edu.uoc.epcsd.microcredential.application.rest.mappers.MicrocredentialMapper;
import edu.uoc.epcsd.microcredential.domain.ports.in.MicrocredentialPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/** The type Patch microcredential reject controller. */
@RestController
@RequiredArgsConstructor
public class PatchMicrocredentialRejectController implements PatchMicrocredentialsIdRejectApi {

  private final MicrocredentialPort microcredentialPort;
  private final MicrocredentialMapper microcredentialMapper;

  @Override
  public ResponseEntity<RejectMicrocredentialIdResponseDto> patchMicrocredentialsIdReject(
      final Long microcredentialId) {

    final var rejectedMicrocredential =
        this.microcredentialPort.rejectPendingMicrocredential(microcredentialId);

    final var microcredentialDto = this.microcredentialMapper.toApi(rejectedMicrocredential);
    final var rejectMicrocredentialIdResponseDto = new RejectMicrocredentialIdResponseDto();
    rejectMicrocredentialIdResponseDto.setMicrocredential(microcredentialDto);

    return ResponseEntity.ok(rejectMicrocredentialIdResponseDto);
  }
}

