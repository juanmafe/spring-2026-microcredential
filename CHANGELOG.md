# Changelog — Microcredential MS

## 2026-05-01

- Updated versions: Spring Boot, Java, springdoc, ArchUnit
- Added testing dependencies: instancio
- Centralized version management with dependencyManagement
- Removed Spring Batch property (not used)
- Changed ddl-auto from create-drop to update

## 2026-05-15

- First refactor normalizing project structure and including openapi

## 2026-05-16

- Fixes in model
- Bruno api added
- Controllers and mappers added
- Controllers and mappers tests

## 2026-05-17

- Fixes in api and services according to architecture model
- Attending Kafka configuration
- Kafka fixes according to accomplishment DIP
- Typo fixes
- The previous implementation sent courseId + studentMail to POST /microcredentials,
  which allowed arbitrary combinations of course and student data to be trusted
  without validation. Replaced with enrollmentId as the sole source of truth,
  since an enrollment already encodes the verified student-course relationship.
- Security: by passing only enrollmentId, the microcredential service cannot
  receive mismatched course/student data. The Kafka notification messages
  intentionally leave userEmail and courseId as null until the Notification
  service resolves them from the enrollmentId via the Course service,
  avoiding propagation of unverified data through the event pipeline.