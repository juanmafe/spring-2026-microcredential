package edu.uoc.epcsd.microcredential.domain.service;

import edu.uoc.epcsd.microcredential.domain.Microcredential;
import edu.uoc.epcsd.microcredential.domain.ports.out.MicrocredentialPersistencePort;
import edu.uoc.epcsd.microcredential.infrastructure.kafka.MicrocredentialMessage;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@RequiredArgsConstructor
@Service
@Validated
public class MicrocredentialServiceImpl implements MicrocredentialService {

  private final MicrocredentialPersistencePort microcredentialPersistencePort;
  private final KafkaTemplate<String, MicrocredentialMessage> microcredentialKafkaTemplate;

  @Value("${courseService.url}")
  private String courseServiceUrl;

  @Override
  public Optional<Microcredential> getMicrocredentialById(Long microcredentialId) {
    // TODO: Complete the implementation
    return null;
  }

  @Override
  public Long requestMicrocredential(Microcredential microcredential) {
    // TODO: Complete the implementation
    return null;
  }

  @Override
  public Microcredential approvePendingMicrocredential(Long microcredentialId) {
    // TODO: Complete the implementation
    return null;
  }

  @Override
  public Microcredential rejectPendingMicrocredential(Long microcredentialId) {
    // TODO: Complete the implementation
    return null;
  }

  @Override
  public List<Microcredential> requestCourseMicrocredentials(Long courseId) {
    // TODO: Complete the implementation
    return null;
  }
}
