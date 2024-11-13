package kgu.developers.domain.professor.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import kgu.developers.domain.professor.domain.Professor;

public interface JpaProfessorRepository extends JpaRepository<Professor, Long> {
}
