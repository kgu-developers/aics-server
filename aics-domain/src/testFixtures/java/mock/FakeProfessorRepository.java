package mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import kgu.developers.domain.professor.domain.Professor;
import kgu.developers.domain.professor.domain.ProfessorRepository;

public class FakeProfessorRepository implements ProfessorRepository {

	private final List<Professor> data = Collections.synchronizedList(new ArrayList<>());
	private final AtomicLong sequence = new AtomicLong(1);

	@Override
	public Professor save(Professor professor) {
		Professor newProfessor = Professor.builder()
			.id(sequence.getAndIncrement())
			.name(professor.getName())
			.role(professor.getRole())
			.contact(professor.getContact())
			.email(professor.getEmail())
			.build();

		data.add(newProfessor);
		return newProfessor;
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
	public void deleteById(Long id) {
		data.removeIf(professor -> professor.getId().equals(id));
	}
}
