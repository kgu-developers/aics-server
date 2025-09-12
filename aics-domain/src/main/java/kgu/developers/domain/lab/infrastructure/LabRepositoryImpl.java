package kgu.developers.domain.lab.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import kgu.developers.domain.lab.domain.Lab;
import kgu.developers.domain.lab.domain.LabRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LabRepositoryImpl implements LabRepository {
	private final JpaLabRepository jpaLabRepository;

	@Override
	public Lab save(Lab lab) {
		LabEntity labEntity = LabEntity.fromDomain(lab);
		LabEntity savedEntity = jpaLabRepository.save(labEntity);
		return savedEntity.toDomain();
	}

	@Override
	public Optional<Lab> findById(Long id) {
		Optional<LabEntity> optionalEntity = jpaLabRepository.findById(id);
		return optionalEntity.map(LabEntity::toDomain);
	}

	@Override
	public List<Lab> findAllByOrderByName() {
		List<LabEntity> entities = jpaLabRepository.findAllByOrderByName();
		return entities.stream()
				.map(LabEntity::toDomain)
				.collect(Collectors.toList());
	}

	@Override
	public void deleteById(Long id) {
		jpaLabRepository.deleteById(id);
	}

}
