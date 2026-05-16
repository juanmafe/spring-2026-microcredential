package edu.uoc.epcsd.microcredential.application.rest.controllers;

import edu.uoc.epcsd.microcredential.application.rest.generated.PatchMicrocredentialsIdApproveApi;
import edu.uoc.epcsd.microcredential.application.rest.generated.model.ApproveMicrocredentialIdResponseDto;
import edu.uoc.epcsd.microcredential.application.rest.mappers.MicrocredentialMapper;
import edu.uoc.epcsd.microcredential.domain.ports.in.MicrocredentialPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/** The type Patch microcredential approve controller. */
@RestController
@RequiredArgsConstructor
public class PatchMicrocredentialApproveController implements PatchMicrocredentialsIdApproveApi {

  private final MicrocredentialPort microcredentialPort;
  private final MicrocredentialMapper microcredentialMapper;

  @Override
  public ResponseEntity<ApproveMicrocredentialIdResponseDto> patchMicrocredentialsIdApprove(
      final Long microcredentialId) {

    final var approvedMicrocredential =
        this.microcredentialPort.approvePendingMicrocredential(microcredentialId);

    final var microcredentialDto = this.microcredentialMapper.toApi(approvedMicrocredential);
    final var approveMicrocredentialIdResponseDto = new ApproveMicrocredentialIdResponseDto();
    approveMicrocredentialIdResponseDto.setMicrocredential(microcredentialDto);

    return ResponseEntity.ok(approveMicrocredentialIdResponseDto);
  }
}

