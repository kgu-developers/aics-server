package kgu.developers.domain.professor.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import kgu.developers.domain.professor.domain.Professor;
import kgu.developers.domain.professor.domain.ProfessorRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProfessorRepositoryImpl implements ProfessorRepository {
	private final JpaProfessorRepository jpaProfessorRepository;
	private final QueryProfessorRepository queryProfessorRepository;

	@Override
	public Professor save(Professor professor) {
		ProfessorEntity entity = ProfessorEntity.fromDomain(professor);
		ProfessorEntity saveEntity = jpaProfessorRepository.save(entity);
		return saveEntity.toDomain();
	}

	@Override
	public Optional<Professor> findById(Long id) {
		Optional<ProfessorEntity> optionalEntity = jpaProfessorRepository.findById(id);
		return optionalEntity.map(ProfessorEntity::toDomain);
	}

	@Override
	public List<Professor> findAllOrderByRoleAndName() {
		return queryProfessorRepository.findAllOrderByRoleAndName();
	}

	@Override
	public void deleteById(Long id) {
		jpaProfessorRepository.deleteById(id);
	}
}
