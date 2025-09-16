package kgu.developers.domain.professor.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProfessorRepository extends JpaRepository<ProfessorJpaEntity, Long> {
}
