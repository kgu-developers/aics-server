package kgu.developers.domain.lab.domain;

import java.util.Optional;

public interface LabRepository {
	Lab save(Lab lab);

	Optional<Lab> findById(Long id);
}
