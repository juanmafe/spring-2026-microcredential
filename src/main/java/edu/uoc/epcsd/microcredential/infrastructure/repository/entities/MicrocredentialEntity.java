package edu.uoc.epcsd.microcredential.infrastructure.repository.entities;

import edu.uoc.epcsd.microcredential.domain.MicrocredentialStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import lombok.*;

@Entity(name = "microcredential")
@ToString
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MicrocredentialEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "submitdate", nullable = true)
  private Date submitDate;

  @Column(name = "assignmentdate", nullable = false)
  private Date assignmentDate;

  @Column(name = "enrollment", nullable = false)
  private Long enrollment;

  @Column(name = "content", nullable = true)
  private String content;

  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  @Builder.Default
  private MicrocredentialStatus status = MicrocredentialStatus.REQUESTED;
}
