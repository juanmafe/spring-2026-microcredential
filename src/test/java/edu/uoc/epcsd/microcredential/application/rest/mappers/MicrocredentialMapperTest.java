package edu.uoc.epcsd.microcredential.application.rest.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.uoc.epcsd.microcredential.application.rest.generated.model.CreateMicrocredentialRequestDto;
import edu.uoc.epcsd.microcredential.application.rest.generated.model.MicrocredentialDto;
import edu.uoc.epcsd.microcredential.domain.Microcredential;
import java.time.ZoneId;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/** The type Microcredential mapper test. */
@ExtendWith(MockitoExtension.class)
class MicrocredentialMapperTest {

  /** The Microcredential mapper. */
  @InjectMocks MicrocredentialMapperFromRest microcredentialMapper;

  /** The Date mapper. */
  @Mock DateMapper dateMapper;

  /** Test to api when microcredential provided then return microcredential dto. */
  @Test
  void testToApi_whenMicrocredentialProvided_thenReturnMicrocredentialDto() {

    // Given
    final var microcredential = Instancio.create(Microcredential.class);
    final var submitDateOffset =
        microcredential.submitDate().atStartOfDay(ZoneId.systemDefault()).toOffsetDateTime();
    final var assignmentDateOffset =
        microcredential.assignmentDate().atStartOfDay(ZoneId.systemDefault()).toOffsetDateTime();

    // When
    when(this.dateMapper.toApi(microcredential.submitDate())).thenReturn(submitDateOffset);
    when(this.dateMapper.toApi(microcredential.assignmentDate())).thenReturn(assignmentDateOffset);

    final var microcredentialDto = this.microcredentialMapper.toApi(microcredential);

    // Then
    verify(this.dateMapper, times(1)).toApi(microcredential.submitDate());
    verify(this.dateMapper, times(1)).toApi(microcredential.assignmentDate());
    assertNotNull(microcredentialDto);
    assertEquals(microcredential.id(), microcredentialDto.getId());
    assertEquals(submitDateOffset, microcredentialDto.getSubmitDate());
    assertEquals(assignmentDateOffset, microcredentialDto.getAssignmentDate());
    assertEquals(
        MicrocredentialDto.StatusEnum.valueOf(microcredential.status().name()),
        microcredentialDto.getStatus());
    assertEquals(microcredential.content(), microcredentialDto.getContent());
    assertEquals(microcredential.enrollmentId(), microcredentialDto.getEnrollmentId());
  }

  /**
   * Test to domain when create microcredential request dto provided then return microcredential.
   */
  @Test
  void testToDomain_whenCreateMicrocredentialRequestDtoProvided_thenReturnMicrocredential() {

    // Given
    final var requestDto = Instancio.create(CreateMicrocredentialRequestDto.class);

    // When
    when(this.dateMapper.toDomain(requestDto.getSubmitDate()))
        .thenReturn(requestDto.getSubmitDate().toLocalDate());

    final var microcredential = this.microcredentialMapper.toDomain(requestDto);

    // Then
    verify(this.dateMapper, times(1)).toDomain(requestDto.getSubmitDate());
    assertNotNull(microcredential);
    assertEquals(requestDto.getSubmitDate().toLocalDate(), microcredential.submitDate());
    assertEquals(requestDto.getContent(), microcredential.content());
    assertEquals(requestDto.getEnrollmentId(), microcredential.enrollmentId());
  }
}
