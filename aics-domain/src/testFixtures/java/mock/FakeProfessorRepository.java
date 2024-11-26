package mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import kgu.developers.domain.professor.domain.Professor;
import kgu.developers.domain.professor.domain.ProfessorRepository;

public class FakeProfessorRepository implements ProfessorRepository {

	private final List<Professor> data = Collections.synchronizedList(new ArrayList<>());
	private Long sequence = 1L;

	@Override
	public Professor save(Professor professor) {
		if (professor.getId() == null) {
			Professor newProfessor = Professor.builder()
				.id(sequence++)
				.name(professor.getName())
				.role(professor.getRole())
				.contact(professor.getContact())
				.email(professor.getEmail())
				.build();
			data.add(newProfessor);
			return newProfessor;
		} else {
			deleteById(professor.getId());
			data.add(professor);
			return professor;
		}
	}

	@Override
	public Optional<Professor> findById(Long id) {
		return data.stream()
			.filter(professor -> professor.getId().equals(id))
			.findFirst();
	}

	@Override
	public List<Professor> findAllOrderByRoleAndName() {
		return data.stream()
			.sorted(Comparator.comparing(Professor::getRole)
				.thenComparing(Professor::getName))
			.toList();
	}

	@Override
	public void delete(Professor professor) {
		data.removeIf(p -> p.getId().equals(professor.getId()));
	}

	// 추가: ID로 삭제하는 메서드
	public void deleteById(Long id) {
		data.removeIf(professor -> professor.getId().equals(id));
	}
}
