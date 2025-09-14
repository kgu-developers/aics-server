package mock.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import kgu.developers.domain.professor.domain.Professor;
import kgu.developers.domain.professor.domain.ProfessorRepository;
import kgu.developers.domain.professor.infrastructure.ProfessorEntity;

public class FakeProfessorRepository implements ProfessorRepository {

	private final List<ProfessorEntity> data = Collections.synchronizedList(new ArrayList<>());
	private final AtomicLong sequence = new AtomicLong(1);

	@Override
	public Professor save(Professor professor) {
		if(professor.getId() == null) {
			ProfessorEntity newProfessor = ProfessorEntity.builder()
					.id(sequence.getAndIncrement())
					.name(professor.getName())
					.role(professor.getRole())
					.contact(professor.getContact())
					.email(professor.getEmail())
					.officeLoc(professor.getOfficeLoc())
					.img(professor.getImg())
					.build();

			data.add(newProfessor);
			return newProfessor.toDomain();
		}else{
			ProfessorEntity existing = data.stream()
					.filter(e -> e.getId().equals(professor.getId()))
					.findFirst()
					.orElseThrow(() -> new RuntimeException("Professor not found"));
			existing.updateFromDomain(professor); // 필드만 덮어쓰기
			return existing.toDomain();
		}
	}

	@Override
	public Optional<Professor> findById(Long id) {
		return data.stream()
			.filter(entity -> entity.getId().equals(id))
			.findFirst()
				.map(ProfessorEntity::toDomain);
	}

	@Override
	public List<Professor> findAllOrderByRoleAndName() {
		return data.stream()
				.map(ProfessorEntity::toDomain)
			.sorted(Comparator.comparing(Professor::getRole)
				.thenComparing(Professor::getName))
			.toList();
	}

	@Override
	public void deleteById(Long id) {
		data.stream()
				.filter(entity -> entity.getId().equals(id))
				.findFirst()
				.ifPresent(entity -> entity.delete());
	}
}
