package kgu.developers.domain.lab.infrastructure;

import java.util.List;
import java.util.Optional;

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
		return jpaLabRepository.save(lab);
	}

	@Override
	public Optional<Lab> findById(Long id) {
		return jpaLabRepository.findById(id);
	}

	@Override
	public List<Lab> findAllByOrderByName() {
		return jpaLabRepository.findAllByOrderByName();
	}

	@Override
	public void deleteById(Long id) {
		jpaLabRepository.deleteById(id);
	}

}
