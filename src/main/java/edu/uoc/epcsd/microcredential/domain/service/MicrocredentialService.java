package edu.uoc.epcsd.microcredential.domain.service;

import edu.uoc.epcsd.microcredential.domain.Microcredential;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface MicrocredentialService {

  Optional<Microcredential> getMicrocredentialById(@NotNull Long microcredentialId);

  Long requestMicrocredential(@NotNull Microcredential microcredential);

  Microcredential approvePendingMicrocredential(@NotNull Long microcredentialId);

  Microcredential rejectPendingMicrocredential(@NotNull Long microcredentialId);

  List<Microcredential> requestCourseMicrocredentials(@NotNull Long courseId);
}
