package kgu.developers.domain.professor.infrastructure;

import kgu.developers.domain.professor.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProfessorRepository extends JpaRepository<Professor, Long> {
}
