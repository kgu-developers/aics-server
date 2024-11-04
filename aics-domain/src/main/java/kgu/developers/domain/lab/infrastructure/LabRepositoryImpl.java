package kgu.developers.domain.lab.infrastructure;

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
}
