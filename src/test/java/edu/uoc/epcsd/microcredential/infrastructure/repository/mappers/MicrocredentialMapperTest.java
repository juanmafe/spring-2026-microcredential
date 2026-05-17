package edu.uoc.epcsd.microcredential.infrastructure.repository.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import edu.uoc.epcsd.microcredential.domain.Microcredential;
import edu.uoc.epcsd.microcredential.infrastructure.repository.entities.MicrocredentialEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/** Unit tests for {@link MicrocredentialMapper} (repository layer). */
@ExtendWith(MockitoExtension.class)
class MicrocredentialMapperTest {

  /** The Microcredential mapper. */
  MicrocredentialMapper microcredentialMapper = new MicrocredentialMapperFromRepository();

  /** Test to domain when microcredential entity provided then return microcredential domain. */
  @Test
  void testToDomain_whenMicrocredentialEntityProvided_thenReturnMicrocredential() {

    // Given
    final var entity = Instancio.create(MicrocredentialEntity.class);

    // When
    final var domain = this.microcredentialMapper.toDomain(entity);

    // Then
    assertNotNull(domain);
    assertEquals(entity.getId(), domain.id());
    assertEquals(entity.getSubmitDate(), domain.submitDate());
    assertEquals(entity.getAssignmentDate(), domain.assignmentDate());
    assertEquals(entity.getStatus(), domain.status());
    assertEquals(entity.getContent(), domain.content());
    assertEquals(entity.getEnrollmentId(), domain.enrollmentId());
  }

  /** Test to entity when microcredential domain provided then return microcredential entity. */
  @Test
  void testToEntity_whenMicrocredentialProvided_thenReturnMicrocredentialEntity() {

    // Given
    final var domain = Instancio.create(Microcredential.class);

    // When
    final var entity = this.microcredentialMapper.toEntity(domain);

    // Then
    assertNotNull(entity);
    assertEquals(domain.id(), entity.getId());
    assertEquals(domain.submitDate(), entity.getSubmitDate());
    assertEquals(domain.assignmentDate(), entity.getAssignmentDate());
    assertEquals(domain.status(), entity.getStatus());
    assertEquals(domain.content(), entity.getContent());
    assertEquals(domain.enrollmentId(), entity.getEnrollmentId());
  }
}
