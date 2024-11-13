package kgu.developers.domain.professor.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kgu.developers.domain.professor.domain.Professor;

public interface JpaProfessorRepository extends JpaRepository<Professor, Long> {

	List<Professor> findAllByOrderByName();
}
