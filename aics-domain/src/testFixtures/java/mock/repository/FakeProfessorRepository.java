package mock.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import kgu.developers.domain.professor.domain.Professor;
import kgu.developers.domain.professor.domain.ProfessorRepository;
import kgu.developers.domain.professor.infrastructure.ProfessorJpaEntity;

public class FakeProfessorRepository implements ProfessorRepository {

	private final List<ProfessorJpaEntity> data = Collections.synchronizedList(new ArrayList<>());
	private final AtomicLong sequence = new AtomicLong(1);

	@Override
	public Professor save(Professor professor) {
		Long id = (professor.getId() == null) ? sequence.getAndIncrement() : professor.getId();

		ProfessorJpaEntity newEntity = ProfessorJpaEntity.toEntity(
				Professor.builder()
						.id(id)
						.name(professor.getName())
						.role(professor.getRole())
						.contact(professor.getContact())
						.email(professor.getEmail())
						.img(professor.getImg())
						.officeLoc(professor.getOfficeLoc())
						.deletedAt(professor.getDeletedAt())
						.build()
		);

		data.removeIf(entity -> entity.getId().equals(id)); // ✅ 중복 방지
		data.add(newEntity);
		return newEntity.toDomain();

	}

	@Override
	public Optional<Professor> findById(Long id) {
		return data.stream()
			.filter(entity -> entity.getId().equals(id))
			.findFirst()
				.map(ProfessorJpaEntity::toDomain);
	}

	@Override
	public List<Professor> findAllOrderByRoleAndName() {
		return data.stream()
				.map(ProfessorJpaEntity::toDomain)
			.sorted(Comparator.comparing(Professor::getRole)
				.thenComparing(Professor::getName))
			.toList();
	}

	@Override
	public void deleteById(Long id) {
		data.removeIf(professor -> professor.getId().equals(id));
	}
}
