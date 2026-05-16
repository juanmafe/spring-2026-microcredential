package edu.uoc.epcsd.microcredential.infrastructure.repository.entities;

import edu.uoc.epcsd.microcredential.domain.MicrocredentialStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.*;

/** The type Microcredential entity. */
@Entity(name = "microcredential")
@Setter
@Getter
@NoArgsConstructor
public class MicrocredentialEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "submitdate", nullable = false)
  private LocalDate submitDate;

  @Column(name = "assignmentdate")
  private LocalDate assignmentDate;

  @Column(name = "enrollment", nullable = false)
  private Long enrollment;

  @Column(name = "content")
  private String content;

  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  private MicrocredentialStatus status = MicrocredentialStatus.REQUESTED;
}
